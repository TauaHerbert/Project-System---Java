package Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.List;

public class ItemCboxAutoCompleted<T> {
	
	private boolean isUpdating = false;
	
	public boolean isUpdating() {
		return isUpdating;
	}
	
	public void ativarText(JComboBox<T> combobox, ComboBoxFilter<T> filtro) {
		
		combobox.setEditable(true);
		
		JTextField editar = (JTextField) combobox.getEditor().getEditorComponent();
		
		editar.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filtrar();
			}
			public void removeUpdate(DocumentEvent e) {
				filtrar();
			}
			public void changedUpdate(DocumentEvent e) {
	            filtrar();
			}
			
			private void filtrar() {
				if (isUpdating) {
					return;
				}
				
				SwingUtilities.invokeLater(() -> {
					String txt = editar.getText();
					
					if (txt.length() >= 2) {
						isUpdating = true;
						
						List<T> results = filtro.filtrar(txt);
						
						combobox.removeAllItems();
						for (T itm : results) {
							combobox.addItem(itm);
						}
						
						combobox.setPopupVisible(true);
						editar.setText(txt);
						editar.setCaretPosition(txt.length());
						
						isUpdating = false;
					}
				});
			}
		});
	}
}
