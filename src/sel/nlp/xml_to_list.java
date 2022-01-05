package sel.nlp;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class xml_to_list {

	static File xml_input = new File("input2.xml");
	static Document input_document;

	static void perform() {
		SAXReader reader = new SAXReader();
		try {
			input_document = reader.read(xml_input);
			List<Node> sentenceNodes = input_document.selectNodes("//sentence");
			for(Node sentenceid : sentenceNodes) {
				CompornentData.sentence_num_list.add(new sentencenumLabel(((Element)sentenceid).attribute("id").getValue()));
				CompornentData.DB_list.add(new DeleteSwitch());
			}
			for(Node node : sentenceNodes) {
				List<Node> compnamenode = node.selectNodes("./compname");
				List<Node> requirementnode = node.selectNodes("./requirement");

				for(Node compname : compnamenode) {
					CompornentData.compornent_name_list.add(new Label2(((Element)compname).getText()));
					if(!CompornentData.compstring.contains(((Element)compname).getText())) {
					CompornentData.compstring.add(((Element)compname).getText());
					}
				}
				for(Node requirement : requirementnode) {
					CompornentData.txlist.add(new Textfield1(((Element)requirement).getText()));
				}
			}

		} catch (DocumentException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

	}

}
