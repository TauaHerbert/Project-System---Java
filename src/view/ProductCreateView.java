package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Utils.CharacterLimitFilter;
import Utils.ItemCategory;
import daoLibrary.ProductDAO;
import modelsLibrary.Product;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.text.AbstractDocument;

public class ProductCreateView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Point fixar;
	
	private JLabel lblNewLabel;
	private JLabel jlblNome;
	private JLabel jlblDescricao;
	private JLabel jlblPreco;
	private JLabel jlblCategoria;
	
	private JTextField jtxtNome;
	private JTextField jtxtPreco;
	private JTextArea jtxtAreaDescri;
	
	private JComboBox<ItemCategory> jcmbCategoria;

	private JButton jbtnExit;
	private JButton btnCadatrarProduto;
	private JButton jbtnListarProdu;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductCreateView frame = new ProductCreateView();
					frame.setVisible(true);
					frame.fixar = frame.getLocation();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ProductCreateView() {
		InitComponents();
		actionsButtons();
	}

	public void InitComponents() {
		setType(Type.UTILITY);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jbtnExit = new JButton("Voltar");
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBounds(1228, 666, 62, 23);
		contentPane.add(jbtnExit);
		
		lblNewLabel = new JLabel("Cadastro de Produtos");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		lblNewLabel.setBounds(463, 58, 368, 32);
		lblNewLabel.setBackground(new Color(240, 240, 240));
		contentPane.add(lblNewLabel);
		
		jtxtNome = new JTextField();
		jtxtNome.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtNome.setBounds(547, 139, 267, 30);
		contentPane.add(jtxtNome);
		jtxtNome.setColumns(10);
		
		jtxtPreco = new JTextField();
		jtxtPreco.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtPreco.setBounds(584, 369, 98, 30);
		contentPane.add(jtxtPreco);
		jtxtPreco.setColumns(10);
		
		jtxtAreaDescri = new JTextArea();
		jtxtAreaDescri.setFont(new Font("Arial", Font.PLAIN, 17));
		jtxtAreaDescri.setBounds(478, 220, 336, 138);
		jtxtAreaDescri.setLineWrap(true);
		jtxtAreaDescri.setWrapStyleWord(true);
		//Limitando o TextArea para 200 caracters
		((AbstractDocument) jtxtAreaDescri.getDocument()).setDocumentFilter(new CharacterLimitFilter(200));
		contentPane.add(jtxtAreaDescri);
		
		//Trazendo os nomes das caegorias junto com o id para o combobox
		jcmbCategoria = new JComboBox<>();
		jcmbCategoria.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 17));
		jcmbCategoria.setBounds(478, 441, 267, 30);
		contentPane.add(jcmbCategoria);
		ItemCategory.comboCategorias(jcmbCategoria);
		
		jlblNome = new JLabel("Nome:");
		jlblNome.setFont(new Font("Arial", Font.BOLD, 18));
		jlblNome.setBounds(478, 145, 59, 14);
		contentPane.add(jlblNome);
		
		jlblDescricao = new JLabel("Descrição:");
		jlblDescricao.setFont(new Font("Arial", Font.BOLD, 18));
		jlblDescricao.setBounds(478, 195, 99, 14);
		contentPane.add(jlblDescricao);
		
		jlblPreco = new JLabel("Preço R$:");
		jlblPreco.setFont(new Font("Arial", Font.BOLD, 18));
		jlblPreco.setBounds(478, 376, 96, 14);
		contentPane.add(jlblPreco);
		
		jlblCategoria = new JLabel("Selecione a categoria:");
		jlblCategoria.setFont(new Font("Arial", Font.BOLD, 18));
		jlblCategoria.setBounds(475, 416, 207, 14);
		contentPane.add(jlblCategoria);
		
		btnCadatrarProduto = new JButton("Cadastrar Produto");
		btnCadatrarProduto.setFont(new Font("Arial Black", Font.PLAIN, 25));
		btnCadatrarProduto.setBackground(new Color(192, 192, 192));
		btnCadatrarProduto.setBounds(478, 502, 279, 80);
		btnCadatrarProduto.setFocusPainted(false);
		btnCadatrarProduto.setBorder(null);
		contentPane.add(btnCadatrarProduto);
		
		jbtnListarProdu = new JButton("Listar Produtos");
		jbtnListarProdu.setFont(new Font("Arial Black", Font.PLAIN, 25));
		jbtnListarProdu.setBounds(811, 502, 279, 80);
		contentPane.add(jbtnListarProdu);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if (fixar != null) {
					setLocation(fixar);
				}
			}
		});
	}
	
	public void actionsButtons() {
		
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tprincipal = new MainView();
				tprincipal.setVisible(true);
				dispose();
			}
		});
		
		jbtnListarProdu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductListView tlProduto = new ProductListView();
				tlProduto.setVisible(true);
				dispose();
			}
		});
		
		btnCadatrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product produto = new Product();
				ProductDAO ddproduto = new ProductDAO();
				
		        produto.setNome(jtxtNome.getText());
		        produto.setDescricao(jtxtAreaDescri.getText());
		        
		        if(jtxtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nome do Produto vazio");
					return;
				}
		        
		        try {
		        	BigDecimal preco = new BigDecimal(jtxtPreco.getText().trim());
		        	produto.setPreco(preco);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Valor do Produto incorreto! Erro: "+ex.getMessage());
					return;
				}
		        
		        ItemCategory categoriaSelecionada = (ItemCategory) jcmbCategoria.getSelectedItem();
		        if (categoriaSelecionada == null) {
		        	JOptionPane.showMessageDialog(null, "Categoria não selecionada. Selecione uma categoria!");
		        	return;
		        }
		        
		        int idCategoria = categoriaSelecionada.getId();
				produto.setIdCategoria(idCategoria);
				ddproduto.insertProduto(produto);
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
				jtxtNome.setText("");
				jtxtAreaDescri.setText("");
				jtxtPreco.setText("");
				
			}
		});
	}
}
