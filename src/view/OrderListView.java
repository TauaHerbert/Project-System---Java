package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import Utils.MessageBox;
import daoLibrary.OrderDAO;
import modelsLibrary.Order;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
public class OrderListView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JDateChooser dateChooser;
	private Date data;

	private JButton jbtnMenu;
	private JButton jbtnBuscar;
	
	private DefaultTableModel modeloPedido;
	private DefaultTableCellRenderer centro;
	private JTable jtablePedidos;
	private JScrollPane jscrollPanePedidos;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderListView frame = new OrderListView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderListView() {
		initComponents();
		actionButtons();
		actionEvents();
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
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(10, 67, 190, 32);
		getContentPane().add(dateChooser);
		//dateChooser.getDateEditor().setEnabled(false);
		((JTextField) dateChooser.getDateEditor().getUiComponent()).setEditable(false);
		
		jbtnMenu = new JButton("Voltar Menu");
		jbtnMenu.setBounds(1201, 666, 89, 23);
		getContentPane().add(jbtnMenu);
		
		modeloPedido = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		modeloPedido.addColumn("ID");
		modeloPedido.addColumn("DATA");
		modeloPedido.addColumn("CLIENTE");
		modeloPedido.addColumn("VALOR TOTAL");
	
		jtablePedidos = new JTable(modeloPedido);
		jtablePedidos.setBounds(130, 241, 245, 115);
		getContentPane().add(jtablePedidos);
		
		centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		for (int i = 0; i < jtablePedidos.getColumnCount(); i++) {
			jtablePedidos.getColumnModel().getColumn(i).setCellRenderer(centro);
		}
		
		jtablePedidos.getTableHeader().setResizingAllowed(false);
		jtablePedidos.getTableHeader().setReorderingAllowed(false);
		
		jtablePedidos.getColumnModel().getColumn(0).setPreferredWidth(100);
		jtablePedidos.getColumnModel().getColumn(1).setPreferredWidth(200);
		jtablePedidos.getColumnModel().getColumn(2).setPreferredWidth(810);
		jtablePedidos.getColumnModel().getColumn(3).setPreferredWidth(170);
		
		jscrollPanePedidos = new JScrollPane(jtablePedidos);
		jscrollPanePedidos.setBounds(10, 130, 1280, 506);
		getContentPane().add(jscrollPanePedidos);
		
		jbtnBuscar = new JButton("Buscar");
		jbtnBuscar.setBounds(210, 67, 89, 32);
		getContentPane().add(jbtnBuscar);

		
	}
	
	public void actionButtons() {
		
		/*jbtnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data = dateChooser.getDate();
				MessageBox.messageShow("Data selecionada: "+data);
			}
		});*/
		
		jbtnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView menu = new MainView();
				menu.setVisible(true);
				dispose();
			}
		});
	}
	
	public void actionEvents() {
		dateChooser.getDateEditor().addPropertyChangeListener("date", evt -> {
			
			java.util.Date dataSelecionada = dateChooser.getDate();
			java.sql.Date datesql = new java.sql.Date(dataSelecionada.getTime());
			
			if(dataSelecionada != null) {
				JOptionPane.showMessageDialog(null, "Data selecionada: "+dataSelecionada);
				//JOptionPane.showMessageDialog(null, "Data selecionada: "+datesql);
			}
			
			carregarPedidosData(dataSelecionada);
		});
		
		jtablePedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clickTable) {
				if (clickTable.getClickCount() == 2 && jtablePedidos.getSelectedRow() != -1) {
					int linha = jtablePedidos.getSelectedRow();
					int idP = (int) jtablePedidos.getValueAt(linha, 0);
					
					OrderListEditView pedidoEdit = new OrderListEditView(idP);
					pedidoEdit.setVisible(true);
					dispose();
				}
			}
		});
	}
	
	public void carregarPedidosData(Date data) {
		
		if (data == null) {
			MessageBox.messageShow("Selecione uma data!");
			return;
		}
		
		modeloPedido.setRowCount(0);
		LocalDate dataConvertida = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		OrderDAO orderDao = new OrderDAO();
		List<Order> pedidos =  orderDao.selectPedidoCliente(dataConvertida);
		
		for (Order pedido : pedidos) {
			modeloPedido.addRow(new Object [] {
					pedido.getIdPedido(),
					pedido.getData(),
					pedido.getNomeC(),
					pedido.getTotal()
			});
		}
	}
}
