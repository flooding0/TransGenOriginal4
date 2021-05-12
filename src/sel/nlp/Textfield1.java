package sel.nlp;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Textfield1  extends JTextField{
	public Textfield1(String text){
		setText(text);
		LineBorder line2 = new LineBorder(Color.black);
		setBorder(line2);

	}

}
