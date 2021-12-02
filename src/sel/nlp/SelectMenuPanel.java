package sel.nlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.collections4.map.HashedMap;

public class SelectMenuPanel extends JPanel {
	Map<String, String> patternClassMap = new HashedMap<String, String>();
	JComboBox<String> comboScopeBox, comboPatternBox;
	String selectedPattern, selectedScope;
	
	public SelectMenuPanel(Map<String, Map<String, LTLPattern>> propertyPattern) {
		comboPatternBox = new JComboBox<String>();
		comboPatternBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)comboPatternBox.getSelectedItem();
				selectedPattern = s.substring(0, s.indexOf(" ["));
			}
		});
		for(Entry<String, Map<String, LTLPattern>> entry: propertyPattern.entrySet()) {
			for(Entry<String, LTLPattern> ent: entry.getValue().entrySet()) {
				comboPatternBox.addItem(ent.getKey()+" ["+entry.getKey()+"]");
				patternClassMap.put(ent.getKey(), entry.getKey());
			}
		}
		
		
		comboScopeBox = new JComboBox<String>();
		comboScopeBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedScope = (String)comboScopeBox.getSelectedItem();
			}
		});
		for(String s: propertyPattern.entrySet().iterator().next().getValue().entrySet().iterator().next().getValue().scopeList) {
			comboScopeBox.addItem(s);
		}
		

		String s = (String)comboPatternBox.getSelectedItem();
		selectedPattern = s.substring(0, s.indexOf(" ["));
		selectedScope = (String)comboScopeBox.getSelectedItem();
		this.add(new JLabel("PATTERN: "));
		this.add(comboPatternBox);
		this.add(new JLabel("SCOPE: "));
		this.add(comboScopeBox);
		
	}
}
