package sel.nlp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBox extends JPanel implements ActionListener{
	static String[] compname = CompornentData.compstring.toArray(new String[0]);
	static JComboBox<String> compselect = new JComboBox<String>(compname);
	public ComboBox() {
		// TODO 自動生成されたコンストラクター・スタブ
		compname = CompornentData.compstring.toArray(new String[0]);
		compselect = new JComboBox<String>(compname);
		//コンボボックスを更新
		compselect.setSelectedIndex(compselect.getItemCount()-1);
		compselect.setPreferredSize(new Dimension(100,30));
		compselect.addActionListener(this);
		add(compselect);
	}
	static JComboBox<String> get_combobox() {

		return compselect;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}


}
