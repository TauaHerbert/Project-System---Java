package daoLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelsLibrary.Category;

public class CategoryDAO {
	
	public void insertCategoria(Category categoria) {

		String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, categoria.getNome());
			stm.setString(2, categoria.getDescricao());
			
			stm.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir a categoria. SQL: "+ex.getMessage()+" Erro: "+ ex);
		} finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public List<Category> selectAllCategoria(){
		
		String sql = "SELECT * FROM categoria";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		List<Category> categorias = new ArrayList<Category>();
		
		try {
			
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			while (resul.next()) {
				Category categoria = new Category();
				
				categoria.setIdCategoria(resul.getInt("idCategoria"));
				categoria.setNome(resul.getString("nome"));
				categoria.setDescricao(resul.getString("descricao"));
				
				categorias.add(categoria);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao Listar Categorias. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		
		return categorias;
	}
	
	public void updateCategoria(Category categoria) {
		String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE idCategoria = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, categoria.getNome());
			stm.setString(2, categoria.getDescricao());
			stm.setInt(3, categoria.getIdCategoria());
			
			stm.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar categoria. Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}

}
