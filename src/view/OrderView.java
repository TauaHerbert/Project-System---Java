package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ItemCategory;
import Utils.MessageBox;
import daoLibrary.ProductDAO;
import modelsLibrary.Product;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class OrderView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable jtableProduto;
	private JTable jtableCarrinho;
	private JTextField jtextFieldPrecoU;
	private JTextField jtextFieldQuant;
	private BigDecimal totalAcumulado = BigDecimal.ZERO;

	private JButton jbtnxitPedido;
	private JButton btnAddCarrinho;
	private JButton jbtnFinalizarCompra;
	private JButton jbtnRemoverItem;
	
	private JLabel jlblLabelNome;
	private JLabel jlblLabelId;
	private JLabel jlblLabelDescri;
	private JLabel jlblLabelPrecoUni;
	private JLabel jlblLabelQuan;
	private JLabel jlblNewLabelId;
	private JLabel jlblNewLabelNome;
	private JLabel jlblNewLabelDesc;
	private JLabel lblLabelCarrinho;
	private JLabel jlblTotalP;
	
	private JScrollPane jscrollPaneProduto;
	private JScrollPane scrollPaneCarrinho;
	
	private DefaultTableModel modelProdutos;
	private DefaultTableModel modelCarrinho;
	private DefaultTableCellRenderer centro;
	private JComboBox<ItemCategory> jcomboBoxCategoria;
	private JLabel jlblTitulo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderView frame = new OrderView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public OrderView() {
		initComponents();
		actionListenerClickendModel();
		actionListnerButtons();
		ButtonsCarrinFalse();
	}

	//Criação dos componentes
	public void initComponents() {
		setType(Type.UTILITY);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		jbtnxitPedido = new JButton("Exit");
		jbtnxitPedido.setBounds(1201, 666, 89, 23);
		jbtnxitPedido.setBackground(new Color(192, 192, 192));
		jbtnxitPedido.setFocusPainted(false);
		jbtnxitPedido.setBorder(null);
		getContentPane().add(jbtnxitPedido);
		
		modelProdutos = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelProdutos.addColumn("ID");
		modelProdutos.addColumn("NOME");
		modelProdutos.addColumn("DESCRIÇÃO");
		modelProdutos.addColumn("PREÇO");
		
		jtableProduto = new JTable(modelProdutos);
		jtableProduto.setBounds(10, 72, 399, 602);
		getContentPane().add(jtableProduto);
		
		jscrollPaneProduto = new JScrollPane(jtableProduto);
		jscrollPaneProduto.setBounds(10, 64, 630, 370);
		getContentPane().add(jscrollPaneProduto);
		
		jtableProduto.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtableProduto.getColumnModel().getColumn(1).setPreferredWidth(215);
		jtableProduto.getColumnModel().getColumn(2).setPreferredWidth(320);
		jtableProduto.getColumnModel().getColumn(3).setPreferredWidth(55);
		
		centerCellRenderer(jtableProduto);
		
		jtableProduto.getTableHeader().setResizingAllowed(false);
		jtableProduto.getTableHeader().setReorderingAllowed(false);
		
		jcomboBoxCategoria = new JComboBox<>();
		jcomboBoxCategoria.setBounds(257, 26, 383, 22);
		getContentPane().add(jcomboBoxCategoria);
		ItemCategory.comboCategorias(jcomboBoxCategoria);
		
		jlblLabelNome = new JLabel("Nome: ");
		jlblLabelNome.setFont(new Font("Arial Black", Font.BOLD, 17));
		jlblLabelNome.setBounds(45, 498, 89, 23);
		getContentPane().add(jlblLabelNome);
		
		jlblLabelId = new JLabel("Id Produto:");
		jlblLabelId.setFont(new Font("Arial Black", Font.BOLD, 17));
		jlblLabelId.setBounds(45, 457, 135, 23);
		getContentPane().add(jlblLabelId);
		
		jlblLabelDescri = new JLabel("Descrição:");
		jlblLabelDescri.setFont(new Font("Arial Black", Font.BOLD, 17));
		jlblLabelDescri.setBounds(45, 542, 135, 23);
		getContentPane().add(jlblLabelDescri);
		
		jlblLabelPrecoUni = new JLabel("Preço Unitario: ");
		jlblLabelPrecoUni.setFont(new Font("Arial Black", Font.BOLD, 17));
		jlblLabelPrecoUni.setBounds(45, 583, 180, 23);
		getContentPane().add(jlblLabelPrecoUni);
		
		jlblLabelQuan = new JLabel("Quantidade: ");
		jlblLabelQuan.setFont(new Font("Arial Black", Font.BOLD, 17));
		jlblLabelQuan.setBounds(45, 626, 145, 23);
		getContentPane().add(jlblLabelQuan);
		
		jlblNewLabelId = new JLabel("");
		jlblNewLabelId.setBounds(167, 464, 86, 14);
		jlblNewLabelId.setVisible(false);
		getContentPane().add(jlblNewLabelId);
		
		jlblNewLabelNome = new JLabel("");
		jlblNewLabelNome.setBounds(120, 505, 371, 14);
		jlblNewLabelNome.setVisible(false);
		getContentPane().add(jlblNewLabelNome);
		
		jlblNewLabelDesc = new JLabel("");
		jlblNewLabelDesc.setBounds(167, 549, 478, 14);
		jlblNewLabelDesc.setVisible(false);
		getContentPane().add(jlblNewLabelDesc);
		
		jtextFieldPrecoU = new JTextField();
		jtextFieldPrecoU.setBounds(208, 587, 86, 20);
		getContentPane().add(jtextFieldPrecoU);
		jtextFieldPrecoU.setColumns(10);
		
		jtextFieldQuant = new JTextField();
		jtextFieldQuant.setBounds(167, 630, 86, 20);
		getContentPane().add(jtextFieldQuant);
		jtextFieldQuant.setColumns(10);
		
		modelCarrinho = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		modelCarrinho.addColumn("ID");
		modelCarrinho.addColumn("NOME");
		modelCarrinho.addColumn("PREÇO UNITARIO");
		modelCarrinho.addColumn("QUANTIDADE");
		
		jtableCarrinho = new JTable(modelCarrinho);
		jtableCarrinho.setBounds(763, 65, 527, 370);
		getContentPane().add(jtableCarrinho);
		
		scrollPaneCarrinho = new JScrollPane(jtableCarrinho);
		scrollPaneCarrinho.setBounds(810, 64, 480, 370);
		getContentPane().add(scrollPaneCarrinho);
		
		jtableCarrinho.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtableCarrinho.getColumnModel().getColumn(1).setPreferredWidth(230);
		jtableCarrinho.getColumnModel().getColumn(2).setPreferredWidth(110);
		jtableCarrinho.getColumnModel().getColumn(3).setPreferredWidth(90);
		
		centerCellRenderer(jtableCarrinho);
		
		jtableCarrinho.getTableHeader().setResizingAllowed(false);
		jtableCarrinho.getTableHeader().setReorderingAllowed(false);
	
		btnAddCarrinho = new JButton("Adicionar ao Carrinho");
		btnAddCarrinho.setBounds(470, 590, 171, 59);
		getContentPane().add(btnAddCarrinho);
		
		lblLabelCarrinho = new JLabel("Carrinho");
		lblLabelCarrinho.setFont(new Font("Arial", Font.BOLD, 25));
		lblLabelCarrinho.setBounds(997, 17, 135, 33);
		getContentPane().add(lblLabelCarrinho);
		
		jlblTotalP = new JLabel("Total:");
		jlblTotalP.setFont(new Font("Arial Black", Font.BOLD, 16));
		jlblTotalP.setBounds(1088, 464, 202, 33);
		getContentPane().add(jlblTotalP);
		
		jbtnFinalizarCompra = new JButton("Finalizar Carrinho ");
		jbtnFinalizarCompra.setBounds(810, 527, 145, 59);
		getContentPane().add(jbtnFinalizarCompra);
		
		jbtnRemoverItem = new JButton("Remover Item");
		jbtnRemoverItem.setBounds(997, 527, 125, 59);
		getContentPane().add(jbtnRemoverItem);
		
		jlblTitulo = new JLabel("Categoria / Produtos:");
		jlblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
		jlblTitulo.setBounds(83, 26, 145, 22);
		getContentPane().add(jlblTitulo);
	}
	//Centralizando as colunas das tabelas
	public void centerCellRenderer(JTable tabelas) {
		centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		for (int i = 0; i < tabelas.getColumnCount(); i++) {
			tabelas.getColumnModel().getColumn(i).setCellRenderer(centro);
		}
	}
	//Outros tipos de ações (click, tabela...)
	public void actionListenerClickendModel() {
		
		jcomboBoxCategoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemCategory itemCate = (ItemCategory) jcomboBoxCategoria.getSelectedItem();
				int idCate = itemCate.getId();
				modelProdutos.setRowCount(0);
				ProductDAO ddproduto = new ProductDAO();
				List<Product> listProduto = ddproduto.listarProdutosCategoria(idCate);
				for (Product p : listProduto) {
					modelProdutos.addRow(new Object[] {
						p.getIdProduto(),
						p.getNome(),
						p.getDescricao(),
						p.getPreco()
					});
				}
			}
		});
		
		jtableProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clickTable) {
				if (clickTable.getClickCount() == 2 && jtableProduto.getSelectedRow() != -1) {
					int linha = jtableProduto.getSelectedRow();
					int idP = (int) jtableProduto.getValueAt(linha, 0);
					String nomeP = (String) jtableProduto.getValueAt(linha, 1);
					String descP = (String) jtableProduto.getValueAt(linha, 2);
					BigDecimal precoP = (BigDecimal) jtableProduto.getValueAt(linha, 3);
					
					jlblNewLabelId.setText(String.valueOf(idP));
				    jlblNewLabelNome.setText(nomeP);
				    jlblNewLabelDesc.setText(descP);
				    jtextFieldPrecoU.setText(String.valueOf(precoP));
				    
				    jlblNewLabelId.setVisible(true);
				    jlblNewLabelNome.setVisible(true);
				    jlblNewLabelDesc.setVisible(true);
				}
			}
		});
		
		modelCarrinho.addTableModelListener(e ->{
			//habilitar e desabilitar os botoes de acordo com a tabela carrinho estando com ou sem produtos adicionado 
			if (jtableCarrinho.getRowCount() > 0) {
				ButtonsCarrinTrue();
			} else {
				ButtonsCarrinFalse();
			}
			//Atualizando o total da tabela carrinho de acordo com os produtos adicionados
			if (e.getType() == javax.swing.event.TableModelEvent.INSERT) {
				int linhaCarrinho = e.getFirstRow();
				
				try {
					Object quantidade = modelCarrinho.getValueAt(linhaCarrinho, 3);
					Object precoUnitario = modelCarrinho.getValueAt(linhaCarrinho, 2);
					
					int quant = Integer.parseInt(quantidade.toString());
					BigDecimal precoU = new BigDecimal(precoUnitario.toString());
					
					BigDecimal subtotal = precoU.multiply(BigDecimal.valueOf(quant));
					
					totalAcumulado = totalAcumulado.add(subtotal);
					
					jlblTotalP.setText("Total: R$ "+totalAcumulado.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				}
		});
	}
	//Ação dos botões
	public void actionListnerButtons() {
		
		btnAddCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String idP = jlblNewLabelId.getText();
				String nomeP = jlblNewLabelNome.getText();
				String descP = jlblNewLabelDesc.getText();
				
				if (idP == null || idP.trim().isEmpty()) {
					MessageBox.messageShow("Produto sem ID, selecione o produto na tabela!");
					return;
				}
				if (nomeP == null || nomeP.trim().isEmpty()) {
					MessageBox.messageShow("Produto sem Nome, selecione o produto na tabela!");
					return;
				}
				if (descP == null || descP.trim().isEmpty()) {
					MessageBox.messageShow("Produto sem Descrição, selecione o produto na tabela!");
					return;
				}
				if(jtextFieldPrecoU.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Campo preço vazio, preencha corretamente!");
					return;
				}else if(jtextFieldQuant.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null, "Campo quantidade vazio, preencha corretamente!");
					return;
				}
				try {
					Double.parseDouble(jtextFieldPrecoU.getText());
					Integer.parseInt(jtextFieldQuant.getText());
				}catch(NumberFormatException efx) {
					JOptionPane.showMessageDialog(null, "Preço ou quantidade inválidos! Use apenas números.");
					return;
				}
				modelCarrinho.addRow(new Object[] {
						jlblNewLabelId.getText(),
					    jlblNewLabelNome.getText(),
					    jtextFieldPrecoU.getText(),
					    jtextFieldQuant.getText()
				});
				jlblNewLabelId.setText("");
			    jlblNewLabelNome.setText("");
			    jlblNewLabelDesc.setText("");
			    jtextFieldPrecoU.setText("");
			    jtextFieldQuant.setText("");
			  }
		});
		
		jbtnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modeloCarrinho = (DefaultTableModel) jtableCarrinho.getModel();
				OrderSummaryDialog tlresumo = new OrderSummaryDialog(modeloCarrinho, OrderView.this, totalAcumulado, jlblTotalP);
				tlresumo.setVisible(true);
			}
		});
		
		jbtnRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaC = jtableCarrinho.getSelectedRow();
				if (linhaC != -1) {
					int conf = JOptionPane.showConfirmDialog(null, "Deseja remover este item?","Confirmação",JOptionPane.YES_NO_OPTION);
					if (conf == JOptionPane.YES_OPTION) {
						Object quantidade = modelCarrinho.getValueAt(linhaC, 3);
						Object precoUnitario = modelCarrinho.getValueAt(linhaC, 2);
						
						int quant = Integer.parseInt(quantidade.toString());
						BigDecimal precoU = new BigDecimal(precoUnitario.toString());
						
						BigDecimal subtotal = precoU.multiply(BigDecimal.valueOf(quant));
						
						totalAcumulado = totalAcumulado.subtract(subtotal);
						
						jlblTotalP.setText("Total: R$ "+totalAcumulado.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
						modelCarrinho.removeRow(linhaC);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Nenhum item selecionado!");
				}
			}
		});
		
		jbtnxitPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tlprincipal = new MainView();
				tlprincipal.setVisible(true);
				dispose();
			}
		});
	}
	
	public void ButtonsCarrinTrue() {
		jbtnFinalizarCompra.setEnabled(true);
		jbtnRemoverItem.setEnabled(true);
	}
	public void ButtonsCarrinFalse() {
		jbtnFinalizarCompra.setEnabled(false);
		jbtnRemoverItem.setEnabled(false);
	}
}
