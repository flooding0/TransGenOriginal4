package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class resultlabel extends JLabel{
	public resultlabel(){
	    LineBorder line2 = new LineBorder(Color.black);
	    setBorder(line2);
		setOpaque(true);
		setPreferredSize(new Dimension(130, 20));
		setBackground(Color.WHITE);
	}
}
