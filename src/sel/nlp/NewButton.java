package sel.nlp;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class NewButton extends JPanel implements ActionListener{
	MainPanel main = new MainPanel();
	public NewButton() {
		Button btn = new Button("新規作成");
		btn.setSize(20,12);
		btn.addActionListener(this);
		add(btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.MainPanel_empty();
	    Window_data.frm.setVisible(true);
	}
}
