package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import daoLibrary.DBConnection;
import daoLibrary.OrderDAO;
import daoLibrary.OrderItemDAO;
import daoLibrary.OrderPaymentDAO;
import modelsLibrary.Order;
import modelsLibrary.OrderItem;
import modelsLibrary.OrderPayment;

public class ServiceOrder {
	
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	private OrderPaymentDAO orderPaymentDAO;
	
	public ServiceOrder() {
		this.orderDAO = new OrderDAO();
		this.orderItemDAO = new OrderItemDAO();
		this.orderPaymentDAO = new OrderPaymentDAO();
	}
	
	public int finalizarPedido(Order pedido, List<OrderItem> itens, List<OrderPayment> pagamentos) {
		int idPedido = 0;
		Connection conn = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			idPedido = orderDAO.insertPedido(conn, pedido);
			
			if (itens != null && !itens.isEmpty()) {
				for (OrderItem item : itens) {
					item.setIdPedido(idPedido);
				}
				
				orderItemDAO.insertItemPedido(conn, idPedido, itens);
			}
			
			if (pagamentos != null && !pagamentos.isEmpty()) {
				orderPaymentDAO.insertPedidoPagamento(conn, idPedido, pagamentos);
			}
			
			conn.commit();
			
		} catch (SQLException ex) {
			if (conn != null) {
				try {
					conn.rollback();
					MessageBox.messageShow("Transação revertida devido a erro: "+ex.getMessage());
				} catch (SQLException rollbackEX) {
					rollbackEX.printStackTrace();
				}
			}
			throw new RuntimeException("Erro ao finalizar pedido: "+ex.getMessage(), ex);
		}finally {
			DBConnection.CloseConnection(conn);
		}
		return idPedido;
	}
}
