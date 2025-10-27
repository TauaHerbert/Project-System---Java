package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import daoLibrary.CategoryDAO;

import javax.swing.table.DefaultTableCellRenderer;

import modelsLibrary.Category;

import javax.swing.JScrollPane;

public class CategoryListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Point fixar;
	private JTable jtableCategoria;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryListView frame = new CategoryListView();
					frame.setVisible(true);
					frame.fixar = frame.getLocation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryListView() {
		setType(Type.UTILITY);
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton jbtnExit = new JButton("Voltar Menu");
		jbtnExit.setBackground(new Color(192, 192, 192));
		jbtnExit.setFocusPainted(false);
		jbtnExit.setBorder(null);
		jbtnExit.setBounds(1209, 666, 81, 23);
		jbtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tlPrincipal = new MainView();
				tlPrincipal.setVisible(true);
				dispose();
			}
		});
		contentPane.add(jbtnExit);
		
		JButton jbtnCadCateg = new JButton("Voltar Cadastro Categoria");
		jbtnCadCateg.setFocusPainted(false);
		jbtnCadCateg.setBorder(null);
		jbtnCadCateg.setBackground(Color.LIGHT_GRAY);
		jbtnCadCateg.setBounds(10, 666, 147, 23);
		jbtnCadCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryCreateView tlcadcategoria = new CategoryCreateView();
				tlcadcategoria.setVisible(true);
				dispose();
			}
		});
		contentPane.add(jbtnCadCateg);
		
		
		//Listando as categorias
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NOME");
		model.addColumn("DESCRIÇÃO");
		
		jtableCategoria = new JTable(model);
		//Ajustando tamanhos das colunas
		jtableCategoria.getColumnModel().getColumn(0).setPreferredWidth(30);
		jtableCategoria.getColumnModel().getColumn(1).setPreferredWidth(170);
		jtableCategoria.getColumnModel().getColumn(2).setPreferredWidth(300);
		
		//Centralizando os textos de cada coluna
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		jtableCategoria.getColumnModel().getColumn(0).setCellRenderer(centro);
		jtableCategoria.getColumnModel().getColumn(1).setCellRenderer(centro);
		jtableCategoria.getColumnModel().getColumn(2).setCellRenderer(centro);
		
		//impedindo o redimensionamento e movimentação das colunas
		jtableCategoria.getTableHeader().setResizingAllowed(false);
		jtableCategoria.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane jscrollPaneCategoria = new JScrollPane(jtableCategoria);
		jscrollPaneCategoria.setBounds(356,126,583,300);
		contentPane.add(jscrollPaneCategoria);
		
		JButton jbtnEditarCategoria = new JButton("Editar Categoria");
		jbtnEditarCategoria.setBounds(356, 457, 133, 66);
		contentPane.add(jbtnEditarCategoria);
		
		jbtnEditarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = jtableCategoria.getSelectedRow();
				if (linha != -1) {
					int idCate = (int) jtableCategoria.getValueAt(linha, 0);
					String nomeC = (String) jtableCategoria.getValueAt(linha, 1);
					String descC = (String) jtableCategoria.getValueAt(linha, 2);
					CategoryEditView tlcategoria = new CategoryEditView(idCate,nomeC,descC);
					tlcategoria.setVisible(true);
					
				}
			}
		});
		
		CategoryDAO ddcategoria = new CategoryDAO();
		
		List<Category> listar = ddcategoria.selectAllCategoria();
		
		for (Category c : listar) {
			model.addRow(new Object[] {
					c.getIdCategoria(),
					c.getNome(),
					c.getDescricao()
			});
		}
		//fim da listagem
		
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
