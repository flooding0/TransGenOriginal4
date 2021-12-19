package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Textfield1  extends JTextField{
	public Textfield1(String text){
		setText(text);
		LineBorder line2 = new LineBorder(Color.black);
		setBorder(line2);
		setPreferredSize(new Dimension(900, 20));

	}
	public Textfield1(int i, String text) {
		setText(text);
		LineBorder line2 = new LineBorder(Color.black);
		setBorder(line2);
		setPreferredSize(new Dimension(i, 20));
	}

}
