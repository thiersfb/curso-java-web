package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	
	public boolean validarLogin(String login, String senha) throws Exception {
		
		
		/* EXEMPLO
		
		String sql = " SELECT ";
		sql += " us.nome "; // "sql += <texto>" significa o mesmo que " sql = sql + <texto>"; efetua
							// concatenação
		sql += " , us.email ";
		sql += " , tel.numero ";
		sql += " FROM userposjava us ";
		sql += " INNER JOIN telefoneuser tel ON us.id = tel.usuarioPessoa ";
		sql += " WHERE us.id = " + idUser;
		
		*/
		String table_name = "usuarios";
		String sql = "SELECT * FROM " + table_name + " WHERE login = '" + login + "' AND senha = '" + senha + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true;	//possui usuário
		} else {
			return false; 	//não validou usuário
		}
		
	}

}
