package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import models.UsuarioModel;

public class DaoUsuario {
	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	
	/// Lista todos os usuários que contém a tag pesquisada
	public List<UsuarioModel> listar(String consulta) throws Exception {
		String table_name = "usuarios";
		String sql = "";

		sql += "SELECT * FROM " + table_name;
		sql += " WHERE login != 'admin' AND senha != 'root1234' AND nome LIKE '%" + consulta + "%'";
		sql += " ORDER BY ID ASC";

		return consultarUsuarios(sql);
	}

	/// Lista todos os usuários cadastrados
	public List<UsuarioModel> listar() throws Exception {
		//List<UsuarioModel> listar = new ArrayList<UsuarioModel>();

		String table_name = "usuarios";
		String sql = "";

		sql += "SELECT * FROM " + table_name;
		sql += " WHERE login != 'admin' AND senha != 'root1234'";
		sql += " ORDER BY ID ASC";

		return consultarUsuarios(sql);


	}


	//private void consultarUsuarios(List<UsuarioModel> listar, String sql) throws SQLException {
	private List<UsuarioModel> consultarUsuarios(String sql) throws SQLException {
		
		List<UsuarioModel> listar = new ArrayList<UsuarioModel>();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			UsuarioModel usuarioModel = new UsuarioModel();
			
			usuarioModel.setId(resultado.getLong("id"));
			usuarioModel.setLogin(resultado.getString("login"));
			usuarioModel.setSenha(resultado.getString("senha"));
			usuarioModel.setNome(resultado.getString("nome"));
			//usuarioModel.setTelefone(resultado.getString("telefone"));
			
			usuarioModel.setCep(resultado.getString("cep"));
			usuarioModel.setRua(resultado.getString("rua"));
			usuarioModel.setBairro(resultado.getString("bairro"));
			usuarioModel.setCidade(resultado.getString("cidade"));
			usuarioModel.setEstado(resultado.getString("estado"));
			usuarioModel.setIbge(resultado.getString("ibge"));
			usuarioModel.setAtivo(resultado.getBoolean("isEnabled"));
			usuarioModel.setSexo(resultado.getString("sexo"));
			usuarioModel.setPerfil(resultado.getString("perfil"));

			usuarioModel.setFotoNome(resultado.getString("fotoNome"));
			//usuarioModel.setFotoBase64(resultado.getString("fotoBase64"));
			usuarioModel.setFotoBase64miniatura(resultado.getString("fotoBase64miniatura")); //nulo
			usuarioModel.setContentType(resultado.getString("contentType"));

			usuarioModel.setDocumentoNome(resultado.getString("documentoNome"));
			usuarioModel.setDocumentoBase64(resultado.getString("documentoBase64"));
			usuarioModel.setContentTypeDocumento(resultado.getString("contentTypeDocumento"));

			
			listar.add(usuarioModel);
		}

