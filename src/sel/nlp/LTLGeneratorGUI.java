package sel.nlp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class LTLGeneratorGUI {

static File input_file = new File("input2.txt");
static Map<TemplateLTLPanel, JLabel> setOfTemplateAndLabel = new HashMap<>();
static List<TemplateLTLPanel> templateList = new ArrayList<>();
static List<JLabel> labelList = new ArrayList<>();

public static void main(String[] args) {
    int data_size = 10;
    window_data.gridpanel.set_size(data_size);
    GeneratorTemplatePanel genPanel = new GeneratorTemplatePanel("resources/ltl_templates.xml");
    Map<String, Map<String, LTLPattern>> propertyPattern =  genPanel.getPropertyPatternMap();
	JButton addButton = new JButton("Add");
	SelectMenuPanel menuPanel = new SelectMenuPanel(propertyPattern);
	menuPanel.add(addButton);
    // genPanel.extractVariableTag("results.xml", "annotatedtreeaction.xml");
	addButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(menuPanel.patternClassMap.get(menuPanel.selectedPattern)+":"+ menuPanel.selectedPattern+":"+  menuPanel.selectedScope);
	    	addTemplateUnit(genPanel.generateTemplatePanel(menuPanel.patternClassMap.get(menuPanel.selectedPattern), menuPanel.selectedPattern, menuPanel.selectedScope));
	        window_data.gridpanel.revalidate();
		}
	});
	window_data.gridpanel.add(menuPanel, BorderLayout.SOUTH);
	JScrollPane scrollpane = new JScrollPane();
	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//	addTemplateUnit(genPanel.generateTemplatePanel("occurrence_patterns", "absence_pattern", "globally"));

    scrollpane.setViewportView(window_data.gridpanel);
    window_data.frm.add(scrollpane);
	JButton convertButton = new JButton("convert to LTL");
	convertButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    extractVariable();
		}
	});
    window_data.frm.add(convertButton);
    window_data.frm.setVisible(true);
    //
    // File log= new File("log.txt");
    // try {
    //     FileWriter log_writer = new FileWriter(log);
    //     log_writer.write("Read Finish" + "\n");
    //     log_writer.close();
    // } catch (IOException e) {
    //     // TODO 自動生成された catch ブロック
    //     e.printStackTrace();
    // }
//    extractVariable();
}
public static void addTemplateUnit(TemplateLTLPanel templatePanel) {
	JLabel label = new JLabel("LTLがここに出る");
	templateList.add(templatePanel);
	labelList.add(label);
    window_data.gridpanel.add(templatePanel, BorderLayout.CENTER);
    window_data.gridpanel.add(label);
}
public static void extractVariable(){
	try{
		File file = new File("test.txt");
		FileWriter filewriter = new FileWriter(file);
		for (int i = 0; i < templateList.size(); i++) {
			TemplateLTLPanel templatePanel = templateList.get(i);
			HashMap<String, String> valMP = templatePanel.getVariables();
			String inputString = "";
			for(Pair<Boolean, String> entry:templatePanel.getLTLPattern().getTemplateListOfScope(templatePanel.getScope())) {
				if (entry.getKey()) {
					inputString += valMP.get(entry.getValue());
					System.out.println("variable"+valMP.get(entry.getValue()));
				}
				else {
					inputString += entry.getValue();
					System.out.println(entry.getValue());
				}
			}
			System.out.println(inputString);
			filewriter.write(inputString+"。\n");
		}
		filewriter.close();
	}catch(IOException e){
	    System.out.println(e);
	}


    var processBuilder = new ProcessBuilder("bin/gen_ltl.bat");
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
    Process process = null;
    try {
    	process = processBuilder.start();
    } catch (IOException e) {
    	e.printStackTrace();
    }
    try {
		process.waitFor();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("extraction is done.");
}
}
