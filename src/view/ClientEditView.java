package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ValidationLibrary.ClientValidation;
import modelsLibrary.Address;
import modelsLibrary.Client;
import daoLibrary.AddressDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Utils.MessageBox;

public class ClientEditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtxtNome;
	private JTextField jtxtEmail;
	private JTextField jtxtTelefone;
	private JTextField jtxtCpf;
    private JButton jbtnAtualizarCliente;
    private JTextField jtextCep;
    private JTextField jtextEstado;
    private JTextField jtextCidade;
    private JTextField jtextBairro;
    private JTextField jtextRua;
    private JTextField jtextNumero;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientEditView frame = new ClientEditView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClientEditView() {
		this(0,"","","","");
		initComponents();
	}
	public ClientEditView(int idClient, String nomeClient, String emailClient, String telefoneClient, String cpfClient) {
		initComponents();
	    editarCliente(idClient, nomeClient, emailClient, telefoneClient, cpfClient);
	    carregarEnderecoClient(idClient);
	}

	public void initComponents() {
		setType(Type.UTILITY);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jtxtNome = new JTextField();
		jtxtNome.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtNome.setBounds(162, 193, 286, 30);
		getContentPane().add(jtxtNome);
		jtxtNome.setColumns(10);
		
		jtxtEmail = new JTextField();
		jtxtEmail.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtEmail.setBounds(162, 257, 454, 30);
		getContentPane().add(jtxtEmail);
		jtxtEmail.setColumns(10);
		
		jtxtTelefone = new JTextField();
		jtxtTelefone.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtTelefone.setBounds(162, 316, 142, 30);
		getContentPane().add(jtxtTelefone);
		jtxtTelefone.setColumns(10);
		
		jtxtCpf = new JTextField();
		jtxtCpf.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtCpf.setBounds(162, 371, 142, 30);
		getContentPane().add(jtxtCpf);
		jtxtCpf.setColumns(10);
		
		JButton jbtnVoltar = new JButton("voltar");
		jbtnVoltar.setBounds(1228, 666, 62, 23);
		jbtnVoltar.setBackground(new Color(192, 192, 192));
		jbtnVoltar.setFocusPainted(false);
		jbtnVoltar.setBorder(null);
		jbtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientListView tlclientelist = new ClientListView();
				tlclientelist.setVisible(true);
				dispose();
			}
		});
		contentPane.add(jbtnVoltar);
		
		JLabel jlblNome = new JLabel("Nome:");
		jlblNome.setHorizontalAlignment(SwingConstants.CENTER);
		jlblNome.setFont(new Font("Arial", Font.BOLD, 18));
		jlblNome.setBounds(90, 193, 62, 30);
		contentPane.add(jlblNome);
		
		JLabel jlblEmail = new JLabel("Email:");
		jlblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jlblEmail.setFont(new Font("Arial", Font.BOLD, 18));
		jlblEmail.setBounds(90, 257, 62, 30);
		contentPane.add(jlblEmail);
		
		JLabel jlblTelefone = new JLabel("Telefone:");
		jlblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTelefone.setFont(new Font("Arial", Font.BOLD, 18));
		jlblTelefone.setBounds(60, 316, 92, 30);
		contentPane.add(jlblTelefone);
		
		JLabel jlblCpf = new JLabel("CPF:");
		jlblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jlblCpf.setFont(new Font("Arial", Font.BOLD, 18));
		jlblCpf.setBounds(106, 371, 46, 30);
		contentPane.add(jlblCpf);
		
		JLabel jlblUpdateCliente = new JLabel("Atualizar informações do Cliente");
		jlblUpdateCliente.setFont(new Font("Arial Black", Font.BOLD, 30));
		jlblUpdateCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jlblUpdateCliente.setBounds(377, 59, 605, 61);
		getContentPane().add(jlblUpdateCliente);
		
		jbtnAtualizarCliente = new JButton("Atualizar Cliente");
		jbtnAtualizarCliente.setFont(new Font("Arial Black", Font.BOLD, 25));
		jbtnAtualizarCliente.setBounds(523, 568, 339, 98);
		contentPane.add(jbtnAtualizarCliente);
		
		jtextCep = new JTextField();
		jtextCep.setText("");
		jtextCep.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextCep.setColumns(10);
		jtextCep.setBounds(776, 193, 286, 30);
		contentPane.add(jtextCep);
		
		jtextEstado = new JTextField();
		jtextEstado.setText("");
		jtextEstado.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextEstado.setColumns(10);
		jtextEstado.setBounds(776, 257, 286, 30);
		contentPane.add(jtextEstado);
		
		jtextCidade = new JTextField();
		jtextCidade.setText("");
		jtextCidade.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextCidade.setColumns(10);
		jtextCidade.setBounds(776, 316, 286, 30);
		contentPane.add(jtextCidade);
		
		jtextBairro = new JTextField();
		jtextBairro.setText("");
		jtextBairro.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextBairro.setColumns(10);
		jtextBairro.setBounds(776, 371, 286, 30);
		contentPane.add(jtextBairro);
		
		jtextRua = new JTextField();
		jtextRua.setText("");
		jtextRua.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextRua.setColumns(10);
		jtextRua.setBounds(776, 425, 305, 30);
		contentPane.add(jtextRua);
		
		jtextNumero = new JTextField();
		jtextNumero.setText("");
		jtextNumero.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextNumero.setColumns(10);
		jtextNumero.setBounds(776, 479, 68, 30);
		contentPane.add(jtextNumero);
		
	}
	
	public void editarCliente(int idClient, String nomeClient, String emailClient, String telefoneClient, String cpfClient) {
		
		jtxtNome.setText(nomeClient);
		jtxtEmail.setText(emailClient);
		jtxtTelefone.setText(telefoneClient);
		jtxtCpf.setText(cpfClient);
		
		jbtnAtualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client cliente = new Client();
				
				cliente.setIdCliente(idClient);
				cliente.setNome(jtxtNome.getText());
				cliente.setEmail(jtxtEmail.getText());
				cliente.setTelefone(jtxtTelefone.getText());
				cliente.setCpf(jtxtCpf.getText());
				
				if (!ClientValidation.validarCliente(cliente)) {
					return;
				}
				MessageBox.messageShow("Validado a situação!");
			}
		});
		
	}
	
	public void carregarEnderecoClient(int idClient) {
		
		AddressDAO addressDao = new AddressDAO();
		Address ad = addressDao.selectEndereco(idClient);
		
		if (ad != null) {
			jtextCep.setText(ad.getCep());
			jtextEstado.setText(ad.getEstado());
			jtextCidade.setText(ad.getCidade());
			jtextBairro.setText(ad.getBairro());
			jtextRua.setText(ad.getRua());
			jtextNumero.setText(ad.getNumero());
		}
	}
}
