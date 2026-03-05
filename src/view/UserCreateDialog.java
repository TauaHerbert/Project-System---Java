package view;

import java.awt.Color;

import javax.swing.JDialog;

import Utils.MessageBox;
import modelsLibrary.User;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class UserCreateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JButton jButtonFechar;
	
	private JTextField jtxtLogin;
	private JPasswordField jpasswordSenha;
	private JPasswordField jpasswordSenhaMaster;
	
	private JLabel jlblLogin;
	private JLabel jlblSenha;
	private JLabel jlblSenhaMaster;
	private JButton jbtnCadastrar;
	
	/**Iniciando a tela
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
					UserCreateDialog dialog = new UserCreateDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
			  } catch (Exception e) {
					e.printStackTrace();
		}
	}

	/**
	 * Carregando a tela e os seus components e ações.
	 */
	public UserCreateDialog() {
		initComponents();
		actionButtons();
	}
	
	/**
	 * Construção da tela e seus components
	 * botão, caixas de textos e label(s).
	 */
	public void initComponents() {
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setUndecorated(true);
		setBounds(100, 100, 472, 381);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(null);
		
		jButtonFechar = new JButton("Sair");
		jButtonFechar.setBounds(405, 347, 57, 23);
		getContentPane().add(jButtonFechar);
		
		jtxtLogin = new JTextField();
		jtxtLogin.setBounds(150, 81, 163, 20);
		getContentPane().add(jtxtLogin);
		jtxtLogin.setColumns(10);
		
		jpasswordSenha = new JPasswordField();
		jpasswordSenha.setBounds(150, 150, 163, 20);
		getContentPane().add(jpasswordSenha);
		
		jpasswordSenhaMaster = new JPasswordField();
		jpasswordSenhaMaster.setBounds(150, 222, 163, 20);
		getContentPane().add(jpasswordSenhaMaster);
		
		jlblLogin = new JLabel("Login:");
		jlblLogin.setBounds(150, 62, 46, 14);
		getContentPane().add(jlblLogin);
		
		jlblSenha = new JLabel("Senha:");
		jlblSenha.setBounds(150, 131, 46, 14);
		getContentPane().add(jlblSenha);
		
		jlblSenhaMaster = new JLabel("Senha Master:");
		jlblSenhaMaster.setBounds(150, 205, 85, 14);
		getContentPane().add(jlblSenhaMaster);
		
		jbtnCadastrar = new JButton("Cadastrar Novo Usuario");
		jbtnCadastrar.setBounds(150, 269, 163, 40);
		getContentPane().add(jbtnCadastrar);
	}
	
	/**
	 * Metodo criado para a ação dos botões da tela (Listeners)
	 */
	public void actionButtons() {

		//Botão para cadastrar Usuario.
		jbtnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				
				user.setLogin(jtxtLogin.getText());
				user.setSenha(new String(jpasswordSenha.getPassword()));
				
				MessageBox.messageShow("Novo Úsuario cadastrado com sucesso!");
				dispose();
			}
		});

		//Botão para fechar a tela dialog.
		jButtonFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
