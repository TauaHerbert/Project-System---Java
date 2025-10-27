package Utils;

import javax.swing.text.*;
public class CharacterLimitFilter extends DocumentFilter {
	private int limiteCarac;
	
	public CharacterLimitFilter(int limiteCarac) {
		this.limiteCarac = limiteCarac;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		if (fb.getDocument().getLength() + string.length() <= limiteCarac) {
			super.insertString(fb, offset, string, attr);
		}
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int lenght, String string, AttributeSet attr) throws BadLocationException {
		if (fb.getDocument().getLength() - lenght + string.length() <= limiteCarac) {
			super.replace(fb, offset, lenght, string, attr);
		}
	}

}
