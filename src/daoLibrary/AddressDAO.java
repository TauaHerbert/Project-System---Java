package daoLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import modelsLibrary.Address;

public class AddressDAO {
	public void insertEndereco(Address endereco) {
		String sql = "INSERT INTO endereco (rua,numero,bairro,cidade,estado,cep,idCliente) VALUES (?,?,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, endereco.getRua());
			stm.setString(2, endereco.getNumero());
			stm.setString(3, endereco.getBairro());
			stm.setString(4, endereco.getCidade());
			stm.setString(5, endereco.getEstado());
			stm.setString(6, endereco.getCep());
			stm.setInt(7, endereco.getIdCliente());
			
			stm.executeUpdate();
			
			
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir Endereço. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public Address selectEndereco (int idClient) {
		String sql = "SELECT * FROM endereco WHERE idCliente = ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		Address address = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, idClient);
			resul = stm.executeQuery();
			
			while (resul.next()) {
				address = new Address();
				
				address.setIdEndereco(resul.getInt("idEndereco"));
				address.setRua(resul.getString("rua"));
				address.setNumero(resul.getString("numero"));
				address.setBairro(resul.getString("bairro"));
				address.setCidade(resul.getString("cidade"));
				address.setEstado(resul.getString("estado"));
				address.setCep(resul.getString("cep"));
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao buscar endereço do cliente. Erro: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		
		return address;
	}

}
