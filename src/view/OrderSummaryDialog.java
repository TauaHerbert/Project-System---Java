package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ItemCategory;
import Utils.ItemCboxAutoCompleted;
import Utils.MessageBox;
import Utils.ServiceOrder;
import daoLibrary.ClientDAO;
import daoLibrary.PaymentMethodDAO;
import modelsLibrary.Client;
import modelsLibrary.Order;
import modelsLibrary.OrderItem;
import modelsLibrary.OrderPayment;
import modelsLibrary.PaymentMethod;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class OrderSummaryDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable jtableResumo;
	private DefaultTableModel modeloResumo;
	private DefaultTableModel modeloCarrinho;
	private DefaultTableModel modeloFormaPagamento;
	private JFrame telaPedido;
	private BigDecimal totalAcumuladoP;
	private BigDecimal totalPagoParcial = BigDecimal.ZERO;
	private BigDecimal precoPgym = BigDecimal.ZERO;
	private BigDecimal restan = BigDecimal.ZERO;
	
	private JScrollPane scrollPane;
	private JScrollPane jscrollPaneFormaPagamento;
	
	private JLabel jlblTotalP;
	private JLabel jlblNomeClient;
	private JLabel jlblEmailClient;
	private JLabel jlblTelefoneClient;
	private JLabel jlblCpfClient;
	private JLabel jlblSelecioneClient;
	private JLabel jlblTotal;
	private JLabel jlblTitulo;
	private JLabel jlblFormaPagamento;
	private JLabel jlblTptalp;
	
	private JDateChooser jdateChooser;
	private Date data;
	private LocalDateTime dataCon;
	
	private JButton jbtnFinalizar;
	private JButton jbtnCancelar;
	private JButton jbtnFormaPagamento;

	private JComboBox<PaymentMethod> jcmboxFormaPagamento;
	private JComboBox<Client> jComboBoxClient;
	private ItemCboxAutoCompleted<Client> auto;
	private JTable jtableFormaPagamento;
	private JTextField jtxtvalor;
	
	private Client clienteSelecionado;
	private JLabel jlblObsDate;
	
	
	public static void main(String[] args) {
		try {
			OrderSummaryDialog dialog = new OrderSummaryDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OrderSummaryDialog(DefaultTableModel tabelaCarrinho, JFrame telaPedido, BigDecimal totalAcumulado, JLabel jlblTotalP) {
		this.modeloCarrinho = tabelaCarrinho;
		this.telaPedido = telaPedido;
		this.totalAcumuladoP = totalAcumulado;
		this.jlblTotalP = jlblTotalP;
		initComponents();
		carregarTabela(tabelaCarrinho);
		actionButtons();
	}
	
	public OrderSummaryDialog() {
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Resumo do Pedido");
		setBounds(100, 100, 950, 750);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setModal(true);
		getContentPane().setLayout(null);
		
		modeloResumo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		jtableResumo = new JTable(modeloResumo);
		scrollPane = new JScrollPane(jtableResumo);
		scrollPane.setBounds(23, 55, 917, 278);
		getContentPane().add(scrollPane);
		
		jbtnCancelar = new JButton("Cancelar Pedido");
		jbtnCancelar.setBounds(821, 705, 119, 34);
		getContentPane().add(jbtnCancelar);
		
		jbtnFinalizar = new JButton("Finalizar Pedido");
		jbtnFinalizar.setBounds(10, 705, 134, 34);
		getContentPane().add(jbtnFinalizar);
		
		jlblTitulo = new JLabel("Resumo do Pedido");
		jlblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTitulo.setFont(new Font("Arial Black", Font.BOLD, 20));
		jlblTitulo.setBounds(187, 11, 317, 46);
		getContentPane().add(jlblTitulo);
		
		jlblTotal = new JLabel("Total:");
		jlblTotal.setFont(new Font("Arial Black", Font.BOLD, 16));
		jlblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		jlblTotal.setBounds(750, 348, 190, 34);
		getContentPane().add(jlblTotal);
		jlblTotal.setText("Total: R$ "+totalAcumuladoP.toString());
		
		jComboBoxClient = new JComboBox<>();
		jComboBoxClient.setBounds(23, 380, 303, 22);
		jComboBoxClient.setEditable(true);
		getContentPane().add(jComboBoxClient);
		
		ClientDAO clDao = new ClientDAO();
		auto = new ItemCboxAutoCompleted<>();
		auto.ativarText(jComboBoxClient, texto -> clDao.listClientBuscName(texto));
		
		jlblNomeClient = new JLabel("Cliente:");
		jlblNomeClient.setFont(new Font("Verdana", Font.BOLD, 14));
		jlblNomeClient.setBounds(23, 426, 411, 14);
		getContentPane().add(jlblNomeClient);
		
		jlblEmailClient = new JLabel("Email:");
		jlblEmailClient.setFont(new Font("Verdana", Font.BOLD, 14));
		jlblEmailClient.setBounds(23, 466, 411, 14);
		getContentPane().add(jlblEmailClient);
		
		jlblTelefoneClient = new JLabel("Telefone:");
		jlblTelefoneClient.setFont(new Font("Verdana", Font.BOLD, 14));
		jlblTelefoneClient.setBounds(23, 507, 259, 14);
		getContentPane().add(jlblTelefoneClient);
		
		jlblCpfClient = new JLabel("CPF:");
		jlblCpfClient.setFont(new Font("Verdana", Font.BOLD, 14));
		jlblCpfClient.setBounds(23, 547, 259, 14);
		getContentPane().add(jlblCpfClient);
		
		jlblSelecioneClient = new JLabel("Selecione o Cliente:");
        jlblSelecioneClient.setFont(new Font("Verdana", Font.BOLD, 12));
        jlblSelecioneClient.setBounds(23, 360, 219, 14);
        getContentPane().add(jlblSelecioneClient);
        
        modeloFormaPagamento = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		modeloFormaPagamento.addColumn("ID");
		modeloFormaPagamento.addColumn("FORMA");
		modeloFormaPagamento.addColumn("VALOR PAGO");
        
        jtableFormaPagamento = new JTable(modeloFormaPagamento);
        jtableFormaPagamento.setBounds(611, 447, 330, 94);
        getContentPane().add(jtableFormaPagamento);
        jtableFormaPagamento.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtableFormaPagamento.getColumnModel().getColumn(1).setPreferredWidth(190);
        jtableFormaPagamento.getColumnModel().getColumn(2).setPreferredWidth(90);
        
        jscrollPaneFormaPagamento = new JScrollPane(jtableFormaPagamento);
        jscrollPaneFormaPagamento.setBounds(611, 447, 330, 95);
        getContentPane().add(jscrollPaneFormaPagamento);
        
        jcmboxFormaPagamento = new JComboBox<PaymentMethod>();
        jcmboxFormaPagamento.setBounds(611, 552, 172, 22);
        getContentPane().add(jcmboxFormaPagamento);
        PaymentMethodDAO.comboCategorias(jcmboxFormaPagamento);
        
        jbtnFormaPagamento = new JButton("Add+");
        jbtnFormaPagamento.setBounds(881, 552, 59, 23);
        getContentPane().add(jbtnFormaPagamento);
        
        jtxtvalor = new JTextField();
        jtxtvalor.setBounds(793, 553, 74, 20);
        getContentPane().add(jtxtvalor);
        jtxtvalor.setColumns(10);
        jtxtvalor.setText(totalAcumuladoP.toString());
        
        jlblFormaPagamento = new JLabel("Forma de Pagamento");
        jlblFormaPagamento.setHorizontalAlignment(SwingConstants.CENTER);
        jlblFormaPagamento.setFont(new Font("Verdana", Font.BOLD, 15));
        jlblFormaPagamento.setBounds(666, 426, 233, 14);
        getContentPane().add(jlblFormaPagamento);
        
        jlblTptalp = new JLabel("R$ ");
        jlblTptalp.setBounds(793, 584, 74, 14);
        getContentPane().add(jlblTptalp);
        jlblTptalp.setText("R$ "+totalAcumuladoP.toString());
        
        JLabel jlblData = new JLabel("Data: ");
        jlblData.setFont(new Font("Verdana", Font.BOLD, 14));
        jlblData.setBounds(23, 640, 46, 14);
        getContentPane().add(jlblData);
        
        jdateChooser = new JDateChooser();
        jdateChooser.setDateFormatString("dd/MM/yyyy");
        jdateChooser.setBounds(72, 632, 190, 32);
        getContentPane().add(jdateChooser);
        jdateChooser.setDate(new Date());
        data = new Date();
        data = jdateChooser.getDate();
        
        dataCon = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        jlblObsDate = new JLabel("Obs: Selecione a data correta.");
        jlblObsDate.setFont(new Font("Verdana", Font.BOLD, 11));
        jlblObsDate.setForeground(new Color(0, 0, 255));
        jlblObsDate.setBounds(23, 605, 239, 14);
        getContentPane().add(jlblObsDate);
        ((JTextField) jdateChooser.getDateEditor().getUiComponent()).setEditable(false);
    }
	
	public void actionButtons() {
		
		jtableFormaPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clickTable) {
				if (clickTable.getClickCount() == 2 && jtableFormaPagamento.getSelectedRow() != -1) {
					int linha = jtableFormaPagamento.getSelectedRow();
					String desc = (String) jtableFormaPagamento.getValueAt(linha, 1);
					BigDecimal valor = (BigDecimal) jtableFormaPagamento.getValueAt(linha, 2);
					int res = JOptionPane.showConfirmDialog(null, "Deseja remover esta forma de pagamento: "+desc+" R$ "+valor,"Confirmação",JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						totalPagoParcial = totalPagoParcial.subtract(valor);
						modeloFormaPagamento.removeRow(linha);
						verificarFormPagm();
						jlblTptalp.setText("R$ "+restan.toString());
					}
				}
			}
		});
		
		 jbtnFormaPagamento.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		PaymentMethod itemPaym = (PaymentMethod) jcmboxFormaPagamento.getSelectedItem();
	        		int idPagm = itemPaym.getIdFormaPagamento();
	        		String descPagm = itemPaym.getDescricao();
	        		//BigDecimal precoPgym;
	        		restan = totalAcumuladoP;
	        		if (jtxtvalor.getText().equals("")) {
						MessageBox.messageShow("Informe o valor do pagamento");
						return;
					}
	        		try {
	        			String preco = jtxtvalor.getText().replace(",", ".");
	        			precoPgym = new BigDecimal(preco);
					}catch(NumberFormatException efx) {
						MessageBox.messageShow("Preço onformado invalido! Use apenas números.");
						return;
					}
	        		
	        		if (totalPagoParcial.add(precoPgym).compareTo(totalAcumuladoP) > 0) {
						MessageBox.messageShow("O valor informado ultrapassa o valor total do pedido!");
						return;
	        		}
	        		
	        		totalPagoParcial = totalPagoParcial.add(precoPgym);
	        		
	        		restan = totalAcumuladoP.subtract(totalPagoParcial);
	        		jlblTptalp.setText("R$ "+restan.toString());
	        		
	        		verificarFormPagm();
	        		
	        		modeloFormaPagamento.addRow(new Object[] {
	        				idPagm,
	        				descPagm,
	        				precoPgym
	        		});
	        		jtxtvalor.setText("");
	        	}
	        });
		
		jbtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cancelar = JOptionPane.showConfirmDialog(null, "Deseja cancelar a compra?","Cancelar compra",JOptionPane.YES_NO_OPTION);
				if (cancelar == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Compra Cancelada");
					dispose();
				}
				
			}
		});
		
		jbtnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int finalizar = JOptionPane.showConfirmDialog(null, "Deseja Finalizar a compra?","Finalizar Compra", JOptionPane.YES_NO_OPTION);
					if (finalizar != JOptionPane.YES_OPTION) {
						return;
					}
					if (clienteSelecionado == null) {
						MessageBox.messageShow("Selecione o cliente antes de finalizar o pedido!");
						return;
					}
					if (totalPagoParcial.compareTo(totalAcumuladoP) < 0) {
						MessageBox.messageShow("O pagamento ainda não cobre o valor total do pedido!");
						return;
					}
					
					//Objeto Order recebendo os dados do pedido
					Order pedido = new Order();
					pedido.setIdCliente(clienteSelecionado.getIdCliente());
					pedido.setData(dataCon);
					pedido.setTotal(totalAcumuladoP);
					
					//Adicionando os itens do carrinho e criando a lista da OrderItem
					List<OrderItem> itensOrder = new ArrayList<>();
					for (int i = 0; i < modeloCarrinho.getRowCount(); i++) {
						OrderItem itemOrder = new OrderItem();
						itemOrder.setIdProduto(Integer.parseInt(modeloCarrinho.getValueAt(i, 0).toString()));
						itemOrder.setQuantidade(Integer.parseInt(modeloCarrinho.getValueAt(i, 3).toString()));
						itemOrder.setPrecoUnitario(new BigDecimal(modeloCarrinho.getValueAt(i, 2).toString()));
						itensOrder.add(itemOrder);
					}
					
					//Adicionando os pagamentos e criando a OrderPayment
					List<OrderPayment> pagamentos = new ArrayList<>();
					for (int i = 0; i < modeloFormaPagamento.getRowCount(); i++) {
						OrderPayment pagamento = new OrderPayment();
						pagamento.setId_pagamento(Integer.parseInt(modeloFormaPagamento.getValueAt(i, 0).toString()));
						pagamento.setValor_pago(new BigDecimal(modeloFormaPagamento.getValueAt(i, 2).toString()));
						pagamentos.add(pagamento);
					}
					
					//Chamando a classe ServiceOrder para finalizar a operação
					ServiceOrder serviceOrder = new ServiceOrder();
					int idPedido = serviceOrder.finalizarPedido(pedido, itensOrder, pagamentos);
					//dadosTableResumo();
						
					JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso!");
					modeloCarrinho.setRowCount(0);
					totalAcumuladoP = BigDecimal.ZERO;
					if (jlblTotalP != null) {
						jlblTotalP.setText("Total:");
					}
					dispose();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					MessageBox.messageShow("Erro ao finalizar pedido! ERRO: "+ex.getMessage());
				}
			}
		});
		
		jComboBoxClient.addActionListener(e -> {
			if (!auto.isUpdating() && jComboBoxClient.isPopupVisible()) {
				clienteSelecionado = (Client) jComboBoxClient.getSelectedItem();
			    if (clienteSelecionado != null) {
			        JOptionPane.showMessageDialog(null, clienteSelecionado.getNome());
			        jlblNomeClient.setText("CLIENTE: "+clienteSelecionado.getNome());
			        jlblEmailClient.setText("EMAIL: "+clienteSelecionado.getEmail());
			        jlblTelefoneClient.setText("TELEFONE: "+clienteSelecionado.getTelefone());
			        jlblCpfClient.setText("CPF: "+clienteSelecionado.getCpf());
			     }
			}
		});
	}
	
	private void dadosTableResumo() {
		
		for (int i = 0; i < modeloResumo.getRowCount(); i++) {
			Object colum1_id = modeloResumo.getValueAt(i, 0);
			Object colum2_nome = modeloResumo.getValueAt(i, 1);
			Object colum3_precoU = modeloResumo.getValueAt(i, 2);
			Object colum4_qtd = modeloResumo.getValueAt(i, 3);
			
			MessageBox.messageShow("ID: "+colum1_id+" NOME: "+colum2_nome+" PREÇO UNITA.: "+colum3_precoU+" QTD: "+colum4_qtd);
		}
	}
	
	private void carregarTabela(DefaultTableModel tabelaCarrinho) {
		modeloResumo.setRowCount(0);
		modeloResumo.setColumnCount(0);
		
		for (int i = 0; i < tabelaCarrinho.getColumnCount(); i++) {
			modeloResumo.addColumn(tabelaCarrinho.getColumnName(i));
	    }
		
		for (int i = 0; i < tabelaCarrinho.getRowCount(); i++) {
	        Object[] row = new Object[tabelaCarrinho.getColumnCount()];
	        	
	        for (int j = 0; j < tabelaCarrinho.getColumnCount(); j++) {
	        		row[j] = tabelaCarrinho.getValueAt(i, j);
	        	}
	        modeloResumo.addRow(row);
	    }
		
		jtableResumo.getColumnModel().getColumn(0).setPreferredWidth(40);
		jtableResumo.getColumnModel().getColumn(1).setPreferredWidth(215);
		jtableResumo.getColumnModel().getColumn(2).setPreferredWidth(300);
		jtableResumo.getColumnModel().getColumn(3).setPreferredWidth(75);
		
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtableResumo.getColumnModel().getColumn(0).setCellRenderer(centro);
		jtableResumo.getColumnModel().getColumn(1).setCellRenderer(centro);
		jtableResumo.getColumnModel().getColumn(2).setCellRenderer(centro);
		jtableResumo.getColumnModel().getColumn(3).setCellRenderer(centro);
		
		jtableResumo.getTableHeader().setResizingAllowed(false);
		jtableResumo.getTableHeader().setReorderingAllowed(false);
	}
	
	public void verificarFormPagm() {
		restan = totalAcumuladoP.subtract(totalPagoParcial);
		
		if (restan.compareTo(BigDecimal.ZERO) == 0) {
			jbtnFormaPagamento.setEnabled(false);
			jtxtvalor.setEnabled(false);
			jcmboxFormaPagamento.setEnabled(false);
		}else {
			jbtnFormaPagamento.setEnabled(true);
			jtxtvalor.setEnabled(true);
			jcmboxFormaPagamento.setEnabled(true);
		}
	}
}
