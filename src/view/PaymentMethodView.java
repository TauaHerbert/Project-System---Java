package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.MessageBox;
import daoLibrary.PaymentMethodDAO;
import modelsLibrary.PaymentMethod;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PaymentMethodView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private int idFormaPagm;
	
	private JLabel jlblTituloView;
	private JLabel jlblDescricao;
	private JLabel jlblTituloDesc;
	private JLabel jlblTitDescEdit;
	private JLabel jlblDescEdt;
	
	private JButton jbtnClose;
	private JButton jbtnAddFormaPag;
	private JButton jbtnEditar;
	private JButton jbtnCancelar;
	
	private JTextField jtxtDescricao;
	private JTextField jtxtDescriEdit;
	
	private JTable jtableFormaPagamento;
	private DefaultTableModel model;
	private DefaultTableCellRenderer center;
	private JScrollPane jscrollPaneFormaPagmanto;
	private JLabel jlblTituloTable;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentMethodView frame = new PaymentMethodView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PaymentMethodView() {
		initComponents();
		actionButtons();
		carregarTabela();
		actionClick();
		EditVisibleFalse();
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
		getContentPane().setLayout(null);
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		model.addColumn("ID");
		model.addColumn("DESCRIÇÃO");
		
		jtableFormaPagamento = new JTable(model);
		jtableFormaPagamento.setBounds(715, 164, 305, 130);
		getContentPane().add(jtableFormaPagamento);
		
		jscrollPaneFormaPagmanto = new JScrollPane(jtableFormaPagamento);
		jscrollPaneFormaPagmanto.setBounds(784, 200, 305, 130);
		getContentPane().add(jscrollPaneFormaPagmanto);
		
		centerCellRenderer(jtableFormaPagamento);
		
		jtableFormaPagamento.getColumnModel().getColumn(0).setPreferredWidth(50);
		jtableFormaPagamento.getColumnModel().getColumn(1).setPreferredWidth(255);
		
		jtableFormaPagamento.getTableHeader().setResizingAllowed(false);
		jtableFormaPagamento.getTableHeader().setReorderingAllowed(false);
		
		jbtnClose = new JButton("Voltar");
		jbtnClose.setBounds(1222, 666, 68, 23);
		getContentPane().add(jbtnClose);
		
		jtxtDescricao = new JTextField();
		jtxtDescricao.setBounds(161, 209, 254, 20);
		getContentPane().add(jtxtDescricao);
		jtxtDescricao.setColumns(10);
		
		jlblTituloView = new JLabel("Area de Cadastro Forma de Pagamento");
		jlblTituloView.setFont(new Font("Arial Black", Font.BOLD, 25));
		jlblTituloView.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloView.setBounds(294, 41, 616, 35);
		getContentPane().add(jlblTituloView);
		
		jlblDescricao = new JLabel("Descreição:");
		jlblDescricao.setBounds(89, 212, 69, 14);
		getContentPane().add(jlblDescricao);
		
		jlblTituloDesc = new JLabel("Adicione a forma de Pagamento");
		jlblTituloDesc.setFont(new Font("Verdana", Font.BOLD, 15));
		jlblTituloDesc.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloDesc.setBounds(143, 164, 299, 23);
		getContentPane().add(jlblTituloDesc);
		
		jbtnAddFormaPag = new JButton("Adicionar Forma de Pagamento");
		jbtnAddFormaPag.setBounds(161, 240, 254, 23);
		getContentPane().add(jbtnAddFormaPag);
		
		jlblTituloTable = new JLabel("Formas de Pagamento");
		jlblTituloTable.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTituloTable.setFont(new Font("Verdana", Font.BOLD, 15));
		jlblTituloTable.setBounds(784, 164, 299, 23);
		getContentPane().add(jlblTituloTable);
		
		jtxtDescriEdit = new JTextField();
		jtxtDescriEdit.setBounds(784, 509, 305, 20);
		getContentPane().add(jtxtDescriEdit);
		jtxtDescriEdit.setColumns(10);
		
		jbtnEditar = new JButton("Editar");
		jbtnEditar.setBounds(783, 540, 89, 23);
		getContentPane().add(jbtnEditar);
		
		jbtnCancelar = new JButton("Cancelar");
		jbtnCancelar.setBounds(1000, 540, 89, 23);
		getContentPane().add(jbtnCancelar);
		
		jlblDescEdt = new JLabel("Descrição:");
		jlblDescEdt.setBounds(725, 512, 56, 14);
		getContentPane().add(jlblDescEdt);
		
		jlblTitDescEdit = new JLabel("Editar forma de Pagamento");
		jlblTitDescEdit.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTitDescEdit.setFont(new Font("Verdana", Font.BOLD, 15));
		jlblTitDescEdit.setBounds(784, 484, 305, 14);
		getContentPane().add(jlblTitDescEdit);
	}
	
	public void actionButtons() {
		
		jbtnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PaymentMethod pym = new PaymentMethod();
				PaymentMethodDAO pymDao = new PaymentMethodDAO();
				
				pym.setDescricao(jtxtDescriEdit.getText());
				pym.setIdFormaPagamento(idFormaPagm);
				
				if (jtxtDescriEdit.getText().equals("") || jtxtDescriEdit.getText().equals(null) ) {
					MessageBox.messageShow("Descrição incorreta!");
					return;
				}
				
				pymDao.updateFormaPagamento(pym);
				MessageBox.messageShow("Forma de pagamento atualizada!");
				jtxtDescriEdit.setText("");
				EditVisibleFalse();
				CadFormaPagamTrue();
				carregarTabela();
			}
		});
		
		jbtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtxtDescriEdit.setText("");
				EditVisibleFalse();
				CadFormaPagamTrue();
			}
		});
		
		jbtnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView mv = new MainView();
				mv.setVisible(true);
				dispose();
			}
		});
		
		jbtnAddFormaPag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentMethod pym = new PaymentMethod();
				PaymentMethodDAO pymDao = new PaymentMethodDAO();
				
				try {
					pym.setDescricao(jtxtDescricao.getText());
					
					if (jtxtDescricao.getText().equals("") || jtxtDescricao.getText().equals(null) ) {
						MessageBox.messageShow("Descrição incorreta!");
						return;
					}
					
					pymDao.insertFormaPagamento(pym);
					MessageBox.messageShow("Forma de pagamento cadastrada com sucesso!");
					carregarTabela();
					jtxtDescricao.setText("");
					
				} catch (Exception ex) {
					MessageBox.messageShow("Erro ao cadastrar forma de pagamento! ERRO: "+ex.getMessage());
				}
			}
		});
	}
	
	public void carregarTabela() {
		PaymentMethodDAO pmyDao = new PaymentMethodDAO();
		List<PaymentMethod> pmyls = pmyDao.selectFormaPagamento();
		
		model.setRowCount(0);
		
		for (PaymentMethod pmy : pmyls) {
			model.addRow(new Object[] {
					pmy.getIdFormaPagamento(),
					pmy.getDescricao()
			});
		}
	}
	
	public void centerCellRenderer(JTable tabelas) {
		center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		for (int i = 0; i < tabelas.getColumnCount(); i++) {
			tabelas.getColumnModel().getColumn(i).setCellRenderer(center);
		}
	}
	
	public void actionClick() {
		jtableFormaPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent clickTable) {
				if (clickTable.getClickCount() == 2 && jtableFormaPagamento.getSelectedRow() != -1) {
					int linha = jtableFormaPagamento.getSelectedRow();
					idFormaPagm = (int) jtableFormaPagamento.getValueAt(linha, 0);
					String descricao = (String) jtableFormaPagamento.getValueAt(linha, 1);
					//jlblLabelId.setText(String.valueOf("ID: "+idFmP));
				    //jlblLabelNome.setText("Nome: "+descricao);
					JOptionPane.showMessageDialog(null, "ID: "+idFormaPagm+" -- DESCRIÇÃO: "+descricao);
					EditVisibleTrue();
					CadFormaPagamFalse();
					jtxtDescriEdit.setText("");
					jtxtDescriEdit.setText(descricao);
				}
			}
		});
	}
	
	public void EditVisibleFalse() {
		jlblDescEdt.setEnabled(false);
		jlblTitDescEdit.setEnabled(false);
		jbtnEditar.setEnabled(false);
		jbtnCancelar.setEnabled(false);
		jtxtDescriEdit.setEditable(false);
	}
	
	public void EditVisibleTrue() {
		jlblDescEdt.setEnabled(true);
		jlblTitDescEdit.setEnabled(true);
		jbtnEditar.setEnabled(true);
		jbtnCancelar.setEnabled(true);
		jtxtDescriEdit.setEditable(true);
	}
	
	public void CadFormaPagamFalse() {
		jlblTituloDesc.setEnabled(false);
		jtxtDescricao.setEditable(false);
		jlblDescricao.setEnabled(false);
		jbtnAddFormaPag.setEnabled(false);
	}
	
	public void CadFormaPagamTrue() {
		jlblTituloDesc.setEnabled(true);
		jtxtDescricao.setEditable(true);
		jlblDescricao.setEnabled(true);
		jbtnAddFormaPag.setEnabled(true);
	}
}

