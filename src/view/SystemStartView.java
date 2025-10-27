package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrary.DBConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SystemStartView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		jbtnIniciar.setBounds(194, 103, 132, 71);
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
		
		jbtnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DBConnection.getConnection();
					
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
			}
		});
	}
}
