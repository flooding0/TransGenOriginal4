package sel.nlp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class Frontpanel extends Panel{
	Read read = new Read();
	String text = "";
	label2 label = new label2(text);
	label2 label2 = new label2("bbbbb");
	BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	Textfield1 tb = new Textfield1("aa");
	JScrollPane scrollpane = new JScrollPane(label);

	public Frontpanel() {
		setLayout(layout);
	}

	public void create_textfield() {
		this.add(tb);
	}

	public void create_label() {
		label.setPreferredSize( new Dimension(500,40));
		this.add(label);
	}

	public void create_label2() {
		label2.setPreferredSize(new Dimension (100,100));
		this.add(label2);
	}

	public void set_text(String text) {
		this.text = this.text + text;
		label.setText(this.text);
		add(scrollpane, BorderLayout.EAST);
	}

}
