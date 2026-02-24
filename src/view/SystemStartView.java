package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.MessageBox;
import daoLibrary.DBConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import java.util.HexFormat;
import java.security.MessageDigest;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class SystemStartView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String login, senha;
	private JPasswordField jpasswordFieldSenha;

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
	 * Create the frame.
	 */
	public SystemStartView() {
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
		
		JButton jbtnIniciar = new JButton("Iniciar");
		jbtnIniciar.setBounds(197, 200, 132, 71);
		contentPane.add(jbtnIniciar);
		
		JButton jbtnExit = new JButton("Encerrar");
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setBounds(437, 331, 95, 23);
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(jbtnExit);
		
		JTextField jtextFieldLogin = new JTextField();
		jtextFieldLogin.setToolTipText("");
		jtextFieldLogin.setBounds(197, 80, 132, 20);
		contentPane.add(jtextFieldLogin);
		jtextFieldLogin.setColumns(10);
		
		jpasswordFieldSenha = new JPasswordField();
		jpasswordFieldSenha.setBounds(197, 142, 132, 20);
		contentPane.add(jpasswordFieldSenha);
		
		JLabel jlblNewLabelLogin = new JLabel("Login:");
		jlblNewLabelLogin.setBounds(197, 63, 46, 14);
		contentPane.add(jlblNewLabelLogin);
		
		JLabel jlblNewLabelSenha = new JLabel("Senha:");
		jlblNewLabelSenha.setBounds(197, 124, 46, 14);
		contentPane.add(jlblNewLabelSenha);
		
		
		jbtnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login = jtextFieldLogin.getText();
				senha = new String (jpasswordFieldSenha.getPassword());
				if (login.equals("system") && senha.equals("system")) {
					
				try {
					MessageDigest codi = MessageDigest.getInstance("SHA-256");
					byte[] hashBytes = codi.digest(senha.getBytes());
					
					String protecao = HexFormat.of().formatHex(hashBytes);
					
					MessageBox.messageShow("Senha criptografada: "+protecao);
					
						if (DBConnection.getConnection() != null) {
							JOptionPane.showMessageDialog(null,"Conexão bem sucedida");
							
							try {
								DBConnection.getConnection().close();
							} catch (SQLException ex) {
								JOptionPane.showMessageDialog(null,"Falha ao encerrar conexão: "+ex.getMessage());
							}
							
							MainView tlPrincipal = new MainView();
							tlPrincipal.setVisible(true);
							dispose();
							
						}else {
							JOptionPane.showMessageDialog(null,"Falha ao conectar");
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Erro no main. Erro: "+ex);
					}
				} else {
					MessageBox.messageShow("Senha incorreta");
				}
				
			}
		});
	}
}
