package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
//import models.UsuarioModel;
import models.ProdutoModel;
import models.UsuarioTelefoneModel;

public class DaoUsuarioTelefone {
	private Connection connection;

	public DaoUsuarioTelefone() {
		connection = SingleConnection.getConnection();
	}

	public List<UsuarioTelefoneModel> listar(Long user) throws Exception {
		List<UsuarioTelefoneModel> listar = new ArrayList<UsuarioTelefoneModel>();

		String table_name = "usuario_telefones";
		String sql = "";

		sql += "SELECT * FROM " + table_name + " WHERE usuarioID = " + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			UsuarioTelefoneModel usuarioTelefoneModelModel = new UsuarioTelefoneModel();
			
			usuarioTelefoneModelModel.setId(resultado.getLong("id"));
			usuarioTelefoneModelModel.setNumero(resultado.getString("numero"));
			usuarioTelefoneModelModel.setTipo(resultado.getString("tipo"));
			usuarioTelefoneModelModel.setUsuario(resultado.getLong("usuarioID")); //"usuarioID" refere-se ao nome da coluna da tabela na base de dados
			//usuarioTelefoneModelModel.setUsuario(resultado.getLong("nomeUsuario")); 
			

			listar.add(usuarioTelefoneModelModel);
		}

		return listar;

	}

	public void salvar(UsuarioTelefoneModel telefone) throws Exception {

		try {
			String table_name = "usuario_telefones";
			String sql = "";
			
			sql += "INSERT INTO " + table_name + " (numero, tipo, usuarioID) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());

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

	//não será necessário, bastando apenas apagar e cadastrar novamente o numero
	/*
	public void atualizar(ProdutoModel produto) {

		try {
			String table_name = "usuario_telefones";
			String sql = "UPDATE " + table_name;
					sql += " SET nome = ?,  descricao = ?, preco = ?, qtdEstoque = ? ";
					sql += " WHERE id = '" + produto.getId() + "'";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, produto.getNome()); 		// posicao 1, parâmetro de atualização
			statement.setString(2, produto.getDescricao()); // posicao 2, parâmetro de atualização
			statement.setBigDecimal(3, produto.getPreco()); // posicao 3, parâmetro de atualização
			statement.setInt(4, produto.getQtdEstoque()); 	// posicao 4, parâmetro de atualização

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
	*/

	// public void delete(String login) {
	public void delete(String id) {

		try {
			String table_name = "usuario_telefones";
			String sql = "";

			sql += "DELETE FROM " + table_name;
			// sql += " WHERE login = '" + login + "'";
			sql += " WHERE id = '" + id + "'";

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

	/*
	public ProdutoModel consultar(String id) throws Exception {

		String table_name = "usuario_telefones";
		String sql = "";

		sql += "SELECT * FROM " + table_name;
		// sql += " WHERE login = '" + login + "'";
		sql += " WHERE id = '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {
			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setId(resultado.getLong("id"));
			produtoModel.setNome(resultado.getString("nome"));
			produtoModel.setDescricao(resultado.getString("descricao"));
			produtoModel.setPreco(resultado.getBigDecimal("preco"));
			produtoModel.setQtdEstoque(resultado.getInt("qtdEstoque"));

			return produtoModel;
		}

		return null;
	}
	*/

	/*
	public boolean validarNome(String nome) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE nome = '" + nome + "'";
		// sql += " WHERE id = '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {

			// "qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; // Return true
		}

		return false;
	}
	*/


}
