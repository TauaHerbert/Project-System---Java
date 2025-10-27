package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import daoLibrary.DBConnection;

public class ItemCategory {
	private int id;
	private String nome;
	
	public ItemCategory(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	public static void comboCategorias(JComboBox<ItemCategory> combobox) {
		String sql = "SELECT idCategoria, nome FROM categoria";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			combobox.removeAllItems();
			
			while(resul.next()) {
				int id = resul.getInt("idCategoria");
				String nome = resul.getString("nome");
				combobox.addItem(new ItemCategory(id, nome));
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao carregar dados para o combobox. SQL: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
	}

}
