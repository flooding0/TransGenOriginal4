package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.dom4j.Document;
public class ResultSpace implements KeyListener{

	static File xml_input = new File("result2.xml");
	static JFrame frame = new JFrame("解析結果");
	static Document result_document;
	private final static JScrollPane scrollpane = new JScrollPane();
	static JPanel gridpanel = new JPanel();

	public ResultSpace() {
//		SAXReader reader = new SAXReader();
//		try {
//			result_document = reader.read(xml_input);
//		} catch (DocumentException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
	}

	static void create_result(String sentenceid, String name, String word) {
		XboxrayoutPanel panel = new XboxrayoutPanel();
		Label2 sentencelabel = new Label2(sentenceid);
		Label2 namelabel = new Label2(name);
		if (name.equals("variable")) {
			namelabel.setText("状態変数");
			namelabel.setBackground(Color.orange);
		}else if (name.equals("variable-value")) {
			namelabel.setText("変数値");
			namelabel.setBackground(Color.yellow);
		} else if(name.equals("subinfo")){
			namelabel.setText("遷移発動要因");
			namelabel.setBackground(Color.PINK);
		}else if(name.equals("transition")) {
			namelabel.setText("遷移後変数値");
			namelabel.setBackground(Color.cyan);
		}
		Label2 wordlabel = new Label2(word,400);
		panel.add(sentencelabel);
		panel.add(namelabel);
		panel.add(wordlabel);
		gridpanel.add(panel);
	}

	static void show_result() {
		frame.setResizable(false);
	    gridpanel.setLayout(new GridLayout(0, 1, 0, 0));
	    scrollpane.setViewportView(gridpanel);
	    scrollpane.setPreferredSize(new Dimension(300, 300));
	    frame.add(scrollpane);
		frame.setBounds(926, 0, 359, 727);
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


}
