package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import models.CategoriaProdutoModel;
//import models.UsuarioModel;
import models.ProdutoModel;

public class DaoProduto {
	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public List<ProdutoModel> listar() throws Exception {
		List<ProdutoModel> listar = new ArrayList<ProdutoModel>();

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT * FROM " + table_name;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			ProdutoModel produtoModel = new ProdutoModel();
			produtoModel.setId(resultado.getLong("id"));
			produtoModel.setNome(resultado.getString("nome"));
			produtoModel.setDescricao(resultado.getString("descricao"));
			produtoModel.setPreco(resultado.getBigDecimal("preco"));
			produtoModel.setQtdEstoque(resultado.getInt("qtdEstoque"));
			produtoModel.setCategoria_id(resultado.getLong("categoria_id"));

			listar.add(produtoModel);
		}

		return listar;

	}
	
	public List<CategoriaProdutoModel> listarCategoriaProduto() throws Exception {
		List<CategoriaProdutoModel> listarCategorias = new ArrayList<CategoriaProdutoModel>();

		String table_name = "produto_categorias";
		String sql = "";

		sql += "SELECT * FROM " + table_name + " ORDER BY descricao";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			CategoriaProdutoModel categoriaProdutoModel = new CategoriaProdutoModel();
			
			categoriaProdutoModel.setId(resultado.getLong("id"));
			categoriaProdutoModel.setDescricao(resultado.getString("descricao"));

			listarCategorias.add(categoriaProdutoModel);
		}

		return listarCategorias;

	}

	public void salvar(ProdutoModel produto) throws Exception {

		try {
			String table_name = "produtos";
			String sql = "";

			sql += "INSERT INTO " + table_name + " (nome";
			sql += ", descricao";
			sql += ", preco";
			sql += ", qtdEstoque";
			sql += ", categoria_id";
			sql += ") VALUES (";
			sql += "?, ";
			sql += "?, ";
			sql += "?, ";
			sql += "?, ";
			sql += "?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.setBigDecimal(3, produto.getPreco());
			statement.setInt(4, produto.getQtdEstoque());
			statement.setLong(5, produto.getCategoria_id());
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

	public void atualizar(ProdutoModel produto) {

		try {
			String table_name = "produtos";
			String sql = "UPDATE " + table_name;
					sql += " SET nome = ?";
					sql += ",  descricao = ?";
					sql += ", preco = ?";
					sql += ", qtdEstoque = ? ";
					sql += ", categoria_id = ? ";
					sql += " WHERE id = '" + produto.getId() + "'";

			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, produto.getNome()); 			// posicao 1, parâmetro de atualização
			statement.setString(2, produto.getDescricao()); 	// posicao 2, parâmetro de atualização
			statement.setBigDecimal(3, produto.getPreco()); 	// posicao 3, parâmetro de atualização
			statement.setInt(4, produto.getQtdEstoque()); 		// posicao 4, parâmetro de atualização
			statement.setLong(5, produto.getCategoria_id()); 	// posicao 5, parâmetro de atualização

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

	public void delete(String id) {

		try {
			String table_name = "produtos";
			String sql = "";

			sql += "DELETE FROM " + table_name;
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

	public ProdutoModel consultar(String id) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT * FROM " + table_name;
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
			produtoModel.setCategoria_id(resultado.getLong("categoria_id"));

			return produtoModel;
		}

		return null;
	}

	public boolean validarNome(String nome) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE nome = '" + nome + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {

			// "qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

	public boolean validarNomeUpdate(String nome, String id) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE nome = '" + nome + "'";
		sql += " AND id != '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {
			// "qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

	public boolean validarDescricao(String descricao) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE descricao = '" + descricao + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {

			// "qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

	public boolean validarDescricaoUpdate(String descricao, String id) throws Exception {

		String table_name = "produtos";
		String sql = "";

		sql += "SELECT COUNT(1) AS qtd FROM " + table_name;
		sql += " WHERE descricao = '" + descricao + "'";
		sql += " AND id != '" + id + "'";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		// "resultado.next()" valida se o registro existe
		if (resultado.next()) {
			// "qtd" é o alias da instrução SQL
			return resultado.getInt("qtd") <= 0; /* Return true */
		}

		return false;
	}

}
