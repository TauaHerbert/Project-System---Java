package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import Utils.*;
import daoLibrary.ProductDAO;
import modelsLibrary.Product;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

public class ProductEditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField jtxtNome;
	private JTextField jtxtpreco;
	private JTextArea jtxtAreaDescricao;
	
	private JLabel jlblTitulo;
	private JLabel jlblNome;
	private JLabel jlblPreco;
	private JLabel jlblDescricao;
	private JLabel jlblCategoria;
	
	private JComboBox<ItemCategory> jcmbCategoria;
	
	private JButton jbtnExit;
	private JButton jbtnUpdate;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductEditView frame = new ProductEditView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductEditView() {
		this(0,"","", null);
	}
	public ProductEditView(int id, String nome, String descricao, BigDecimal preco) {
		initComponets();
		eventsButtons(id, nome, descricao, preco);
	}
	public void initComponets() {
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
		
		jbtnExit = new JButton("Cancelar");
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBounds(10, 666, 62, 23);
		contentPane.add(jbtnExit);
		
		
		jtxtNome = new JTextField();
		jtxtNome.setBounds(221, 109, 233, 20);
		contentPane.add(jtxtNome);
		jtxtNome.setColumns(10);
		
		jtxtAreaDescricao = new JTextArea();
		jtxtAreaDescricao.setBounds(221, 159, 334, 94);
		((AbstractDocument) jtxtAreaDescricao.getDocument()).setDocumentFilter(new CharacterLimitFilter(200));
		contentPane.add(jtxtAreaDescricao);
		
		jtxtpreco = new JTextField();
		jtxtpreco.setBounds(221, 281, 86, 20);
		contentPane.add(jtxtpreco);
		jtxtpreco.setColumns(10);
	
		jbtnUpdate = new JButton("Atualizar");
		jbtnUpdate.setBounds(221, 385, 334, 47);
		contentPane.add(jbtnUpdate);
		
		jlblNome = new JLabel("Nome: ");
		jlblNome.setBounds(221, 92, 46, 14);
		contentPane.add(jlblNome);
		
		jlblDescricao = new JLabel("Descrição: ");
		jlblDescricao.setBounds(221, 140, 67, 14);
		contentPane.add(jlblDescricao);
		
		jlblPreco = new JLabel("Preço: ");
		jlblPreco.setBounds(221, 264, 46, 14);
		contentPane.add(jlblPreco);
		
		jlblTitulo = new JLabel("Atualizar Produto");
		jlblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
		jlblTitulo.setBounds(221, 32, 233, 56);
		contentPane.add(jlblTitulo);
		
		jlblCategoria = new JLabel("Categoria: ");
		jlblCategoria.setBounds(221, 319, 67, 14);
		contentPane.add(jlblCategoria);
		
		jcmbCategoria = new JComboBox<>();
		jcmbCategoria.setBounds(221, 339, 334, 22);
		ItemCategory.comboCategorias(jcmbCategoria);
		contentPane.add(jcmbCategoria);
	}
	
	public void eventsButtons(int id, String nome, String descricao, BigDecimal preco) {
		
		jtxtNome.setText(nome);
		jtxtAreaDescricao.setText(descricao);
		jtxtpreco.setText(preco != null ? preco.toString() : "");
		
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductListView tlprodu = new ProductListView();
				tlprodu.setVisible(true);
				dispose();
			}
		});
		
		jbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product produto = new Product();
				ProductDAO ddproduto = new ProductDAO();
				
				produto.setNome(jtxtNome.getText());
				produto.setDescricao(jtxtAreaDescricao.getText());
				try {
					String txtPreco = jtxtpreco.getText();
					BigDecimal valor = new BigDecimal(txtPreco);
					produto.setPreco(valor);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Preço invalido. Digite um numero válido! Erro: "+ex);
					return;
				}
				
				ItemCategory categoriaSelecionada = (ItemCategory) jcmbCategoria.getSelectedItem();
				if (categoriaSelecionada == null) {
		        	JOptionPane.showMessageDialog(null, "Categoria não selecionada. Selecione uma categoria!");
		        	return;
		        }
				int idCategoria = categoriaSelecionada.getId();
				produto.setIdProduto(id);
				produto.setIdCategoria(idCategoria);
				
				ddproduto.updateProduto(produto);
				JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
				dispose();
			}
		});
	}
}