		return listar;
	}
	
	public void salvar(UsuarioModel usuario) throws Exception {

		try {
			String table_name = "usuarios";
			String sql = "";

			
			// sql += "INSERT INTO " + table_name + " (login, senha) ";
			// sql += " VALUES ('" + usuario.getLogin() + "','" + usuario.getSenha() + "')";

			//sql += "INSERT INTO " + table_name + " (login, senha) VALUES (?, ?)";
			//sql += "INSERT INTO " + table_name + " (login, senha, nome) VALUES (?, ?, ?)";
			//sql += "INSERT INTO " + table_name + " (login, senha, nome, telefone) VALUES (?, ?, ?, ?)";
			//sql += "INSERT INTO " + table_name + " (login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			//sql += "INSERT INTO " + table_name + " (login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotoBase64) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			//sql += "INSERT INTO " + table_name + " (login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotoBase64, contentType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			sql += "INSERT INTO " + table_name + " (";
			/*sql += "login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, sexo";*/
			sql += "login, senha, nome, cep, rua, bairro, cidade, estado, ibge, sexo, perfil";
			sql += ", isEnabled";
			sql += ", fotoNome";
			sql += ", fotoBase64";
			sql += ", contentType";
			sql += ", documentoNome";
			sql += ", documentoBase64";
			sql += ", contentTypeDocumento";
			sql += ", fotoBase64miniatura";
			sql += ", createdOn";
			sql += ", modifiedOn";
			/*sql += ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?";*/
			sql += ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", ?";
			sql += ", SYSDATE()";
			sql += ", SYSDATE()";
			sql += ")";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			/*statement.setString(4, usuario.getTelefone());*/
			
			statement.setString(4, usuario.getCep());
			statement.setString(5, usuario.getRua());
			statement.setString(6, usuario.getBairro());
			statement.setString(7, usuario.getCidade());
			statement.setString(8, usuario.getEstado());
			statement.setString(9, usuario.getIbge());
			statement.setString(10, usuario.getSexo());
			statement.setString(11, usuario.getPerfil());
			statement.setBoolean(12, usuario.isAtivo());

			statement.setString(13, usuario.getFotoNome());
			statement.setString(14, usuario.getFotoBase64());
			statement.setString(15, usuario.getContentType());
			
			statement.setString(16, usuario.getDocumentoNome());
			statement.setString(17, usuario.getDocumentoBase64());
			statement.setString(18, usuario.getContentTypeDocumento());
			
			statement.setString(19, usuario.getFotoBase64miniatura());
			
			statement.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	
	public void atualizar(UsuarioModel usuario) {

		try {
			
			StringBuilder sql = new StringBuilder();
			
			String table_name = "usuarios";
			//String sql = "";
			
			
			//String sql = "UPDATE " + table_name + " SET login = ?,  senha = ? WHERE id = '" + usuario.getId() + "'";
			//String sql = "UPDATE " + table_name + " SET login = ?,  senha = ?, nome = ? WHERE id = '" + usuario.getId() + "'";
			//String sql = "UPDATE " + table_name + " SET login = ?,  senha = ?, nome = ?, telefone = ? WHERE id = '" + usuario.getId() + "'";
			//String sql = "UPDATE " + table_name + " SET login = ?,  senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ? WHERE id = '" + usuario.getId() + "'";
			//sql += "UPDATE " + table_name + " SET login = ?,  senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?";
			sql.append("UPDATE " + table_name + " SET ");
			sql.append(" login = ? ");
			sql.append(", senha = ?");
			sql.append(", nome = ?");
			//sql.append(", telefone = ?");
			sql.append(", cep = ?");
			sql.append(", rua = ?");
			sql.append(", bairro = ?");
			sql.append(", cidade = ?");
			sql.append(", estado = ?");
			sql.append(", ibge = ?");
			sql.append(", isEnabled = ?");
			sql.append(", sexo = ?");
			sql.append(", perfil = ?");
			
			if (usuario.isAtualizarImg()) {
				sql.append(", fotoNome = ?");
				sql.append(", fotoBase64 = ?");
				sql.append(", contentType = ?");
				sql.append(", fotoBase64miniatura = ?");
			}

			if (usuario.isAtualizarDoc()) {
				sql.append(", documentoNome = ?");
				sql.append(", documentoBase64 = ?");
				sql.append(", contentTypeDocumento = ?");
			}

			sql.append(", modifiedOn = SYSDATE()");

			sql.append(" WHERE id = '" + usuario.getId() + "'");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usuario.getLogin()); // posicao 1, parâmetro de atualização
			statement.setString(2, usuario.getSenha()); // posicao 2, parâmetro de atualização
			statement.setString(3, usuario.getNome()); // posicao 3, parâmetro de atualização
			// statement.setString(4, usuario.getTelefone()); // posicao 4, parâmetro de atualização

			statement.setString(4, usuario.getCep());
			statement.setString(5, usuario.getRua());
			statement.setString(6, usuario.getBairro());
			statement.setString(7, usuario.getCidade());
			statement.setString(8, usuario.getEstado());
			statement.setString(9, usuario.getIbge());
			statement.setBoolean(10, usuario.isAtivo());
			statement.setString(11, usuario.getSexo());
			statement.setString(12, usuario.getPerfil());
			
			if (usuario.isAtualizarImg()) {
				statement.setString(13, usuario.getFotoNome());
				statement.setString(14, usuario.getFotoBase64());
				statement.setString(15, usuario.getContentType());
				statement.setString(16, usuario.getFotoBase64miniatura());
			}

			// se atualizar imagem e documento
			if (usuario.isAtualizarImg() && usuario.isAtualizarDoc()) {
				statement.setString(17, usuario.getDocumentoNome());
				statement.setString(18, usuario.getDocumentoBase64());
				statement.setString(19, usuario.getContentTypeDocumento());
			}

			// se atualizar somente documento
			if (!usuario.isAtualizarImg() && usuario.isAtualizarDoc()) {
				statement.setString(13, usuario.getDocumentoNome());
				statement.setString(14, usuario.getDocumentoBase64());
				statement.setString(15, usuario.getContentTypeDocumento());
			}

			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//public void delete(String login) {
	public void delete(String id) {

		try {
			String table_name = "usuarios";
			String sql = "";
	
			sql += "DELETE FROM " + table_name;
			//sql += " WHERE login = '" + login + "'";
			sql += " WHERE id = '" + id + "'";
			sql += " AND id != 1"; //id = 1 -> usuário admin
	
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
	
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public UsuarioModel consultar(String id) throws Exception {
		
		String table_name = "usuarios";
		String sql = "";

		sql += "SELECT * FROM " + table_name;
		//sql += " WHERE login = '" + login + "'";
		sql += " WHERE id = '" + id + "'";
		sql += " AND id != 1"; //id = 1 -> usuário admin
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		//"resultado.next()" valida se o registro existe
		if(resultado.next()) {
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setId(resultado.getLong("id"));
			usuarioModel.setLogin(resultado.getString("login"));
			usuarioModel.setSenha(resultado.getString("senha"));
			usuarioModel.setNome(resultado.getString("nome"));
			//usuarioModel.setTelefone(resultado.getString("telefone"));

			usuarioModel.setCep(resultado.getString("cep"));
			usuarioModel.setRua(resultado.getString("rua"));
			usuarioModel.setBairro(resultado.getString("bairro"));
			usuarioModel.setCidade(resultado.getString("cidade"));
			usuarioModel.setEstado(resultado.getString("estado"));
			usuarioModel.setIbge(resultado.getString("ibge"));
			usuarioModel.setSexo(resultado.getString("sexo"));
			usuarioModel.setPerfil(resultado.getString("perfil"));
			
			usuarioModel.setAtivo(resultado.getBoolean("isEnabled"));

			usuarioModel.setFotoNome(resultado.getString("fotoNome"));
			usuarioModel.setFotoBase64(resultado.getString("fotoBase64"));
			usuarioModel.setContentType(resultado.getString("contentType"));

			usuarioModel.setDocumentoNome(resultado.getString("documentoNome"));
			usuarioModel.setDocumentoBase64(resultado.getString("documentoBase64"));
			usuarioModel.setContentTypeDocumento(resultado.getString("contentTypeDocumento"));

			usuarioModel.setFotoBase64miniatura(resultado.getString("fotoBase64miniatura"));
			
			return usuarioModel;
		}
		
		return null;
	}

	public boolean validarLogin(String login) throws Exception {
		
		String table_name = "usuarios";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE login = '" + login + "'";
		//sql += " WHERE id = '" + id + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		//"resultado.next()" valida se o registro existe
		if(resultado.next()) {
			/*
			BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
			beanCursoJSP.setId(resultado.getLong("id"));
			beanCursoJSP.setLogin(resultado.getString("login"));
			beanCursoJSP.setSenha(resultado.getString("senha"));
			beanCursoJSP.setNome(resultado.getString("nome"));
			*/
			
			//return beanCursoJSP;
			
			//"qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /*Return true*/
		}
		
		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		
		String table_name = "usuarios";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE login = '" + login + "'";
		sql += " AND id != '" + id + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		//"resultado.next()" valida se o registro existe
		if(resultado.next()) {
			//"qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /*Return true*/
		}
		
		return false;
	}
	
	//public BeanCursoJSP consultar(String id) throws Exception {
		public boolean validarSenha(String senha) throws Exception {
			
			String table_name = "usuarios";
			String sql = "";

			sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
			sql += " WHERE senha = '" + senha + "'";
			//sql += " WHERE id = '" + id + "'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			//"resultado.next()" valida se o registro existe
			if(resultado.next()) {
				/*
				BeanCursoJSP beanCursoJSP = new BeanCursoJSP();
				beanCursoJSP.setId(resultado.getLong("id"));
				beanCursoJSP.setLogin(resultado.getString("login"));
				beanCursoJSP.setSenha(resultado.getString("senha"));
				beanCursoJSP.setNome(resultado.getString("nome"));
				*/
				
				//return beanCursoJSP;
				
				//"qtd" é o alias da instrução SQL
				return resultado.getInt("qtd") <= 0; /*Return true*/
			}
			
			return false;
		}

		public boolean validarSenhaUpdate(String senha, String id) throws Exception {
			
			String table_name = "usuarios";
			String sql = "";

			sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
			sql += " WHERE senha = '" + senha + "'";
			sql += " AND id != '" + id + "'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			//"resultado.next()" valida se o registro existe
			if(resultado.next()) {
				//"qtd" é o alias da instrução SQL
				return resultado.getInt("qtd") <= 0; /*Return true*/
			}
			
			return false;
		}

}
