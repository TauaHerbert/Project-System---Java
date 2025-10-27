package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JButton jbtnCadProd;
	private JButton btnCadastrarCliente;
	private JButton jbtnCadCaterg;
	private JButton jbtnEditCate;
	private JButton jbtnEditProd;
	private JButton jbtnListProd;
	private JButton jbtnListCater;
	private JButton jbtnPedidos;
	private JButton jbtnCompraPedido;
	private JButton jbtnExitSystem;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainView() {
		initComponents();
		eventsButtons();
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
		
		jbtnCadProd = new JButton("Cadastrar Produto");
		jbtnCadProd.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnCadProd.setBounds(140, 86, 301, 137);
		getContentPane().add(jbtnCadProd);
		
		jbtnCompraPedido = new JButton(" Iniciar Vendas\r\n(Fazer Pedidos)");
		jbtnCompraPedido.setToolTipText(" Iniciar Vendas\r\n(Fazer Pedidos)");
		jbtnCompraPedido.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnCompraPedido.setBounds(950, 86, 301, 137);
		getContentPane().add(jbtnCompraPedido);
		
		jbtnCadCaterg = new JButton("Cadastrar Categoria");
		jbtnCadCaterg.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnCadCaterg.setBounds(568, 86, 301, 137);
		getContentPane().add(jbtnCadCaterg);
		
		jbtnExitSystem = new JButton("Sair");
		jbtnExitSystem.setBounds(1228, 666, 62, 23);
		jbtnExitSystem.setBackground(new Color(192, 192, 192));
		jbtnExitSystem.setFocusPainted(false);
		jbtnExitSystem.setBorder(null);
		getContentPane().add(jbtnExitSystem);
		
		jbtnListProd = new JButton("Listar Produtos Cadastrado");
		jbtnListProd.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnListProd.setBounds(140, 294, 301, 137);
		getContentPane().add(jbtnListProd);
		
		jbtnListCater = new JButton("Listar Todas Categorias");
		jbtnListCater.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnListCater.setBounds(568, 294, 301, 137);
		getContentPane().add(jbtnListCater);
		
		jbtnEditCate = new JButton("Editar Categoria");
		jbtnEditCate.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnEditCate.setBounds(568, 498, 301, 137);
		getContentPane().add(jbtnEditCate);
		
		jbtnEditProd = new JButton("Editar Produtos");
		jbtnEditProd.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnEditProd.setBounds(140, 498, 301, 137);
		getContentPane().add(jbtnEditProd);
		
		btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		btnCadastrarCliente.setBounds(951, 498, 300, 137);
		getContentPane().add(btnCadastrarCliente);
		
		jbtnPedidos = new JButton("Pedidos");
		jbtnPedidos.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		jbtnPedidos.setBounds(950, 294, 301, 137);
		getContentPane().add(jbtnPedidos);
	}
	
	public void eventsButtons() {
		jbtnCadProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCreateView tlproduto = new ProductCreateView();
				tlproduto.setVisible(true);
				dispose();
			}
		});
		
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientCreateView tlcliente = new ClientCreateView();
				tlcliente.setVisible(true);
				dispose();
			}
		});
		
		jbtnCadCaterg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryCreateView tlcategoria = new CategoryCreateView();
				tlcategoria.setVisible(true);
				dispose();
			}
		});
		
		jbtnListProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductListView tlProdut = new ProductListView();
				tlProdut.setVisible(true);
				dispose();
			}
		});
		
		jbtnListCater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryListView tlcategoria = new CategoryListView();
				tlcategoria.setVisible(true);
				dispose();
			}
		});
		
		jbtnCompraPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderView tlpedido = new OrderView();
				tlpedido.setVisible(true);
				dispose();
			}
		});
		
		jbtnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderListView pedido = new OrderListView();
				pedido.setVisible(true);
				dispose();
			}
		});
		
		jbtnExitSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int conf = JOptionPane.showConfirmDialog(null, "Deseja encerrar o sistema?","Sytema Loja",JOptionPane.YES_NO_OPTION);
				if (conf == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		});
	}
}
