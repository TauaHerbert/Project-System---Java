package Utils;

import daoLibrary.DBConnection;
import modelsLibrary.Address;
import modelsLibrary.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class ClientAndAddress {
	public void insertClientAndAdress(Client cliente, Address endereco) throws Exception {
		String sqlClient = "INSERT INTO cliente (nome,email,telefone,cpf) VALUES (?,?,?,?)";
		String sqlAddress = "INSERT INTO endereco (rua, numero, bairro, cidade, estado, cep, idCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement stmCli = null, stmAdr = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			stmCli = conn.prepareStatement(sqlClient, Statement.RETURN_GENERATED_KEYS);
			
			stmCli.setString(1, cliente.getNome());
			stmCli.setString(2, cliente.getEmail());
			stmCli.setString(3, cliente.getTelefone());
			stmCli.setString(4, cliente.getCpf());
		    stmCli.executeUpdate();
		    
		    ResultSet rsul = stmCli.getGeneratedKeys();
		    int idClientGerado = -1; 
		    if (rsul.next()) {
				idClientGerado = rsul.getInt(1);
			} else {
				throw new Exception("Erro ao obter Id do Cliente.");
			}
		    
		    stmAdr = conn.prepareStatement(sqlAddress);
		    
		    stmAdr.setString(1, endereco.getRua());
		    stmAdr.setString(2, endereco.getNumero());
		    stmAdr.setString(3, endereco.getBairro());
		    stmAdr.setString(4, endereco.getCidade());
		    stmAdr.setString(5, endereco.getEstado());
		    stmAdr.setString(6, endereco.getCep());
		    stmAdr.setInt(7, idClientGerado);
		    stmAdr.executeUpdate();
		    
		    conn.commit();
			
		} catch (SQLException ex) {
			
				if (conn != null) {
					try {
					conn.rollback();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				throw new RuntimeException("Erro ao inserir Cliente/Endereço. SQL: "+ex.getMessage()+" Erro: "+ex);
		}finally {
			DBConnection.CloseConnection(conn, stmCli, stmAdr, null);
		}
	}
}
