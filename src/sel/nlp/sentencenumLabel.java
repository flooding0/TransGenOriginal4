package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class sentencenumLabel extends JLabel{
	public sentencenumLabel(String text) {
		// TODO 自動生成されたコンストラクター・スタブ
		setText(text);
	    LineBorder line2 = new LineBorder(Color.black);
	    setBorder(line2);
		setOpaque(true);
		setPreferredSize(new Dimension(40, 20));
		setBackground(Color.WHITE);
	}
}
