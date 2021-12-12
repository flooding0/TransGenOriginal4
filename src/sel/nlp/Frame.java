package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Frame extends JFrame{
	static JPanel panel = new JPanel();
	static JPanel gridpanel = new JPanel();
	static JPanel buttons = new JPanel();
	static JPanel resultspace = new JPanel();
	static RefreshButton refresh_btn = new RefreshButton();
	static SaveButton save_btn = new SaveButton();
	static NewButton new_btn = new NewButton();
	private final JScrollPane scrollpane2 = new JScrollPane();
	private final JScrollPane scrollpane1 = new JScrollPane();
	public Frame() {
		getContentPane().setBounds(new Rectangle(0, 0, 500, 600));
		setBounds(new Rectangle(0, 0, 1220, 1000));
		setForeground(new Color(240, 240, 240));
	        // ウィンドウの閉じ方
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setTitle("要求仕様作成ツール");
		    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		    		    getContentPane().add(scrollpane1);
		    		    scrollpane1.setViewportView(panel);
		    		    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    		    scrollpane2.setPreferredSize(new Dimension(1200, 500));
		    		    scrollpane2.setViewportView(gridpanel);
		    		    panel.add(scrollpane2);
		    		    gridpanel.setLayout(new GridLayout(0, 1, 0, 0));
		    		    Frame.buttons.add(refresh_btn);
		    		    Frame.buttons.add(save_btn);
		    		    Frame.buttons.add(new_btn);
		    		    panel.add(buttons);
		    		    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		    		    panel.add(resultspace);
		    		    resultspace.setLayout(new BoxLayout(resultspace, BoxLayout.Y_AXIS));
		    		    scrollpane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scrollpane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    }



}
