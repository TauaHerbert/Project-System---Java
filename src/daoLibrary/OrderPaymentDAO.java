package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelsLibrary.OrderPayment;
import modelsLibrary.PaymentMethod;
public class OrderPaymentDAO {
	public void insertPedidoPagamento(Connection conn, int idPedido, List<OrderPayment> pagamentos) throws SQLException {
		String sql = "INSERT INTO pedido_pagamento (id_pedido,id_pagamento,valor_pago) VALUES (?,?,?)";
		
		try (PreparedStatement stm = conn.prepareStatement(sql)) {
			for (OrderPayment pagamento : pagamentos) {
				stm.setInt(1, idPedido);
				stm.setInt(2, pagamento.getId_pagamento());
				stm.setBigDecimal(3, pagamento.getValor_pago());
				
				stm.executeUpdate();
			}
		}
	}
	
	public List<OrderPayment> selectFormaPagamentoPedido (int idPedido) {
		String sql = "SELECT pedido_pagamento.id_pagamento, pedido_pagamento.valor_pago, "
				+ "       forma_pagamento.descricao "
				+ "FROM pedido_pagamento "
				+ "INNER JOIN forma_pagamento ON pedido_pagamento.id_pagamento = forma_pagamento.id_pagamento "
				+ "WHERE pedido_pagamento.id_pedido = ?";
		
		List<OrderPayment> pagamentos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, idPedido);
			
			try(ResultSet resul = stm.executeQuery()) {
				
				while (resul.next()) {
					OrderPayment pedidoPagamento = new OrderPayment();
					pedidoPagamento.setId_pagamento(resul.getInt("id_pagamento"));
					pedidoPagamento.setValor_pago(resul.getBigDecimal("valor_pago"));
		
					PaymentMethod metodoPagamento = new PaymentMethod();
					metodoPagamento.setDescricao(resul.getString("descricao"));
					
					pedidoPagamento.setMetodoPagamento(metodoPagamento);
					
					pagamentos.add(pedidoPagamento);
				}
				
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao buscar Pagamentos do pedido! Erro: "+ex.getMessage(), ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
		
		return pagamentos;
	}
}
