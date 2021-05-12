package sel.nlp;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			Button btn1 = new Button("更新");
			btn1.setSize(20,12);
			btn1.addActionListener(this);
			add(btn1);
	    setSize(30,15);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("push button");
//			String batpath= "analyse2.bat";
//			try {
//				Runtime run = Runtime.getRuntime();
//			    Process pro = run.exec(batpath);
//			    int ret = pro.waitFor();
//			    System.err.println("Complete");
//			    read.show_result();
//			    } catch (IOException | InterruptedException e1) {
//			      e1.printStackTrace();
//			    }
			analyse.analysis();
			buildtree.buildtree();
			classify.classify();
			annotate.annotate();
			annotateaction.annotateaction();
			extract.extract();
			extractword.extractword();
			System.out.println("Complete");
		}
}
