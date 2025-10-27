package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

import daoLibrary.CategoryDAO;
import modelsLibrary.Category;

import javax.swing.JTextArea;


public class CategoryCreateView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Point fixar;
	private JTextField jtxtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryCreateView frame = new CategoryCreateView();
					frame.setVisible(true);
					//Captura a posição central depois de visível
					frame.fixar = frame.getLocation();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CategoryCreateView() {
		setType(Type.UTILITY);
		//removendo as bordas do Jframe
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 480, 335);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jlabelTitulo = new JLabel("Cadastro de Categorias dos Produtos");
		jlabelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		jlabelTitulo.setBounds(440, 143, 373, 32);
		jlabelTitulo.setBackground(new Color(240, 240, 240));
		contentPane.add(jlabelTitulo);
		
		JButton jbtnfechar = new JButton("Voltar");
		jbtnfechar.setBackground(new Color(192, 192, 192));
		jbtnfechar.setFocusPainted(false);
		jbtnfechar.setBorder(null);
		jbtnfechar.setBounds(1228, 666, 62, 23);
		contentPane.add(jbtnfechar);
		jbtnfechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView tprincipal = new MainView();
				tprincipal.setVisible(true);
				dispose();
			}
		});
		
		jtxtNome = new JTextField();
		jtxtNome.setBounds(543, 275, 226, 20);
		contentPane.add(jtxtNome);
		jtxtNome.setColumns(10);
		
		JTextArea jtextArea = new JTextArea();
		jtextArea.setBounds(543, 345, 232, 88);
		contentPane.add(jtextArea);
		
		JLabel jlabelNome = new JLabel("Nome:");
		jlabelNome.setFont(new Font("Arial", Font.BOLD, 12));
		jlabelNome.setBounds(543, 250, 36, 14);
		contentPane.add(jlabelNome);
		
		JLabel jlabelDescricao = new JLabel("Descrição:");
		jlabelDescricao.setFont(new Font("Arial", Font.BOLD, 12));
		jlabelDescricao.setBounds(543, 320, 62, 14);
		contentPane.add(jlabelDescricao);
		
		JButton jbtnTelaListarCategoria = new JButton("Listar Catgoiras");
		jbtnTelaListarCategoria.setBackground(new Color(192, 192, 192));
		jbtnTelaListarCategoria.setFocusPainted(false);
		jbtnTelaListarCategoria.setBorder(null);
		jbtnTelaListarCategoria.setBounds(1134, 274, 156, 67);
		contentPane.add(jbtnTelaListarCategoria);
		jbtnTelaListarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryListView tlcategoria = new CategoryListView();
				tlcategoria.setVisible(true);
				dispose();
			}
		});
		
		JButton jbtnCadastrarCategoria = new JButton("Cadastrar");
		jbtnCadastrarCategoria.setBackground(new Color(192, 192, 192));
		jbtnCadastrarCategoria.setBounds(570, 458, 185, 32);
		jbtnCadastrarCategoria.setFocusPainted(false);
		jbtnCadastrarCategoria.setBorder(null);
		contentPane.add(jbtnCadastrarCategoria);
		jbtnCadastrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category categoria = new Category();
				CategoryDAO ddcategoria = new CategoryDAO();
				
				categoria.setNome(jtxtNome.getText());
				categoria.setDescricao(jtextArea.getText());
				
				if (jtxtNome.getText().equals(null) || jtxtNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O nome está vazio!");
				}else if(jtextArea.getText().equals(null) || jtextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "A Descrição está vazia!");
				}else {
				categoria.setNome(jtxtNome.getText());
				categoria.setDescricao(jtextArea.getText());
				ddcategoria.insertCategoria(categoria);
				JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
				jtxtNome.setText("");
				jtextArea.setText("");
				}
			}
		});
		
		//Impede do o usuário mover a janela após abrir
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
