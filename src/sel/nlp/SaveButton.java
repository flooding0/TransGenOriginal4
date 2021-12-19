package sel.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SaveButton extends JPanel implements ActionListener{

	public SaveButton() {
		JButton btn = new JButton("保存(S)");
		btn.setSize(20,12);
		btn.setMnemonic(KeyEvent.VK_S);
		btn.addActionListener(this);
		add(btn);
    setSize(30,15);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Xml_write.input_write();
		System.out.println("保存しました。");
	}

}
