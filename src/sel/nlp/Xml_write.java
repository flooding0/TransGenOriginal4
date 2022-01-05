package sel.nlp;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;

public class Xml_write {
	XSSFWorkbook workbook;
	FileOutputStream out;
	XMLWriter writer;
	Document document;
	Element input;



	  public Xml_write() {
		  //コンストラクタ
		  writer = new XMLWriter(new File("input2.xml"));
		  input = DocumentHelper.createElement("input");
		  document = DocumentHelper.createDocument();
		  document.add(input);
	  }

	  void write() {
		  writer.write(document, true);
		  }


	void perform() {
		int i = 0;
		for (sentencenumLabel num : CompornentData.sentence_num_list) {
			if(CompornentData.DB_list.get(i).getBackground()== Color.GRAY) {
				num.setText(Integer.valueOf(i+1).toString());

				//ここで削除した文抜きで文番号を整える
			Element sentence = DocumentHelper.createElement("sentence");
			sentence.addAttribute("id",num.getText());
			int id = Integer.valueOf(num.getText());
			Element compname = XMLBuilder.comp(CompornentData.compornent_name_list.get(id-1).getText());
			Element requirement = XMLBuilder.requirement(CompornentData.txlist.get(id-1).getText());
			sentence.add(compname);
			sentence.add(requirement);
			input.add(sentence);
			}else if(CompornentData.DB_list.get(i).getBackground()== Color.RED){
				}
			i++;

		}


	}

	static void input_write() {
		Xml_write writer = new Xml_write();
		writer.perform();
		writer.write();
	}
}
