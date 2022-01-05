package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Label2 extends JLabel{
	public Label2(String text){
		setText(text);
	    LineBorder line2 = new LineBorder(Color.black);
	    setBorder(line2);
		setOpaque(true);
		setPreferredSize(new Dimension(130, 20));
		setBackground(Color.WHITE);
	}

	public Label2(String text, int i){
		setText(text);
	    LineBorder line2 = new LineBorder(Color.black);
	    setBorder(line2);
		setOpaque(true);
		setPreferredSize(new Dimension(i, 20));
		setBackground(Color.WHITE);
	}

}
