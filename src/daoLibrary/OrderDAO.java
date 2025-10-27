package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelsLibrary.Client;
import modelsLibrary.Order;

public class OrderDAO {
	public int insertPedido(Connection conn, Order pedido) throws SQLException {
		int idPedGerado = 0;
		String sql = "INSERT INTO pedido (data,total,idCliente) VALUES (?,?,?)";
		
		try(PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stm.setTimestamp(1, Timestamp.valueOf(pedido.getData()));
			stm.setBigDecimal(2, pedido.getTotal());
			stm.setInt(3, pedido.getIdCliente());
			
			stm.executeUpdate();
			
			try (ResultSet resul = stm.getGeneratedKeys()){
				if (resul.next()) {
					idPedGerado = resul.getInt(1);
				}
			}
		}
		return idPedGerado;
	}
	
	public List<Order> selectPedidoCliente(LocalDate data) {
		List<Order> itensPedido = new ArrayList<Order>();
		
		String sql = "SELECT pedido.id_pedido, pedido.data, cliente.nome, pedido.total "
				+ "FROM pedido INNER JOIN cliente ON pedido.idCliente = cliente.idCliente "
				+ "WHERE DATE(pedido.data) = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setDate(1, java.sql.Date.valueOf(data));
			
			try (ResultSet resul = stm.executeQuery()){
				while (resul.next()) {
					Order pedido = new Order();
					pedido.setIdPedido(resul.getInt("id_pedido"));
					pedido.setData(resul.getTimestamp("data").toLocalDateTime());
					pedido.setTotal(resul.getBigDecimal("total"));
					pedido.setNomeC(resul.getString("nome"));
					
					itensPedido.add(pedido);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao listar pedidos pela busca por Data! Erro: "+ex.getMessage(), ex);
		}
		return itensPedido;
	}
	
	public Order selectPdidoId(int id_Pedido) {
		String sql = "SELECT pedido.id_pedido, pedido.data, pedido.total, "
				+ "cliente.idCliente, cliente.nome, cliente.cpf, cliente.email, cliente.telefone "
				+ "FROM pedido "
				+ "INNER JOIN cliente ON pedido.idCliente = cliente.idCliente "
				+ "WHERE pedido.id_pedido = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, id_Pedido);
			
			try(ResultSet resul = stm.executeQuery()) {
				if (resul.next()) {
					Order pedido = new Order();
					pedido.setIdPedido(resul.getInt("id_Pedido"));
					pedido.setData(resul.getTimestamp("data").toLocalDateTime());
					pedido.setTotal(resul.getBigDecimal("total"));
					
					Client cliente = new Client();
					cliente.setIdCliente(resul.getInt("idCliente"));
					cliente.setNome(resul.getString("nome"));
					cliente.setEmail(resul.getString("email"));
					cliente.setTelefone(resul.getString("telefone"));
					cliente.setCpf(resul.getString("cpf"));
					
					pedido.setCliente(cliente);
					
					DBConnection.CloseResultSet(resul);
					
					return pedido;
				}
			} 
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao buscar pedido por ID! ERRO: "+ex.getMessage(), ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
		return null;
	}
}
