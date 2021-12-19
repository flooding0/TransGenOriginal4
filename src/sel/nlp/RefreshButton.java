package sel.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RefreshButton extends JPanel implements ActionListener{

    Read read = new Read();
    Analyse analyse = new Analyse();
    BuildTree buildtree = new BuildTree();
    Classify classify = new Classify();
    Annotate annotate = new Annotate();
    AnnotateAction annotateaction = new AnnotateAction();
    Extract extract = new Extract();
    ExtractWord extractword = new ExtractWord();



		public RefreshButton() {
			JButton btn1 = new JButton("更新(R)");
			btn1.setSize(20,12);
			btn1.setMnemonic(KeyEvent.VK_R);
			btn1.addActionListener(this);
			add(btn1);
	    setSize(30,15);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			read.rewrite_input2();
			analyse.analysis();
			buildtree.buildtree();
			classify.classify();
			annotate.annotate();
      		annotateaction.annotateaction();
			extract.extract();
			extractword.extractword();
			read.extract_difference();
			Frame.gridpanel.removeAll();
			DeleteButton.check_status();
			//MainPanel.CreateMainPanel();


		    Window_data.frm.setVisible(true);
			System.out.println("Complete");
			System.out.println("***************************************");
		}
}
