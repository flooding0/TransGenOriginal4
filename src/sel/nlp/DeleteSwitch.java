package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class DeleteSwitch extends JLabel implements  MouseListener{
	public DeleteSwitch() {
		setText("削除");
	    LineBorder line2 = new LineBorder(Color.black);
	    setBorder(line2);
		setOpaque(true);
		setPreferredSize(new Dimension(30, 20));
		setBackground(Color.GRAY);
		addMouseListener(this);
	}

	static void check_status() {
		CompornentData.deleteData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(getBackground()==Color.GRAY) {
			setBackground(Color.RED);
		}else {
		setBackground(Color.GRAY);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
