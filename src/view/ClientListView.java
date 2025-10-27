package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.MessageBox;
import modelsLibrary.Client;

import daoLibrary.ClientDAO;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ClientListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jtableCliente;
	
	private DefaultTableModel modelClient;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientListView frame = new ClientListView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientListView() {
		initComponents();
		carregarListaClientes();
	}

	public void initComponents() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		modelClient = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
		
		modelClient.addColumn("ID");
		modelClient.addColumn("NOME");
		modelClient.addColumn("EMAIL");
		modelClient.addColumn("TELEFONE");
		modelClient.addColumn("CPF");
		
		jtableCliente = new JTable(modelClient);
		jtableCliente.setBounds(39, 109, 1050, 461);
		jtableCliente.getTableHeader().setResizingAllowed(false);
		jtableCliente.getTableHeader().setReorderingAllowed(false);
		contentPane.add(jtableCliente);
		
		jtableCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtableCliente.getColumnModel().getColumn(1).setPreferredWidth(250);
		jtableCliente.getColumnModel().getColumn(2).setPreferredWidth(450);
		jtableCliente.getColumnModel().getColumn(3).setPreferredWidth(175);
		jtableCliente.getColumnModel().getColumn(4).setPreferredWidth(175);
		
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtableCliente.getColumnModel().getColumn(0).setCellRenderer(centro);
		jtableCliente.getColumnModel().getColumn(1).setCellRenderer(centro);
		jtableCliente.getColumnModel().getColumn(2).setCellRenderer(centro);
		jtableCliente.getColumnModel().getColumn(3).setCellRenderer(centro);
		jtableCliente.getColumnModel().getColumn(4).setCellRenderer(centro);
		
		JScrollPane jscrollPaneCliente = new JScrollPane(jtableCliente);
		jscrollPaneCliente.setBounds(40, 84, 1100, 480);
		contentPane.add(jscrollPaneCliente);
		
		JButton jbtnVoltar = new JButton("Voltar");
		jbtnVoltar.setBounds(1228, 666, 62, 23);
		jbtnVoltar.setBackground(new Color(192, 192, 192));
		jbtnVoltar.setFocusPainted(false);
		jbtnVoltar.setBorder(null);
		jbtnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tlprincipal = new MainView();
				tlprincipal.setVisible(true);
				dispose();
			}
		});
		contentPane.add(jbtnVoltar);
		
		JButton jbtnEditarCliente = new JButton("Editar Cliente");
		jbtnEditarCliente.setBounds(40, 593, 254, 65);;
		jbtnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = jtableCliente.getSelectedRow();
				if (linha != -1) {
					int idClient = (int) jtableCliente.getValueAt(linha, 0);
					String nomeClient = (String) jtableCliente.getValueAt(linha, 1);
					String emailClient = (String) jtableCliente.getValueAt(linha, 2);
					String telefoneClient = (String) jtableCliente.getValueAt(linha, 3);
					String cpfClient = (String) jtableCliente.getValueAt(linha, 4);
					
					ClientEditView editarClient = new ClientEditView(idClient, nomeClient, emailClient, telefoneClient, cpfClient);
					editarClient.setVisible(true);
					dispose();
				}else {
					MessageBox.messageShow("Selecione um Cliente!");
				}
			}
		});
		contentPane.add(jbtnEditarCliente);
		
		JButton jbtnRemoverCliente = new JButton("Remover Cliente");
		jbtnRemoverCliente.setBounds(523, 593, 254, 65);
		contentPane.add(jbtnRemoverCliente);
	}
	
	public void carregarListaClientes() {
		
		DefaultTableModel model = (DefaultTableModel) jtableCliente.getModel();
		model.setRowCount(0);
		ClientDAO clientedados = new ClientDAO();
		List<Client> listaC = clientedados.selectAllCliente();
		
		 for(Client c : listaC) {
			 model.addRow(new Object[] {
					 c.getIdCliente(),
					 c.getNome(),
					 c.getEmail(),
					 c.getTelefone(),
					 c.getCpf()
			 });
		 }
	}
}
