package Utils;

import java.awt.Frame;

public class MessageBox {
	
	public static void messageShow(String mensagem) {
		new CustomMessageDialog((Frame) null, "Mensagem", mensagem).setVisible(true);;
	}
	
	public static boolean messageConfirm(String mensagem) {
		CustomConfirmDialog confirmMess = new CustomConfirmDialog((Frame) null, "Confirmação", mensagem);
		return confirmMess.showDialog();
	}

}
