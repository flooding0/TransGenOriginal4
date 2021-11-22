package sel.nlp;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class Frontpanel extends Panel{
	Read read = new Read();
	String text = "";
	Label2 label = new Label2(text);
	Label2 label2 = new Label2("bbbbb");
	BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	Textfield1 tb = new Textfield1("aa");
	JScrollPane scrollpane = new JScrollPane(label);

	public Frontpanel() {
		setLayout(layout);
	}


	public void set_text(String text) {
		this.text = this.text + text;
		label.setText(this.text);
		add(scrollpane, BorderLayout.EAST);
	}

}
