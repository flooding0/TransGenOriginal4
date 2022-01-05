package sel.nlp;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Btn extends JPanel{
	MainPanel main = new MainPanel();
	JButton btn = new JButton();
	public Btn(String text) {
		btn.setText(text);
		btn.setSize(20,12);
		add(btn);
	}

}
