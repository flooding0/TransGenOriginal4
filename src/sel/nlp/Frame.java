package sel.nlp;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame() {

	        // ウィンドウの閉じ方
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setBounds(80, 50, 1200, 600);
		    setTitle("試作");
		    setBackground(Color.BLUE);
	        ImageIcon icon = new ImageIcon("./oma.png");
//	        setResizable(false);
	        setIconImage(icon.getImage());
            FlowLayout layout = new FlowLayout();
	        setLayout(layout);

//	        Frontpanel fp = new Frontpanel();
	    }


}
