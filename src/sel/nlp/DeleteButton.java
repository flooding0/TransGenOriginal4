package sel.nlp;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton implements ActionListener{
	public DeleteButton() {
		// TODO 自動生成されたコンストラクター・スタブ
		Button btn1 = new Button("削除");
		btn1.setSize(20,12);
		btn1.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
