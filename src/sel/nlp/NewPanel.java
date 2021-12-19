package sel.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewPanel implements ActionListener{
	JFrame frame = new JFrame();
    JLabel jLabel = new JLabel("要素名を入力してください");
    JButton btn = new JButton("確定");
    static Textfield1 tx = new Textfield1(200, "");
    JPanel panel = new JPanel();
    XboxrayoutPanel mainpanel = new XboxrayoutPanel();
	JDialog dialog = new JDialog(frame);
	MainPanel main = new MainPanel();

	public NewPanel() {
		// TODO 自動生成されたコンストラクター・スタブ
		dialog.setTitle("要素名入力フォーム");
		btn.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
		panel.add(jLabel);
		mainpanel.add(tx);
		mainpanel.add(btn);
		panel.add(mainpanel);
		dialog.add(panel);
        dialog.setBounds(500, 300, 400, 150);
        dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		main.MainPanel_empty();
	    Window_data.frm.setVisible(true);
	    dialog.setVisible(false);
	    tx.setText("");
	}

}
