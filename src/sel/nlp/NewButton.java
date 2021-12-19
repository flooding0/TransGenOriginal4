package sel.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NewButton extends JPanel implements ActionListener{
	MainPanel main = new MainPanel();
	public NewButton() {
		JButton btn = new JButton("新規作成(N)");
		btn.setSize(20,12);
		btn.setMnemonic(KeyEvent.VK_N);
		btn.addActionListener(this);
		add(btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NewPanel popup = new NewPanel();
	}
}
