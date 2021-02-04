package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
 * @author thiers
 *
 */
public class SingleConnection {
	
	private static String urlBanco = "jdbc:mysql://localhost:3306/curso?autoReconnect=true";
	private static String user  = "root";
	private static String password  = "root1234";
	private static Connection connection = null;
	
	//a partir do momento que esta classe for chamada, este metodo garantirá que a conexão esteja ativa
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	public static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				//Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(urlBanco, user, password);
				connection.setAutoCommit(false);
			//} else {
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	//metodo que retorna a conexão
	public static Connection getConnection() {
		return connection;
	}
	
	
}
