package sel.nlp;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class XboxrayoutPanel extends JPanel {
	public XboxrayoutPanel() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);
	}
}
