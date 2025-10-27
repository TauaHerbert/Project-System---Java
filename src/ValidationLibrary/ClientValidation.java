package ValidationLibrary;
import javax.swing.JOptionPane;

import modelsLibrary.Client;

public class ClientValidation {
	public static boolean validarCliente(Client cliente) {
		if (cliente.getNome() == null || cliente.getNome().isBlank()) {
			JOptionPane.showMessageDialog(null, "Nome do cliente invalido. Preencha corretamente!");
			return false;
		}
		//CustomConfirmDialog message = new CustomConfirmDialog(null, "Confirmação Nome","Deseja cadastrar o Cliente"+cliente.getNome()+" sem o Email ?");
		//boolean confirm = message.showDialog();
		//if (!confirm) {
			//return false;
		//}
		if (cliente.getEmail() == null || cliente.getEmail().equals("")) {
			int res = JOptionPane.showConfirmDialog(null, "Deseja cadastrar o Cliente "+cliente.getNome()+" sem o Email ?","Confirmação Email",JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.NO_OPTION || res ==JOptionPane.CLOSED_OPTION) {
				return false;
			}
		}
		if (cliente.getEmail().length() > 1 && cliente.getEmail().length() <= 10) {
			JOptionPane.showMessageDialog(null, "Email incorreto");
			return false;
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().equals("")) {
			int res = JOptionPane.showConfirmDialog(null, "Deseja cadastrar o Cliente "+cliente.getNome()+" sem o Telefone ?","Confirmação Telefone",JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.NO_OPTION || res ==JOptionPane.CLOSED_OPTION) {
				return false;
			}
		}
		if (cliente.getTelefone().length() > 1 && cliente.getTelefone().length() <= 10) {
			JOptionPane.showMessageDialog(null, "Telefone incorreto");
			return false;
		}
		if (cliente.getCpf() == null || cliente.getCpf().equals("")) {
			int res = JOptionPane.showConfirmDialog(null, "Deseja cadastrar o Cliente "+cliente.getNome()+" sem o CPF ?","Confirmação CPF",JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.NO_OPTION || res ==JOptionPane.CLOSED_OPTION) {
				return false;
			}
		}
		if (cliente.getCpf().length() > 1 && cliente.getCpf().length() <= 10) {
			JOptionPane.showMessageDialog(null, "CPF incorreto");
			return false;
		}
		return true;
	}

}
