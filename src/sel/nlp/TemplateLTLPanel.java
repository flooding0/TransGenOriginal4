package sel.nlp;

import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TemplateLTLPanel extends JPanel {
	private LTLPattern patternLTL;
	private String scope;
	private Map<String, Textfield1> mpTextField = new HashMap<String, Textfield1>();
	
	public TemplateLTLPanel(String scope) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);
		this.scope = scope;
	}
	public void setLTLPattern(LTLPattern pattern) {
		patternLTL = pattern;
	}
	public LTLPattern getLTLPattern() {
		return patternLTL;
	}
	public String getScope() {
		return scope;
	}
	public HashMap<String, String> getVariables() {
		HashMap<String, String> mp = new HashMap<String, String>();
		for(Map.Entry<String, Textfield1> entry : mpTextField.entrySet()) {
			mp.put(entry.getKey(), entry.getValue().getText());
		}
		return mp;
	}
	public void setVariablefield(Textfield1 txtfield, String valName) {
		this.add(txtfield);
		mpTextField.put(valName, txtfield);
	}
}
