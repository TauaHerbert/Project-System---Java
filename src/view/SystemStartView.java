package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.MessageBox;
import modelsLibrary.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.util.HexFormat;
import java.security.MessageDigest;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class SystemStartView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instaciando os componentes da tela globalmente
	 */
	private JPanel contentPane;
	private JPasswordField jpasswordFieldSenha;
	
	private JButton jbtnIniciar;
	private JButton jbtnExit;
	
	private JLabel jlblNewLabelLogin;
	private JLabel jlblNewLabelSenha;
	
	private JTextField jtextFieldLogin;

	/**Iniciando a tela
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemStartView frame = new SystemStartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Carregando a tela e os seus components e ações
	 */
	public SystemStartView() {
		initComponents();
		actionButtons();
	}
	
	/**
	 * Construção da tela e seus components
	 */
	public void initComponents() {
		setType(Type.UTILITY);
		setLocationRelativeTo(null);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 404);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jbtnIniciar = new JButton("Iniciar");
		jbtnIniciar.setBounds(197, 200, 132, 71);
		contentPane.add(jbtnIniciar);
		
		jbtnExit = new JButton("Encerrar");
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setBounds(437, 331, 95, 23);
		contentPane.add(jbtnExit);
		
		jtextFieldLogin = new JTextField();
		jtextFieldLogin.setToolTipText("");
		jtextFieldLogin.setBounds(197, 80, 132, 20);
		contentPane.add(jtextFieldLogin);
		jtextFieldLogin.setColumns(10);
		
		jpasswordFieldSenha = new JPasswordField();
		jpasswordFieldSenha.setBounds(197, 142, 132, 20);
		contentPane.add(jpasswordFieldSenha);
		
		jlblNewLabelLogin = new JLabel("Login:");
		jlblNewLabelLogin.setBounds(197, 63, 46, 14);
		contentPane.add(jlblNewLabelLogin);
		
		jlblNewLabelSenha = new JLabel("Senha:");
		jlblNewLabelSenha.setBounds(197, 124, 46, 14);
		contentPane.add(jlblNewLabelSenha);
		
		
	}
	
	/**
	 * Metodo criado para a ação dos botões da tela (Listeners)
	 */
	public void actionButtons() {
		
		// Encerra a execução do sistema ao clicar em Sair
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Botão para verificação do login. Verificar se o usuario esta correto e em seguida liberar o acesso para a tela inicial do sistema.
		jbtnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User usuario = new User();
				usuario.setLogin(jtextFieldLogin.getText());
				usuario.setSenha(new String (jpasswordFieldSenha.getPassword()));
				
				if (usuario.getLogin().equals("system") && usuario.getSenha().equals("system")) {
					
				try {
					//Contrução teste para a criação de criptografia para a senha do usuario
					MessageDigest codi = MessageDigest.getInstance("SHA-256");
					byte[] hashBytes = codi.digest(usuario.getSenha().getBytes());
					String protecao = HexFormat.of().formatHex(hashBytes);
					
					MessageBox.messageShow("Senha criptografada: "+protecao);
					
				} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Erro no main. Erro: "+ex);
				}
				
				//Menssagem de acesso liberado e em seguida construção da tela principal e fechamento da tela de login
				MessageBox.messageShow("Acesso ao sistema autorizado");
				MainView tlPrincipal = new MainView();
				tlPrincipal.setVisible(true);
				dispose();
				
				} else {
					MessageBox.messageShow("Login ou Senha incorreta!");
				}
				
			}
		});
	}
}
