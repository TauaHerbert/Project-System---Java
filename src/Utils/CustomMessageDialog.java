package Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CustomMessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public CustomMessageDialog() {
		super((Frame) null, "Mensagem", true);
		initComponents("Olá mundo");
	}

	public CustomMessageDialog(Frame parent, String titulo, String mensagem) {
		super(parent, titulo, true);
		initComponents(mensagem);
	}
	private void initComponents(String mensagem) {
		
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(new EmptyBorder(20,20,20,20));
		panel.setBackground(Color.WHITE);
		
		JLabel jlblMensagem = new JLabel("<html><body style='text-align: center;'>"+mensagem+"</body></html>");
		jlblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		jlblMensagem.setFont(new Font("Arial", Font.PLAIN, 20));
		jlblMensagem.setForeground(Color.BLUE);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPanel.setBackground(Color.WHITE);
		
		JButton jbtnOk = new JButton("OK");
		jbtnOk.setBackground(Color.DARK_GRAY);
		jbtnOk.setForeground(Color.WHITE);
		jbtnOk.setFont(new Font("Arial", Font.BOLD, 15));
		jbtnOk.setFocusPainted(false);
		jbtnOk.setPreferredSize(new Dimension(90,35));;
		
		jbtnOk.addActionListener(e -> {
			dispose();
		});
		
		btnPanel.add(jbtnOk);
		panel.add(jlblMensagem, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		setContentPane(panel);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		setSize(400, 200);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		try {
			CustomMessageDialog dialog = new CustomMessageDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
