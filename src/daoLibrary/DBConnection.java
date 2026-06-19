package daoLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gerenciador de conexões com o banco de dados da aplicação.
 * * @author Tauã Herbert
 */
public class DBConnection {
	
	/**
	 * Constantes de configuração do JDBC Driver para o MySQL.
	 */
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/banco_test";
	public static final String USER = "";
	public static final String PASS = "";
	
	
	/**
     * Carrega as propriedades de configuração do banco de dados a partir
     * do arquivo externo 'config.properties' localizado na raiz do projeto.
     * * @return Um objeto {@link Properties} contendo as chaves e valores lidos.
     * @throws RuntimeException Se o arquivo não for encontrado ou ocorrer erro de leitura.
     */
	private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo config.properties: " + e.getMessage());
        }
    }
	
	/**
     * Estabelece uma conexão ativa com o banco de dados MySQL utilizando
     * os parâmetros dinâmicos carregados do arquivo de propriedades.
     * * @return Uma instância de {@link Connection} pronta para uso.
     * @throws RuntimeException Se houver falha de autenticação ou erro no driver de rede do banco.
     */
	public static Connection getConnection() {
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");
            
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
	
	/**
	 * Gera a conexão com o banco de dados utilizando os parâmetros configurados.
	 * @return Connection - Objeto de conexão ativa com o banco de dados.
	 * @throws SQLException - Lançada caso as credenciais (USER/PASS) ou a URL estejam incorretas.
	 * @throws ClassNotFoundException - Lançada caso o Driver JDBC não seja encontrado no projeto.
	 */
	/*public static Connection getConnection() throws SQLException{
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
			
		
		//Caso aconteça alguma falha na conexão é lançada uma exceção
		} catch (Exception ex) {
			throw new RuntimeException("Erro de conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}*/
	
	
	/**
	 * Realiza o fechamento seguro da conexão com o banco de dados.
	 * @param conn Objeto Connection a ser encerrado.
	 * @throws RuntimeException - Lançada caso ocorra uma falha crítica ao encerrar os recursos.
	 */
	public static void CloseConnection (Connection conn){
		try {
			if (conn != null ) {
				conn.close();
			}
			
		//Caso aconteça alguma falha é lançada uma exceção
        //SQLException - Lançada caso o encerramento da conexão não aconteça.
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * Realiza o fechamento seguro dos recursos do banco de dados.
	 * @param conn Objeto Connection a ser encerrado.
	 * @param stmt Objeto PreparedStatement para liberação de cursores no banco.
	 */
	public static void CloseConnection (Connection conn, PreparedStatement stmt) {
		try {
			if (conn != null) {
				conn.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
		//Caso aconteça alguma falha é lançada uma exceção
		//SQLException - Lançada caso o encerramento da conexão e a liberação do PreparedStatement não aconteça.
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * Realiza o fechamento seguro dos recursos do banco de dados.
	 * @param conn Objeto Connection a ser encerrado.
	 * @param stmt Objeto PreparedStatement para liberação de cursores no banco.
	 * @param rslt Objeto ResultSet contendo o conjunto de resultados da query.
	 */
	public static void CloseConnection (Connection conn, PreparedStatement stmt, ResultSet rslt) {
		try {
			
			if (conn != null) conn.close();
			if (stmt != null) stmt.close();
			if (rslt != null) rslt.close();
		
		// Caso aconteça alguma falha é lançada uma exceção
		//SQLException - Lançada caso o encerramento da conexão a liberação do PreparedStatement e a query do ResultSet não aconteça.
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	/**
	 * Realiza o fechamento seguro dos recursos do banco de dados.
	 * @param conn Objeto Connection a ser encerrado.
	 * @param stmt (1 e 2) Objeto PreparedStatement para liberação de cursores no banco.
	 * @param rslt Objeto ResultSet contendo o conjunto de resultados da query.
	 */
	public static void CloseConnection (Connection conn, PreparedStatement stmt1, PreparedStatement stmt2, ResultSet rslt) {
		try {
			
			if (conn != null) conn.close();
			if (stmt1 != null) stmt1.close();
			if (stmt2 != null) stmt2.close();
			if (rslt != null) rslt.close();
		
		//Caso aconteça alguma falha é lançada uma exceção
		//SQLException - Lançada caso o encerramento da conexão a liberação do PreparedStatement (1 e 2) e a query do ResultSet não aconteça.
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao encerrar a conexão com o banco de dados: "+ex.getMessage(), ex);
		}
	}
	
	/** 
	 * Encerramento seguro do ResultSet com o banco de dados
	 * @param rslt Objeto ResultSet contendo o conjunto de resultados da query.
	 */
	public static void CloseResultSet (ResultSet rslt) {
		try {
			
			if (rslt != null) rslt.close();
		
		//Caso aconteça alguma falha é lançada uma exceção
		//SQLException - Lançada caso o encerramento da query do ResultSet não aconteça.
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao fechar o ResultSet: "+ex.getMessage(), ex);
		}
	}

}
