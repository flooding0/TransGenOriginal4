package sel.nlp;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;

public class CreateWindowResult {
	  Document document;
	  Document document2;
	  Document newDocument;
	  Element newSentences;
	  XMLWriter writer;
	  Frontpanel fp;
	  Read read =new Read();

	  void write() {
		    this.writer.write(this.newDocument, true);
		  }

	  void init(String[] args) {
		  SAXReader reader = new SAXReader();

		  try {
		      if (args.length == 1) {
		        this.document = reader.read(args[0]);
		        this.writer = new XMLWriter(System.out);
		      } else if (args.length == 2) {
		        this.document = reader.read(args[0]);
		        this.writer = new XMLWriter(new File(args[1]));
		      }else if (args.length == 3) {
			    this.document = reader.read(args[0]);//result
			    this.document2 = reader.read(args[1]);//anotatedtree
			    this.writer = new XMLWriter(new File(args[2]));//output
			  } else {
		        this.document = reader.read(System.in);
		        this.writer = new XMLWriter(System.out);
		      }
		    } catch (DocumentException e) {
		      e.printStackTrace();
		    }
		  newDocument = DocumentHelper.createDocument();
		  newSentences = XMLBuilder.sentences();
		  newDocument.add(newSentences);
	  }

	  public void CWR() {
		  CreateWindowResult cwr = new CreateWindowResult();
		  String[] input = {"",""};
		  cwr.init(input);
	  }
}
