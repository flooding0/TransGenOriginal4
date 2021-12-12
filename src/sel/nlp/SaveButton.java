package sel.nlp;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SaveButton extends JPanel implements ActionListener{

	public SaveButton() {
		Button btn = new Button("保存");
		btn.setSize(20,12);
		btn.addActionListener(this);
		add(btn);
    setSize(30,15);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
