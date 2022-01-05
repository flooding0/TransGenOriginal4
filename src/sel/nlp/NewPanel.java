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
    JButton btn2 = new JButton("確定");
    static Textfield1 tx = new Textfield1(200, "");
    JPanel panel = new JPanel();
    XboxrayoutPanel mainpanel = new XboxrayoutPanel();
	JDialog dialog = new JDialog(frame);
	MainPanel main = new MainPanel();

	public NewPanel() {
		ComboBox selectbox = new ComboBox();
		// TODO 自動生成されたコンストラクター・スタブ
		dialog.setResizable(false);
		dialog.setTitle("要素名入力フォーム");
		btn.addActionListener(this);
		btn2.addActionListener(this);
		btn.setMnemonic(KeyEvent.VK_ENTER);
		panel.add(jLabel);
		mainpanel.add(tx);
		mainpanel.add(btn);
		panel.add(mainpanel);
		panel.add(selectbox);
		panel.add(btn2);
		dialog.add(panel);
        dialog.setBounds(500, 300, 300, 150);
        dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==btn) {
		if(!tx.getText().equals("")) {
		main.MainPanel_empty(NewPanel.tx.getText());
	    Window_data.frm.setVisible(true);
	    if(!CompornentData.compstring.contains(NewPanel.tx.getText())) {
	    	CompornentData.compstring.add(NewPanel.tx.getText());
	    }
	    tx.setText("");
		}
		}else if(e.getSource()==btn2) {
			main.MainPanel_empty((String) ComboBox.get_combobox().getSelectedItem());
		}


	    dialog.setVisible(false);
	}

}
