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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomConfirmDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private boolean confirm = false;

	public CustomConfirmDialog(Frame parent, String titulo, String mensagem) {
		super(parent, titulo, true);
		initComponents(mensagem);
	}
	
	private void initComponents(String mensagem) {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(Color.WHITE);
	
		//criando uma label para receber a mensagem, e ajustando a mensgem com html
		JLabel jlblMensagem = new JLabel("<html><body style='text-align: center;'>"+mensagem+"</body></html>");
		jlblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		jlblMensagem.setFont(new Font("Arial", Font.PLAIN, 20));
		jlblMensagem.setForeground(Color.BLUE);
		panel.add(jlblMensagem, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		btnPanel.setBackground(Color.gray);
		
		JButton jbtnSim = new JButton("Sim");
		jbtnSim.setBackground(new Color(0, 153, 76));
		jbtnSim.setForeground(Color.WHITE);
		jbtnSim.setFont(new Font("Arial", Font.BOLD, 17));
		jbtnSim.setFocusPainted(false);
		jbtnSim.setPreferredSize(new Dimension(100,30));
		jbtnSim.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		JButton jbtnNao = new JButton("Não");
		jbtnNao.setBackground(new Color(204, 0, 0));
		jbtnNao.setForeground(Color.WHITE);
		jbtnNao.setFont(new Font("Arial", Font.BOLD, 17));
		jbtnNao.setFocusPainted(false);
		jbtnNao.setPreferredSize(new Dimension(100,30));
		jbtnNao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			
		jbtnSim.addActionListener(e ->{
			confirm = true;
			dispose();
		});
		
		jbtnNao.addActionListener(e ->{
			confirm = false;
			dispose();
		});
		
		btnPanel.add(jbtnSim);
		btnPanel.add(jbtnNao);
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		setContentPane(panel);
		setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		//setForeground(Color.WHITE);
		setBackground(new Color(204, 0, 0));
		setSize(400, 200);
		setLocationRelativeTo(null);
	}
	
	public boolean showDialog() {
		setVisible(true);
		return confirm;
	}
}
