package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;

import daoLibrary.CategoryDAO;
import modelsLibrary.Category;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategoryEditView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtxtNome;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryEditView frame = new CategoryEditView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CategoryEditView() {
		this(0,"","");
	}
	
	public CategoryEditView(int idCater, String nomeC, String descC) {
		setType(Type.UTILITY);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		setSize(new Dimension(1300, 700));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		jtxtNome = new JTextField();
		jtxtNome.setText("");
		jtxtNome.setBounds(252, 180, 211, 20);
		getContentPane().add(jtxtNome);
		jtxtNome.setColumns(10);
		jtxtNome.setText(nomeC);
		
		JTextArea jtxtAreaDesc = new JTextArea();
		jtxtAreaDesc.setBounds(252, 254, 211, 77);
		getContentPane().add(jtxtAreaDesc);
		jtxtAreaDesc.setText(descC);
		
		JButton btnUpdateCater = new JButton("Atualizar Categoria");
		btnUpdateCater.setBounds(252, 369, 211, 23);
		getContentPane().add(btnUpdateCater);
		btnUpdateCater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Category categoria = new Category();
				CategoryDAO ddcategoria = new CategoryDAO();
				
				categoria.setIdCategoria(idCater);
				categoria.setNome(jtxtNome.getText());
				categoria.setDescricao(jtxtAreaDesc.getText());
				
				ddcategoria.updateCategoria(categoria);
			}
		});
		
		JButton jbtnCancelar = new JButton("Cancelar");
		jbtnCancelar.setBounds(10, 466, 89, 23);
		getContentPane().add(jbtnCancelar);
		jbtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryListView tlcategoria = new CategoryListView();
				tlcategoria.setVisible(true);
				dispose();
			}
		});
	}
}
