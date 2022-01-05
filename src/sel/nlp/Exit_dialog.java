package sel.nlp;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Exit_dialog{
	Read read = new Read();
	static JFrame exit_frame = new JFrame();
	JDialog dialog = new JDialog(exit_frame);
    JLabel jLabel = new JLabel("終了オプションを選択してください");
    JPanel panel = new JPanel();
    XboxrayoutPanel exitpanel = new XboxrayoutPanel();
    JButton btn = new JButton("保存して終了");
    JButton btn2 = new JButton("保存せず終了");
    JButton btn3 = new JButton("キャンセル");

    public Exit_dialog() {
		// TODO 自動生成されたコンストラクター・スタブ
    	dialog.setResizable(false);
		dialog.setTitle("終了フォーム");
		dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		dialog.getContentPane().add(jLabel);
		exitpanel.add(btn);
		exitpanel.add(btn2);
		exitpanel.add(btn3);
		dialog.getContentPane().add(exitpanel);
        dialog.setBounds(500, 300, 400, 150);
	}

    void show_dialog() {
    	dialog.setVisible(true);
    }



}
