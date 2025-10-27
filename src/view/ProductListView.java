package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ItemCategory;
import daoLibrary.ProductDAO;
import modelsLibrary.Product;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class ProductListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Point fixar;
	private JTable jtableProduto;
	int idLinha = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductListView frame = new ProductListView();
					frame.setVisible(true);
					frame.fixar = frame.getLocation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductListView() {
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
		
		JButton jbtnExit = new JButton("Voltar Menu");
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBounds(1174, 666, 116, 23);
		contentPane.add(jbtnExit);
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tlPrincipal = new MainView();
				tlPrincipal.setVisible(true);
				dispose();
			}
		});
		
		JButton jbtnCadProd = new JButton("Voltar Cadastro Produto");
		jbtnCadProd.setBounds(10, 666, 157, 23);
		jbtnCadProd.setBackground(new Color(192, 192, 192));
		jbtnCadProd.setFocusPainted(false);
		jbtnCadProd.setBorder(null);
		jbtnCadProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCreateView tlCadProdu = new ProductCreateView();
				tlCadProdu.setVisible(true);
				dispose();
			}
		});
		contentPane.add(jbtnCadProd);
		
		//Listando as categorias
				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				model.addColumn("ID");
				model.addColumn("NOME");
				model.addColumn("DESCRIÇÃO");
				model.addColumn("PREÇO");
				
				jtableProduto = new JTable(model);
				//Ajustando tamanhos das colunas
				jtableProduto.getColumnModel().getColumn(0).setPreferredWidth(30);
				jtableProduto.getColumnModel().getColumn(1).setPreferredWidth(170);
				jtableProduto.getColumnModel().getColumn(2).setPreferredWidth(300);
				jtableProduto.getColumnModel().getColumn(3).setPreferredWidth(50);
				
				//Centralizando os textos de cada coluna
				DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
				centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				jtableProduto.getColumnModel().getColumn(0).setCellRenderer(centro);
				jtableProduto.getColumnModel().getColumn(1).setCellRenderer(centro);
				jtableProduto.getColumnModel().getColumn(2).setCellRenderer(centro);
				jtableProduto.getColumnModel().getColumn(3).setCellRenderer(centro);
				
				//impedindo o redimensionamento e movimentação das colunas
				jtableProduto.getTableHeader().setResizingAllowed(false);
				jtableProduto.getTableHeader().setReorderingAllowed(false);
				
				JScrollPane jscrollPaneProduto = new JScrollPane(jtableProduto);
				jscrollPaneProduto.setBounds(10,58,880,455);
				contentPane.add(jscrollPaneProduto);
				
				//Criando o combobox e fazendo algumas definições 
				JComboBox<ItemCategory> comboBoxCategoria = new JComboBox<>();
				comboBoxCategoria.setBounds(628, 25, 262, 22);
				//Adicionando o combobox ao painel de interface
				contentPane.add(comboBoxCategoria);
				//Preenchendo o combobox com as categorias ou dados atraves da Classe (ItemCategoria)
				ItemCategory.comboCategorias(comboBoxCategoria);
				//Adicionando um ouvinte de eventos que reage ao selecionar uma categoria
				comboBoxCategoria.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Salva o item selecionado do combobox e passa para o ItemCategoria
						ItemCategory categorias = (ItemCategory) comboBoxCategoria.getSelectedItem();
						//Pega o id da categoria selecionada
						int idCategoria = categorias.getId();
						//Limpa as linhas da tabela antes de adicionar as informações da categoria selecionada
						model.setRowCount(0);
						ProductDAO ddproduto = new ProductDAO();
						//Pega (busca) os produtos da categoria selecionada
						List<Product> listProdCateg = ddproduto.listarProdutosCategoria(idCategoria);
						//Adiciona cada produto a tabela de acordo com as odem das colunas
						for (Product p : listProdCateg) {
							model.addRow(new Object[] {
									p.getIdProduto(),
									p.getNome(),
									p.getDescricao(),
									p.getPreco()
							});
							
						}
						
					}
				});
				
				
				
				
				JLabel jlblCateProd = new JLabel("Selecione a categoria que deseja listar os produtos: ");
				jlblCateProd.setBounds(342, 29, 262, 14);
				contentPane.add(jlblCateProd);
				
				JButton jbtnEditar = new JButton("Editar Produto");
				jbtnEditar.setBounds(10, 524, 181, 49);
				contentPane.add(jbtnEditar);
				jbtnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int linha = jtableProduto.getSelectedRow();
						if(linha != -1) {
							idLinha = (int) jtableProduto.getValueAt(linha, 0);
							String nome = (String) jtableProduto.getValueAt(linha, 1);
							String descricao = (String) jtableProduto.getValueAt(linha, 2);
							BigDecimal preco = (BigDecimal) jtableProduto.getValueAt(linha, 3);
							model.setRowCount(0);
							ProductEditView telaEditar = new ProductEditView(idLinha, nome, descricao, preco);
							telaEditar.setVisible(true);
							
						}else {
							JOptionPane.showMessageDialog(null, "Não Peguei aqui");
						}
					}
				});
				
				JButton jbtnDelete = new JButton("Apagar Produto");
				jbtnDelete.setBounds(272, 524, 181, 49);
				contentPane.add(jbtnDelete);
				
				JTextArea jtxtAreaDesc = new JTextArea();
				jtxtAreaDesc.setBackground(new Color(255, 255, 255));
				jtxtAreaDesc.setFont(new Font("Arial", Font.BOLD, 16));
				jtxtAreaDesc.setEnabled(false);
				jtxtAreaDesc.setEditable(false);
				jtxtAreaDesc.setLineWrap(true);
				jtxtAreaDesc.setWrapStyleWord(true);
				jtxtAreaDesc.setBounds(917, 158, 360, 90);
				contentPane.add(jtxtAreaDesc);
				
				JLabel jlblLabelId = new JLabel("ID:");
				jlblLabelId.setFont(new Font("Arial", Font.BOLD, 16));
				jlblLabelId.setBounds(917, 58, 360, 14);
				contentPane.add(jlblLabelId);
				
				JLabel jlblLabelNome = new JLabel("Nome:");
				jlblLabelNome.setFont(new Font("Arial", Font.BOLD, 16));
				jlblLabelNome.setBounds(917, 104, 360, 14);
				contentPane.add(jlblLabelNome);
				
				JLabel jlblLabelPreco = new JLabel("Preço:");
				jlblLabelPreco.setFont(new Font("Arial", Font.BOLD, 16));
				jlblLabelPreco.setBounds(917, 291, 360, 14);
				contentPane.add(jlblLabelPreco);
				
				jbtnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int linha = jtableProduto.getSelectedRow();
						
						if (linha != -1) {
							idLinha = (int) jtableProduto.getValueAt(linha, 0);
							String nomeP = (String) jtableProduto.getValueAt(linha, 1);
							
							int op = JOptionPane.showConfirmDialog(null, "Deseja excluir o produto: "+nomeP+" ?","Confirmação", JOptionPane.YES_NO_OPTION);
							if (op == JOptionPane.YES_OPTION) {
								//ProductDAO ddproduto = new ProductDAO();
								//Product produto = new Product();
								//produto.setIdProduto(idLinha);
								//ddproduto.deleteProduto(produto);
								JOptionPane.showMessageDialog(null, "Produto '"+nomeP+"' excluido com sucesso!");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada!");
						}
					}
				});
				
				jtableProduto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent clickTable) {
						if (clickTable.getClickCount() == 1 && jtableProduto.getSelectedRow() != -1) {
							int linha = jtableProduto.getSelectedRow();
							int idP = (int) jtableProduto.getValueAt(linha, 0);
							String nomeP = (String) jtableProduto.getValueAt(linha, 1);
							String descP = (String) jtableProduto.getValueAt(linha, 2);
							BigDecimal precoP = (BigDecimal) jtableProduto.getValueAt(linha, 3);
							
							jlblLabelId.setText(String.valueOf("ID: "+idP));
						    jlblLabelNome.setText("Nome: "+nomeP);
						    jtxtAreaDesc.setText(descP);
						    jlblLabelPreco.setText(String.valueOf("Preço: "+precoP));
						    
						   
						}
					}
				});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				if (fixar != null) {
					setLocation(fixar);
				}
			}
		});
	}
}
