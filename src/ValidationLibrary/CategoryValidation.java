package ValidationLibrary;

import javax.swing.JOptionPane;

import modelsLibrary.Category;

public class CategoryValidation {
	public static void validarCategoria(Category categoria) {
		if (categoria.getNome() == null) {
			JOptionPane.showMessageDialog(null, "Nome da categoria nulo ou vazio!");
		}
		if (categoria.getDescricao() == null || categoria.getDescricao().equals("")) {
			JOptionPane.showMessageDialog(null, "Descrição da categoria nula ou vazia!");
		}
	}

}
