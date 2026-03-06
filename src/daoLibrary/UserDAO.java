package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelsLibrary.User;

public class UserDAO {
	/**
	 * Cadastrando um novo úsuario no banco de dados.
	 * @param user
	 */
	public void insertUser(User user) {
		String sql = "INSERT INTO usuario (login, senha) VALUES (?,?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, user.getLogin());
			stm.setString(2, user.getSenha());
			
			stm.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir Úsuario. Error: "+e.getMessage());
		}finally{
			DBConnection.CloseConnection(conn, stm);
		}
	}

	/**
	 * Verifica se a senha master é verdadeira. 
	 * @param senhaMaster
	 * @return
	 */
	public boolean validarSenhaMaster(String senhaMaster) {
		 
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
	    String sql = "SELECT senha FROM usuario WHERE login = 'system'";
	    
	    try {
	    	 conn = DBConnection.getConnection();
	         stm = conn.prepareStatement(sql);
	         rs = stm.executeQuery();
	        
	        if (rs.next()) {
	            String hashNoBanco = rs.getString("senha");
	            return senhaMaster.equals(hashNoBanco);
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Erro ao validar senha mestre: " + e.getMessage());
	    }finally {
	    	DBConnection.CloseConnection(conn, stm, rs);
	    }
	    return false;
	}
}
