package sel.nlp;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Gridpanel extends JPanel{
	public Gridpanel(){
	}
	public void set_size(int data_size) {
	GridLayout layout = new GridLayout(data_size,1);
	//横1,縦data_sizeのGridを作成
	setLayout(layout);
	setBounds(200, 200, 1000, 300);
	layout.setVgap(5);//垂直方向の間隔
	layout.setHgap(20);//水平方向の間隔
	}
}