package daoLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelsLibrary.Client;

public class ClientDAO {
	public void insertCliente(Client cliente) {
		String sql = "INSERT INTO cliente (nome,email,telefone,cpf) VALUES (?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getTelefone());
			stm.setString(4, cliente.getCpf());
			
			stm.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao inserir Cliente. SQL: "+ex.getMessage()+" Erro: "+ex); 		
		}finally {
			DBConnection.CloseConnection(conn, stm);
		}
	}
	
	public List<Client> selectAllCliente(){
		String sql = "SELECT * FROM cliente ORDER BY nome ASC";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		List<Client> clientes = new ArrayList<Client>();
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			resul = stm.executeQuery();
			
			while (resul.next()) {
				
				Client cliente = new Client();
				
				cliente.setIdCliente(resul.getInt("idCliente"));
				cliente.setNome(resul.getString("nome"));
				cliente.setEmail(resul.getString("email"));
				cliente.setTelefone(resul.getString("telefone"));
				cliente.setCpf(resul.getString("cpf"));
				
				clientes.add(cliente);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao Listar Clientes. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		
		return clientes;
	}
	
	public List<Client> listClientBuscName (String name){
		
		List<Client> listClients = new ArrayList<Client>();
		String sql = "SELECT * FROM cliente WHERE nome LIKE ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet resul = null;
		
		try {
			conn = DBConnection.getConnection();
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, name + "%");
			resul = stm.executeQuery();
			
			while (resul.next()) {
				Client cliente = new Client();
				cliente.setIdCliente(resul.getInt("idCliente"));
				cliente.setNome(resul.getString("nome"));
				cliente.setEmail(resul.getString("email"));
				cliente.setTelefone(resul.getString("telefone"));
				cliente.setCpf(resul.getString("cpf"));
				
				listClients.add(cliente);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao buscar Clientes. Error: "+ex.getMessage());
		}finally {
			DBConnection.CloseConnection(conn, stm, resul);
		}
		return listClients;
	}

}
