package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelsLibrary.Product;

public class ProductDAO {
	public void insertProduto(Product produto) {
		String sql = "INSERT INTO produto (nome,descricao,preco,idCategoria) VALUES (?,?,?,?)";
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.setBigDecimal(3, produto.getPreco());
			stm.setInt(4, produto.getIdCategoria());
			
			stm.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir o Produto. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public List<Product> selectAllProduto(){
		String sql = "SELECT * FROM produto";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		List<Product> produtos = new ArrayList<Product>();
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			while (resul.next()) {
				
				Product produto = new Product();
				
				produto.setIdProduto(resul.getInt("idProduto"));
				produto.setNome(resul.getString("nome"));
				produto.setDescricao(resul.getString("descricao"));
				produto.setPreco(resul.getBigDecimal("preco"));
				produto.setIdCategoria(resul.getInt("idCategoria"));
				
				produtos.add(produto);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao Listar Produtos. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		
		return produtos;
	}
	
	public List<Product> listarProdutosCategoria(int idCategoria){
		String sql = "SELECT idProduto,nome,descricao,preco FROM produto WHERE idCategoria = ? ORDER BY nome ASC";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		List<Product> produtos = new ArrayList<Product>();
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, idCategoria);
			resul = stm.executeQuery();
			
			while(resul.next()) {
				Product produto = new Product();
				
				produto.setIdProduto(resul.getInt("idProduto"));
				produto.setNome(resul.getString("nome"));
				produto.setDescricao(resul.getString("descricao"));
				produto.setPreco(resul.getBigDecimal("preco"));
				
				produtos.add(produto);
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao listar Produtos com base na Categoria. Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		return produtos;
	}
	
	public void updateProduto(Product produto) {
		String sql = "UPDATE produto SET nome = ?, descricao = ?, preco = ?, idCategoria = ? WHERE idProduto = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.setBigDecimal(3, produto.getPreco());
			stm.setInt(4, produto.getIdCategoria());
			stm.setInt(5, produto.getIdProduto());
			
			stm.executeUpdate();
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar o Produto. Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public void deleteProduto (Product produto) {
		String sql = "DELETE FROM produto WHERE idProduto = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, produto.getIdProduto());
			
			stm.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar Produto. Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}

}
