package view;

import javax.swing.JOptionPane;
import daoLibrary.DBConnection;

/**
 * Classe responsável pelo Bootstrap (inicialização) da aplicação.
 * Realiza o Health Check da conexão com o banco de dados antes de instanciar a UI.
 */
public class ApplicationLauncher {

	public static void main(String[] args) {
		
		//Verificação do Teste de Conectividade para checar a conexão com o banco de dados.
		try {
				
				if (DBConnection.getConnection() != null) {
					
					JOptionPane.showMessageDialog(null,"Conexão bem sucedida");
					
					//Inicialização da tela de login do sistema
					SystemStartView tlInicial = new SystemStartView();
					tlInicial.setVisible(true);
					
					DBConnection.getConnection().close();
					
				}
				
		// Caso não tenha uma conexão é lançado uma exceção	
		} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"Falha ao se conectar com servidor (banco de dados):  - "+ex.getMessage());
			}
		} 
}

