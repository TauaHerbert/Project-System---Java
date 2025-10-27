package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.swing.JComboBox;

import modelsLibrary.PaymentMethod;

public class PaymentMethodDAO {
	
	public void insertFormaPagamento(PaymentMethod paym) {
		String sql = "INSERT INTO forma_pagamento (descricao) VALUES (?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, paym.getDescricao());
			stm.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir forma de pagamento. Error: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public List<PaymentMethod> selectFormaPagamento(){
		String sql = "SELECT * FROM forma_pagamento";
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			while (resul.next()) {
				PaymentMethod paymentMethod = new PaymentMethod();
				paymentMethod.setIdFormaPagamento(resul.getInt("id_pagamento"));
				paymentMethod.setDescricao(resul.getString("descricao"));
				
				paymentMethods.add(paymentMethod);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao listar formas de pagamento. Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		return paymentMethods;
	}
	
	public static void comboCategorias(JComboBox<PaymentMethod> combobox) {
		String sql = "SELECT id_pagamento, descricao FROM forma_pagamento";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			combobox.removeAllItems();
			
			while(resul.next()) {
				int idFormaPagamento = resul.getInt("id_pagamento");
				String descricao = resul.getString("descricao");
				combobox.addItem(new PaymentMethod(idFormaPagamento, descricao));
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao listar formas de pagamentos no combobox. Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
	}
	
    public void updateFormaPagamento(PaymentMethod paym) {
    	String sql = "UPDATE forma_pagamento SET descricao = ? WHERE id_pagamento = ?";
    	
    	Connection conn = null;
    	PreparedStatement stm = null;
    	
    	try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, paym.getDescricao());
			stm.setInt(2, paym.getIdFormaPagamento());
			
			stm.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao atualizar forma de pagamento! Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
    }

}
