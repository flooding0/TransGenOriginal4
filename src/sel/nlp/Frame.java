package sel.nlp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Frame extends JFrame implements ActionListener,KeyListener,WindowListener{
	static JPanel panel = new JPanel();
	static JPanel gridpanel = new JPanel();
	static JPanel buttons = new JPanel();
	static JPanel resultspace = new JPanel();
	static Btn new_btn = new Btn("新規作成");
	static Btn save_btn = new Btn("保存");
	static Btn refresh_btn = new Btn("更新");
	static Btn delete_btn = new Btn("削除");
	private final JScrollPane scrollpane2 = new JScrollPane();
	private final JScrollPane scrollpane1 = new JScrollPane();
	Read read = new Read();
    Analyse analyse = new Analyse();
    BuildTree buildtree = new BuildTree();
    Classify classify = new Classify();
    Annotate annotate = new Annotate();
    AnnotateAction annotateaction = new AnnotateAction();
    Extract extract = new Extract();
    ExtractWord extractword = new ExtractWord();
    Exit_dialog exit = new Exit_dialog();
    Timer timer = new Timer();

	public Frame() {
		getContentPane().setBounds(new Rectangle(0, 0, 500, 600));
		setBounds(new Rectangle(-7, 0, 947, 727));
		setForeground(new Color(240, 240, 240));
	        // ウィンドウの閉じ方
		    addWindowListener(new WindowClosing());
		    addWindowListener(this);
		    addKeyListener(this);
	        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    setTitle("要求仕様作成ツール");
		    setResizable(false);
		    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		    		    getContentPane().add(scrollpane1);
		    			new_btn.btn.addActionListener(this);
		    			refresh_btn.btn.addActionListener(this);
		    			save_btn.btn.addActionListener(this);
		    			delete_btn.btn.addActionListener(this);
		    			exit.btn.addActionListener(this);
		    			exit.btn2.addActionListener(this);
		    			exit.btn3.addActionListener(this);
		    			ResultSpace.frame.addWindowListener(this);
		    			ResultSpace.frame.addKeyListener(this);
		    		    scrollpane1.setViewportView(panel);
		    		    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    		    scrollpane2.setPreferredSize(new Dimension(800, 500));
		    		    scrollpane2.setViewportView(gridpanel);
		    		    panel.add(scrollpane2);
		    		    gridpanel.setLayout(new GridLayout(0, 1, 0, 0));
		    		    Frame.buttons.add(refresh_btn);
		    		    Frame.buttons.add(save_btn);
		    		    Frame.buttons.add(new_btn);
		    		    Frame.buttons.add(delete_btn);
		    		    panel.add(buttons);
		    		    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		    		    panel.add(resultspace);
		    		    resultspace.setLayout(new BoxLayout(resultspace, BoxLayout.Y_AXIS));
		    		    scrollpane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
						scrollpane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

						TimerTask task = new TimerTask() {
				            int count = 0;
				            public void run() {
//				            	read.rewrite_input2();
//				    			analyse.analysis();
//				    			buildtree.buildtree();
//				    			classify.classify();
//				    			annotate.annotate();
//				          		annotateaction.annotateaction();
//				    		    ResultSpace.gridpanel.removeAll();
//				    			extract.extract();
//				    			extractword.extractword();
//				    			read.extract_difference();
//				    			Frame.gridpanel.removeAll();
//				    			DeleteSwitch.check_status();
//				    			setBounds(new Rectangle(-7, 0, 947, 727));
//				    			ResultSpace.show_result();
//				    		    Window_data.frm.setVisible(true);
//				    			System.out.println("Complete");
//				    			System.out.println("***************************************");
				            }
				        };
				        timer.scheduleAtFixedRate(task,1000,3000); // 今回追加する処理
	    }
	class WindowClosing extends WindowAdapter{
		         public void windowClosing(WindowEvent e) {
		        	 exit.show_dialog();
		         }
		     }

	void close_window() {
		System.exit(0);
        dispose();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource()==refresh_btn.btn) {
			read.rewrite_input2();
			analyse.analysis();
			buildtree.buildtree();
			classify.classify();
			annotate.annotate();
      		annotateaction.annotateaction();
		    ResultSpace.gridpanel.removeAll();
			extract.extract();
			extractword.extractword();
			read.extract_difference();
			Frame.gridpanel.removeAll();
			DeleteSwitch.check_status();
			setBounds(new Rectangle(-7, 0, 947, 727));
		    Window_data.frm.setVisible(true);
			ResultSpace.show_result();
			System.out.println("Complete");
			System.out.println("***************************************");


		}else if(e.getSource()==save_btn.btn) {
			Xml_write.input_write();
			System.out.println("保存しました。");
		}else if(e.getSource()==new_btn.btn) {
			NewPanel popup = new NewPanel();
		}else if (e.getSource() == delete_btn.btn) {
			read.rewrite_input2();
			Frame.gridpanel.removeAll();
			DeleteSwitch.check_status();
		    Window_data.frm.setVisible(true);
		}

		if(e.getSource()==exit.btn) {
			read.rewrite_input2();
			System.exit(0);
			dispose();
		}else if(e.getSource() == exit.btn2) {
			System.exit(0);
			dispose();
		}else if(e.getSource() == exit.btn3) {

		}
		exit.dialog.setVisible(false);
		exit.dialog.dispose();

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


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if(e.getSource() == this) {
			System.out.println("open");
		}

	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}
}
