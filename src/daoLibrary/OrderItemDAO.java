package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelsLibrary.OrderItem;
import modelsLibrary.Product;

public class OrderItemDAO {
	public void insertItemPedido(Connection conn, int idPedido, List<OrderItem> itensP) throws SQLException {
		String sql = "INSERT INTO item_Pedido (quantidade,preco_unitario,id_pedido,id_produto) VALUES (?,?,?,?)";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			for (OrderItem itemP : itensP) {
				stm.setInt(1, itemP.getQuantidade());
				stm.setBigDecimal(2, itemP.getPrecoUnitario());
				stm.setInt(3, idPedido);
				stm.setInt(4, itemP.getIdProduto());
				
				stm.executeUpdate();
			}
		}
	}
	
	public List<OrderItem> selectItemPedido(int idPedido) {
		String str = "SELECT item_pedido.id_item, item_pedido.quantidade, item_pedido.preco_unitario, "
				+ "       produto.idProduto, produto.nome, produto.descricao "
				+ "FROM item_pedido "
				+ "INNER JOIN produto ON item_pedido.id_produto = produto.idProduto "
				+ "WHERE item_pedido.id_pedido = ?";
		
		List<OrderItem> itens = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(str);
			
			stm.setInt(1, idPedido);
			
			try (ResultSet resul = stm.executeQuery()){
				while (resul.next()) {
					OrderItem itemPedido = new OrderItem();
					itemPedido.setIdItem(resul.getInt("id_item"));
					itemPedido.setQuantidade(resul.getInt("quantidade"));
					itemPedido.setPrecoUnitario(resul.getBigDecimal("preco_unitario"));
					
					Product produto = new Product();
					produto.setIdProduto(resul.getInt("idProduto"));
					produto.setNome(resul.getString("nome"));
					produto.setDescricao(resul.getString("descricao"));
					
					itemPedido.setProduto(produto);
					
					itens.add(itemPedido);
				}
				
				DBConnection.CloseResultSet(resul);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao buscar itens do pedido pelo ID do pedido. ERRO: "+ex.getMessage(), ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
		return itens;
	}
}
