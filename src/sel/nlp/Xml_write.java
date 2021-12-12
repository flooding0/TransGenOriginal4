package sel.nlp;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;

import sel.nlp.xml.XMLWriter;

public class Xml_write {
	static XSSFWorkbook workbook;
	static FileOutputStream out;
	static Document newDocument;
	static XMLWriter writer;
	static Document document;

	  public Xml_write() {
		  Xml_write.writer = new XMLWriter(new File("input.xml"));
	  }

	  void write() {
		    Xml_write.writer.write(Xml_write.newDocument, true);
		  }


	static void input_write() {


	}
}
