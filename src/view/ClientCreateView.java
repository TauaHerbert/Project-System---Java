package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import Utils.CharacterLimitFilter;
import Utils.MessageBox;
import Utils.CustomMessageDialog;
import Utils.ClientAndAddress;
import ValidationLibrary.ClientValidation;
import modelsLibrary.Client;
import modelsLibrary.Address;

import java.awt.Font;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

public class ClientCreateView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtxtNome;
	private JTextField jtxtEmail;
	private JTextField jtxtTelefone;
	private JTextField jtxtCpf;

	private JLabel jlblCadastroCliente;
	private JLabel jlblNome;
	private JLabel jlblEmail;
	private JLabel jlblTelefone;
	private JLabel jlblCpf;
	private JLabel jlblRua;
	private JLabel jlblBairro;
	private JLabel jlblNumero;
	private JLabel jlblCep;
	private JLabel jlblCidade;
	private JLabel jlblEstado;
	private JLabel jlblCepError;
	
	private JButton jbtnVoltar;
	private JButton jbtnCadastrarCliente;
	private JButton jbtnBuscCep;
	
	private JTextField jtextFieldCep;
	private JTextField jtextFieldNumero;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientCreateView frame = new ClientCreateView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ClientCreateView() {
		initComponents();
		actionButtons();
		actionDocumentsEvents();
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
		
		jbtnVoltar = new JButton("Voltar");
		jbtnVoltar.setBounds(1228, 666, 62, 23);
		jbtnVoltar.setBackground(new Color(192, 192, 192));
		jbtnVoltar.setFocusPainted(false);
		jbtnVoltar.setBorder(null);
		getContentPane().add(jbtnVoltar);
		
		jlblCadastroCliente = new JLabel("Cadastro de Cliente");
		jlblCadastroCliente.setFont(new Font("Arial Black", Font.BOLD, 30));
		jlblCadastroCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jlblCadastroCliente.setBounds(472, 59, 363, 61);
		getContentPane().add(jlblCadastroCliente);
		
		jtxtNome = new JTextField();
		jtxtNome.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtNome.setBounds(166, 190, 286, 30);
		getContentPane().add(jtxtNome);
		jtxtNome.setColumns(10);
		
		jtxtEmail = new JTextField();
		jtxtEmail.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtEmail.setBounds(166, 254, 454, 30);
		getContentPane().add(jtxtEmail);
		jtxtEmail.setColumns(10);
		
		jtxtTelefone = new JTextField();
		jtxtTelefone.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtTelefone.setBounds(166, 313, 142, 30);
		getContentPane().add(jtxtTelefone);
		jtxtTelefone.setColumns(10);
		
		jtxtCpf = new JTextField();
		jtxtCpf.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtCpf.setBounds(166, 368, 142, 30);
		getContentPane().add(jtxtCpf);
		jtxtCpf.setColumns(10);
		
		jlblNome = new JLabel("Nome:");
		jlblNome.setFont(new Font("Arial", Font.BOLD, 18));
		jlblNome.setHorizontalAlignment(SwingConstants.CENTER);
		jlblNome.setBounds(94, 190, 62, 30);
		((AbstractDocument) jtxtNome.getDocument()).setDocumentFilter(new CharacterLimitFilter(50));
		getContentPane().add(jlblNome);
		
		jlblEmail = new JLabel("Email:");
		jlblEmail.setFont(new Font("Arial", Font.BOLD, 18));
		jlblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jlblEmail.setBounds(94, 254, 62, 30);
		((AbstractDocument) jtxtEmail.getDocument()).setDocumentFilter(new CharacterLimitFilter(100));
		getContentPane().add(jlblEmail);
		
		jlblTelefone = new JLabel("Telefone:");
		jlblTelefone.setFont(new Font("Arial", Font.BOLD, 18));
		jlblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTelefone.setBounds(64, 313, 92, 30);
		((AbstractDocument) jtxtTelefone.getDocument()).setDocumentFilter(new CharacterLimitFilter(11));
		getContentPane().add(jlblTelefone);
		
		jlblCpf = new JLabel("CPF:");
		jlblCpf.setFont(new Font("Arial", Font.BOLD, 18));
		jlblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jlblCpf.setBounds(110, 368, 46, 30);
		((AbstractDocument) jtxtCpf.getDocument()).setDocumentFilter(new CharacterLimitFilter(11));
		getContentPane().add(jlblCpf);
		
		jbtnCadastrarCliente = new JButton("Cadastrar Cliente");
		jbtnCadastrarCliente.setFont(new Font("Arial Black", Font.BOLD, 25));
		jbtnCadastrarCliente.setBounds(490, 581, 345, 80);
		getContentPane().add(jbtnCadastrarCliente);
		
		jtextFieldCep = new JTextField();
		jtextFieldCep.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextFieldCep.setColumns(10);
		jtextFieldCep.setBounds(866, 190, 101, 30);
		((AbstractDocument) jtextFieldCep.getDocument()).setDocumentFilter(new CharacterLimitFilter(8)); 
		getContentPane().add(jtextFieldCep);
		
		jlblRua = new JLabel("Rua:");
		jlblRua.setHorizontalAlignment(SwingConstants.LEFT);
		jlblRua.setFont(new Font("Arial", Font.BOLD, 18));
		jlblRua.setBounds(810, 433, 321, 30);
		getContentPane().add(jlblRua);
		
		jlblNumero = new JLabel("Numero:");
		jlblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jlblNumero.setFont(new Font("Arial", Font.BOLD, 18));
		jlblNumero.setBounds(762, 490, 101, 30);
		getContentPane().add(jlblNumero);
		
		jlblBairro = new JLabel("Bairro:");
		jlblBairro.setHorizontalAlignment(SwingConstants.LEFT);
		jlblBairro.setFont(new Font("Arial", Font.BOLD, 18));
		jlblBairro.setBounds(792, 373, 200, 30);
		getContentPane().add(jlblBairro);
		
		jlblCidade = new JLabel("Cidade:");
		jlblCidade.setHorizontalAlignment(SwingConstants.LEFT);
		jlblCidade.setFont(new Font("Arial", Font.BOLD, 18));
		jlblCidade.setBounds(782, 313, 200, 30);
		getContentPane().add(jlblCidade);
		jlblEstado = new JLabel("Estado:");
		jlblEstado.setHorizontalAlignment(SwingConstants.LEFT);
		jlblEstado.setFont(new Font("Arial", Font.BOLD, 18));
		jlblEstado.setBounds(783, 254, 116, 30);
		getContentPane().add(jlblEstado);
		
		jlblCep = new JLabel("Cep:");
		jlblCep.setHorizontalAlignment(SwingConstants.CENTER);
		jlblCep.setFont(new Font("Arial", Font.BOLD, 18));
		jlblCep.setBounds(800, 189, 56, 30);
		getContentPane().add(jlblCep);
		
		jtextFieldNumero = new JTextField();
		jtextFieldNumero.setEnabled(false);
		jtextFieldNumero.setFont(new Font("Arial", Font.PLAIN, 17));
		jtextFieldNumero.setColumns(10);
		jtextFieldNumero.setBounds(866, 491, 62, 30);
		getContentPane().add(jtextFieldNumero);
		
		jbtnBuscCep = new JButton("Buscar Cep");
		jbtnBuscCep.setBounds(977, 191, 101, 30);
		getContentPane().add(jbtnBuscCep);
		
		jlblCepError = new JLabel("");
		jlblCepError.setForeground(new Color(255, 0, 0));
		jlblCepError.setBounds(866, 229, 220, 14);
		getContentPane().add(jlblCepError);
	}
	
	public void actionDocumentsEvents() {
		jtextFieldCep.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				verificar();
				verificarCep();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				verificar();
				verificarCep();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				verificar();
				verificarCep();
			}
			
			public void verificarCep() {
				if (!isCepValidation(jtextFieldCep.getText())) {
					jlblCepError.setText("Cep invalido");
				} else if(!cepExiste(jtextFieldCep.getText())) {
					jlblCepError.setText("Cep não encontrado na busca do ViaCEP");
				}else {
					jlblCepError.setText("");
				}
			}
			
			public void verificar() {
				if (jtextFieldCep.getText().length() < 8) {
					jtextFieldNumero.setEnabled(false);
				}else if (jtextFieldCep.getText().trim().isEmpty()) {
					jtextFieldNumero.setEnabled(false);
				}else {
					jtextFieldNumero.setEnabled(true);
				}
			}
		});
	}
	
	public void actionButtons() {
		jbtnBuscCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!isCepValidation(jtextFieldCep.getText())) {
						MessageBox.messageShow("Cep invalido");
						return;
					} else if(!cepExiste(jtextFieldCep.getText())) {
						MessageBox.messageShow("Cep não encontrado na busca do ViaCEP");
						return;
					}else {
						jlblRua.setText("Rua: ");
						jlblBairro.setText("Bairro: ");
						jlblCidade.setText("Cidade: ");
						jlblEstado.setText("Estado: ");
						
						jtextFieldNumero.setEnabled(true);
						
						Address address = buscarEndereco(jtextFieldCep.getText());
						
						jlblRua.setText("Rua: "+address.getRua());
						jlblBairro.setText("Bairro: "+address.getBairro());
						jlblCidade.setText("Cidade: "+address.getCidade());
						jlblEstado.setText("Estado: "+address.getEstado());
						
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
					MessageBox.messageShow(ex.getMessage());
				}
			}
		});
		
		jbtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tlprincipal = new MainView();
				tlprincipal.setVisible(true);
				dispose();
			}
		});
		
		Color bntHover = new Color(90,90,90);
		jbtnCadastrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				jbtnCadastrarCliente.setBackground(bntHover);
				jbtnCadastrarCliente.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				jbtnCadastrarCliente.setBackground(null);
				jbtnCadastrarCliente.setForeground(null);
			}
		});
		
		jbtnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client cliente = new Client();
					Address address = new Address();
					
					cliente.setNome(jtxtNome.getText());
					cliente.setEmail(jtxtEmail.getText());
					cliente.setTelefone(jtxtTelefone.getText());
					cliente.setCpf(jtxtCpf.getText());
					
					address.setCep(jtextFieldCep.getText());
					address.setEstado(address.getEstado());
					address.setCidade(address.getCidade());
					address.setBairro(address.getBairro());
					address.setRua(address.getRua());
					address.setNumero(jtextFieldNumero.getText());
					
					if (!ClientValidation.validarCliente(cliente)) {
						return;
					}
					
					try {
						ClientAndAddress ca = new ClientAndAddress();
						ca.insertClientAndAdress(cliente, address);
					} catch (Exception e2) {
						MessageBox.messageShow("Erro ao Cadastrar cliente e endereço!");
						return;
					}
					
					new CustomMessageDialog(null, "Aviso", "Cliente "+cliente.getNome()+" cadastrado(a) com sucesso!").setVisible(true);
					JOptionPane.showMessageDialog(null, "Cliente "+cliente.getNome()+" cadastrado(a) com sucesso! ");
					jtxtNome.setText("");
					jtxtEmail.setText("");
					jtxtTelefone.setText("");
					jtxtCpf.setText("");
					
					jtextFieldCep.setText("");
					jlblRua.setText("Rua: ");
					jlblBairro.setText("Bairro: ");
					jlblCidade.setText("Cidade: ");
					jlblEstado.setText("Estado: ");
					jtextFieldNumero.setText("");
					jlblCepError.setText("");
					
				} catch (IllegalArgumentException ex) {
					MessageBox.messageShow("Erro ao cadastrar cliente. Erro: "+ex.getMessage());
					return;
				}
			}
		});
	}
	
	public static boolean isCepValidation(String cep) {
		return cep != null && cep.matches("^\\d{5}-?\\d{3}$");
	}
	
	public static boolean cepExiste(String cep) {
	    try {
	        String urlStr = "https://viacep.com.br/ws/" + cep + "/json/";
	        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
	        con.setRequestMethod("GET");

	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        StringBuilder resposta = new StringBuilder();
	        String linha;

	        while ((linha = in.readLine()) != null) {
	            resposta.append(linha);
	        }
	        in.close();

	     // se tiver erro:true, o CEP não existe
	        return !resposta.toString().contains("\"erro\": true"); 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Address buscarEndereco(String cep) throws Exception{
		String urlCep = "https://viacep.com.br/ws/" + cep + "/json/";
		URL url = new URL(urlCep);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(http.getInputStream()));
		StringBuilder stringbuilder = new StringBuilder();
		String inputLine;
		
		while ((inputLine = buf.readLine()) != null) {
			stringbuilder.append(inputLine);
		}
		buf.close();
		
		Gson gson = new Gson();
		Address address = gson.fromJson(stringbuilder.toString(), Address.class);
		
		return address;
	}
}
