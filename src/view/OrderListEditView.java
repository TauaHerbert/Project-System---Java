package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import daoLibrary.OrderDAO;
import daoLibrary.OrderItemDAO;
import daoLibrary.OrderPaymentDAO;
import modelsLibrary.Order;
import modelsLibrary.OrderItem;
import modelsLibrary.OrderPayment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class OrderListEditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JDateChooser dateChooser;
	private Date dataConv;
	
	private JButton jbtnVoltar;
	
	private JLabel jlblIdpedido;
	private JLabel jlblTotal;
	private JLabel jlblIdCliente;
	private JLabel jlblNomeCliente;
	private JLabel jlblCpfCliente;
	private JLabel jlblEmailCliente;
	private JLabel jlblTelefoneCliente;
	private JLabel jlblTitulo;
	private JLabel jlblTituloMP;
	private JLabel jlblTituloDadosDoCliente;
	private JLabel jlblTituloDadosDoPedido;
	
	private JTable jtableItensPedido;
	private JScrollPane jscrollPaneItensPedido;
	private DefaultTableModel modeloItensPedido;
	private JTable jtablePagamentos;
	private JScrollPane jscrollPanePagamentos;
	private DefaultTableModel modeloPagamento;
	private DefaultTableCellRenderer centro;
	
	private int Id_pedido;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderListEditView frame = new OrderListEditView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public OrderListEditView() {
		initComponents();
		actionButtons();
	}

	public OrderListEditView(int IdPedido) {
		this.Id_pedido = IdPedido;
		initComponents();
		actionButtons();
		carregarDadosPedidoCliente(Id_pedido);
		carregarDadosItensProduto(Id_pedido);
		carregarDadosPagamentoPedido(Id_pedido);
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
		jbtnVoltar.setBounds(1201, 666, 89, 23);
		getContentPane().add(jbtnVoltar);
		
		jlblIdpedido = new JLabel("ID: ");
		jlblIdpedido.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblIdpedido.setBounds(36, 302, 163, 23);
		getContentPane().add(jlblIdpedido);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(36, 243, 139, 32);
		getContentPane().add(dateChooser);
		dateChooser.setEnabled(false);
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setEditable(false);
		
		jlblTotal = new JLabel("TOTAL: R$  ");
		jlblTotal.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblTotal.setBounds(1096, 302, 194, 23);
		getContentPane().add(jlblTotal);
		
		jlblIdCliente = new JLabel("ID:");
		jlblIdCliente.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblIdCliente.setBounds(341, 213, 194, 23);
		getContentPane().add(jlblIdCliente);
		
		jlblNomeCliente = new JLabel("NOME:");
		jlblNomeCliente.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblNomeCliente.setBounds(341, 174, 533, 23);
		getContentPane().add(jlblNomeCliente);
		
		jlblCpfCliente = new JLabel("CPF: ");
		jlblCpfCliente.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblCpfCliente.setBounds(550, 213, 271, 23);
		getContentPane().add(jlblCpfCliente);
		
		jlblEmailCliente = new JLabel("EMAIL: ");
		jlblEmailCliente.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblEmailCliente.setBounds(341, 259, 515, 23);
		getContentPane().add(jlblEmailCliente);
		
		jlblTelefoneCliente = new JLabel("TELEFONE:");
		jlblTelefoneCliente.setFont(new Font("Verdana", Font.PLAIN, 17));
		jlblTelefoneCliente.setBounds(341, 302, 322, 23);
		getContentPane().add(jlblTelefoneCliente);
		
		modeloItensPedido = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloItensPedido.addColumn("ID");
		modeloItensPedido.addColumn("NOME");
		modeloItensPedido.addColumn("DESCRIÇÃO");
		modeloItensPedido.addColumn("QUANTIDADE");
		modeloItensPedido.addColumn("VALOR DO PRODUTO");
		
		jtableItensPedido = new JTable(modeloItensPedido);
		jtableItensPedido.setBounds(36, 289, 585, 333);
		getContentPane().add(jtableItensPedido);
		
		centerCellRenderer(jtableItensPedido);
		
		jtableItensPedido.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtableItensPedido.getColumnModel().getColumn(1).setPreferredWidth(170);
		jtableItensPedido.getColumnModel().getColumn(2).setPreferredWidth(510);
		jtableItensPedido.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtableItensPedido.getColumnModel().getColumn(4).setPreferredWidth(120);
		
		jtableItensPedido.getTableHeader().setResizingAllowed(false);
		jtableItensPedido.getTableHeader().setReorderingAllowed(false);
		
		jscrollPaneItensPedido = new JScrollPane(jtableItensPedido);
		jscrollPaneItensPedido.setBounds(36, 336, 1254, 301);
		getContentPane().add(jscrollPaneItensPedido);
		
		modeloPagamento = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		modeloPagamento.addColumn("ID");
		modeloPagamento.addColumn("MODO DE PAGAMENTO");
		modeloPagamento.addColumn("VALOR PAGO");
		
		
		jtablePagamentos = new JTable(modeloPagamento);
		jtablePagamentos.setBounds(920, 102, 370, 140);
		getContentPane().add(jtablePagamentos);
		
		centerCellRenderer(jtablePagamentos);
		
		jtablePagamentos.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtablePagamentos.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtablePagamentos.getColumnModel().getColumn(2).setPreferredWidth(130);
		
		jtablePagamentos.getTableHeader().setResizingAllowed(false);
		jtablePagamentos.getTableHeader().setReorderingAllowed(false);
		
		jscrollPanePagamentos = new JScrollPane(jtablePagamentos);
		jscrollPanePagamentos.setBounds(920, 174, 370, 117);
		getContentPane().add(jscrollPanePagamentos);
		
		jlblTitulo = new JLabel("Informações do Pedido");
		jlblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
		jlblTitulo.setBounds(459, 11, 357, 46);
		getContentPane().add(jlblTitulo);
		
		jlblTituloMP = new JLabel("Modo de Pagamento");
		jlblTituloMP.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloMP.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlblTituloMP.setBounds(989, 140, 221, 23);
		getContentPane().add(jlblTituloMP);
		
		jlblTituloDadosDoCliente = new JLabel("Dados do Cliente");
		jlblTituloDadosDoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloDadosDoCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlblTituloDadosDoCliente.setBounds(459, 140, 221, 23);
		getContentPane().add(jlblTituloDadosDoCliente);
		
		jlblTituloDadosDoPedido = new JLabel("Dados do Pedido");
		jlblTituloDadosDoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloDadosDoPedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		jlblTituloDadosDoPedido.setBounds(10, 147, 221, 23);
		getContentPane().add(jlblTituloDadosDoPedido);
		
	}
	
	public void actionButtons() {
		jbtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderListView listpedidos = new OrderListView();
				listpedidos.setVisible(true);
				dispose();
			}
		});
	}
	
	public void centerCellRenderer(JTable tabelas) {
		centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		for (int i = 0; i < tabelas.getColumnCount(); i++) {
			tabelas.getColumnModel().getColumn(i).setCellRenderer(centro);
		}
	}
	
	public void carregarDadosPedidoCliente(int idPedido) {
		OrderDAO dadosPedido = new OrderDAO();
		Order pedido = dadosPedido.selectPdidoId(idPedido);
		
		jlblIdpedido.setText("ID: "+String.valueOf(pedido.getIdPedido()));
		//convertendo a data
		dataConv = Date.from(pedido.getData().atZone(ZoneId.systemDefault()).toInstant());
		dateChooser.setDate(dataConv);
		jlblTotal.setText("TOTAL: R$ "+String.valueOf(pedido.getTotal()));
		
		jlblIdCliente.setText("ID: "+String.valueOf(pedido.getCliente().getIdCliente()));
		jlblNomeCliente.setText("NOME: "+pedido.getCliente().getNome());
		jlblEmailCliente.setText("EMAIL: "+pedido.getCliente().getEmail());
		jlblCpfCliente.setText("CPF: "+pedido.getCliente().getCpf());
		jlblTelefoneCliente.setText("TELEFONE: "+pedido.getCliente().getTelefone());
	}
	
	public void carregarDadosItensProduto(int idPedido) {
		OrderItemDAO dadosItens = new OrderItemDAO();
		List<OrderItem> itens = dadosItens.selectItemPedido(idPedido);
		for (OrderItem iten : itens) {
			modeloItensPedido.addRow(new Object[] {
					iten.getProduto().getIdProduto(),
					iten.getProduto().getNome(),
					iten.getProduto().getDescricao(),
					iten.getQuantidade(),
					iten.getPrecoUnitario()
			});
		}
	}
	
	public void carregarDadosPagamentoPedido(int idPedido) {
		OrderPaymentDAO dadosPedidoPagamento = new OrderPaymentDAO();
		List<OrderPayment> itensPedidoPagamentos = dadosPedidoPagamento.selectFormaPagamentoPedido(idPedido);
		for (OrderPayment pedidoPagamento : itensPedidoPagamentos) {
			modeloPagamento.addRow(new Object[] {
					pedidoPagamento.getId_pagamento(),
					pedidoPagamento.getMetodoPagamento().getDescricao(),
					pedidoPagamento.getValor_pago()
			});
		}
	}
}
