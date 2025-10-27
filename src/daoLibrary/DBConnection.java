package daoLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/banco_test";
	public static final String USER = "root";
	public static final String PASS = "t@uaxddemysql1997";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (Exception ex) {
			throw new RuntimeException("Erro de conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	
	public static void CloseConnection (Connection conn){
		try {
			if (conn != null ) {
				conn.close();
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	public static void CloseConnection (Connection conn, PreparedStatement stmt) {
		try {
			if (conn != null) {
				conn.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	public static void CloseConnection (Connection conn, PreparedStatement stmt, ResultSet rslt) {
		try {
			
			if (conn != null) conn.close();
			if (stmt != null) stmt.close();
			if (rslt != null) rslt.close();
		
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	public static void CloseConnection (Connection conn, PreparedStatement stmt1, PreparedStatement stmt2, ResultSet rslt) {
		try {
			
			if (conn != null) conn.close();
			if (stmt1 != null) stmt1.close();
			if (stmt2 != null) stmt2.close();
			if (rslt != null) rslt.close();
		
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	public static void CloseResultSet (ResultSet rslt) {
		try {
			
			if (rslt != null) rslt.close();
		
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao fechar o ResultSet: "+ex.getMessage(), ex);
		}
	}

}
