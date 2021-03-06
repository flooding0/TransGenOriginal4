package sel.nlp;
import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;
public class ExtractWord {
	  Document document;
	  Document document2;
	  Document newDocument;
	  Element newSentences;
	  XMLWriter writer;


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


	  void perform() {
		  List<Node> sentenceNodes = document.selectNodes("//sentence");
		  List<Node> sentenceNodes2 = document2.selectNodes("//sentence");
		  for(int i=0; i<sentenceNodes.size(); i++) {
		  Node node = sentenceNodes.get(i);
		  String sentenceid = ((Element)node).attribute("id").getValue();
		  Element newSentence =
		  XMLBuilder.sentence(sentenceid);
		  List<Node> varNodes = node.selectNodes("./variables/variable");//状態変数
	      List<Node> valNodes = node.selectNodes("./variable-values/value");//変数値
	      List<Node> subinfoNodes = node.selectNodes("./set_of_subinfo/subinfo");//subinfo
	      List<Node> transitions = node.selectNodes("./transitions/transition");//transition
	      List<Node> inneroperations = node.selectNodes("./user_operations/user_operation");//inner-operation
	      Element variables = DocumentHelper.createElement("variables");


	      for(Node var:varNodes) {
	    	  String phraseid = ((Element)var).attribute("phraseId").getValue();
	    	  String Elementname = "variable";
	    	  Element variable = DocumentHelper.createElement(Elementname);
	    	  Node node2 = sentenceNodes2.get(i);
	    	  List<Node> variable_components = node2.selectNodes(".//phrase[@id='" + phraseid + "']//token");
	    	  //phraseId以下の単語全てをリストにぶち込む
	    	  String word ="";
	    	  for(Node token:variable_components) {
	    		  List<Element> tokenE = ((Element)token).elements("word");
	    		  word = word + tokenE.get(0).getText();
	    	  }
	    	  ResultSpace.create_result(sentenceid,Elementname, word);
	    	  variable.addText(word);
	    	  variables.add(variable);
	      }
	      Element variable_variables = DocumentHelper.createElement("variable-values");
	      for(Node val:valNodes) {
	    	  String phraseid = ((Element)val).attribute("phraseId").getValue();
	    	  String Elementname = "value";
	    	  Element value = DocumentHelper.createElement(Elementname);
	    	  Node node2 = sentenceNodes2.get(i);
	    	  List<Node> value_components = node2.selectNodes(".//phrase[@id='" + phraseid + "']//token");
	    	  String word ="";
	    	  for(Node token:value_components) {
	    		  List<Element> tokenE = ((Element)token).elements("word");
	    		  word = word + tokenE.get(0).getText();
	    	  }
	    	  ResultSpace.create_result(sentenceid,Elementname, word);
	    	  value.addText(word);
	    	  variable_variables.add(value);
	      }

	      Element set_of_subinfo = DocumentHelper.createElement("set_of_subinfo");
	      for(Node subinfo:subinfoNodes) {
	    	  String phraseid = ((Element)subinfo).attribute("phraseId").getValue();
	    	  String Elementname = "subinfo";
	    	  Element subinfoE = DocumentHelper.createElement(Elementname);
	    	  Node node2 = sentenceNodes2.get(i);
	    	  List<Node> subinfo_components = node2.selectNodes(".//phrase[@id='" + phraseid + "']//token");
	    	  String word ="";
	    	  for(Node token:subinfo_components) {
	    		  List<Element> tokenE = ((Element)token).elements("word");
	    		  word = word + tokenE.get(0).getText();
	    	  }
	    	  //ここからGUI上で表示するデータの作成


	    	  //文番号、要素の種類、要素記述の情報を有するLinkedHashMapデータができる
	    	  ResultSpace.create_result(sentenceid,Elementname, word);
	    	  subinfoE.addText(word);
	          set_of_subinfo.add(subinfoE);
	      }

	      Element transitionsE = DocumentHelper.createElement("transitions");
	      for(Node transition:transitions) {
	    	  String phraseid = ((Element)transition).attribute("phraseId").getValue();
	    	  String Elementname = "transition";
	    	  Element TE = DocumentHelper.createElement(Elementname);
	    	  Node node2 = sentenceNodes2.get(i);
	    	  List<Node> transition_components = node2.selectNodes(".//phrase[@id='" + phraseid + "']//token");
	    	  String word ="";
	    	  for(Node token:transition_components) {
	    		  List<Element> tokenE = ((Element)token).elements("word");
	    		  word = word + tokenE.get(0).getText();
	    	  }
	    	  ResultSpace.create_result(sentenceid,Elementname, word);
	    	  TE.addText(word);
	    	  transitionsE.add(TE);
	      }

	      Element inneroperationsE = DocumentHelper.createElement("user_operations");
	      for(Node inneroperation:inneroperations) {
	    	  String phraseid = ((Element)inneroperation).attribute("phraseId").getValue();
	    	  String Elementname = "user_operation";
	    	  Element IE = DocumentHelper.createElement(Elementname);
	    	  Node node2 = sentenceNodes2.get(i);
	    	  List<Node> inneroperation_components = node2.selectNodes(".//phrase[@id='" + phraseid + "']//token");
	    	  String word ="";
	    	  for(Node token:inneroperation_components) {
	    		  List<Element> tokenE = ((Element)token).elements("word");
	    		  word = word + tokenE.get(0).getText();
		    	  }
	    	  ResultSpace.create_result(sentenceid,Elementname, word);
	    	  IE.addText(word);
	    	  inneroperationsE.add(IE);
	      }

	      ResultSpace.gridpanel.add(new Label2("--------------------------------------------------------------------",200));

	      newSentence.add(variables);
	      newSentence.add(variable_variables);
	      newSentence.add(set_of_subinfo);
	      newSentence.add(transitionsE);
	      newSentence.add(inneroperationsE);
	      newSentences.add(newSentence);
		  }
	  }

	  public static void main(String[] args) {
		    ExtractWord extractor = new ExtractWord();
		    extractor.init(args);
		    extractor.perform();
		    extractor.write();
		  }

	  public void extractword() {
		    ExtractWord extractor = new ExtractWord();
		    String[] filenames = {"results.xml", "annotatedtree.xml", "result2.xml"};
			extractor.init(filenames);
		    extractor.perform();
		    extractor.write();
		  }

}
