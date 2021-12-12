package sel.nlp;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import sel.nlp.decisionrule.Rules;
import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;
//このプログラムでは結果データの抽出とresults.xmlへの書き込みを行っている
public class Extract {

  Document document;
  Document newDocument;
  Element newSentences;
  Rules rules;

  XMLWriter writer;

  void write() {
    this.writer.write(this.newDocument, true);
  }

  void init (String [] args)  {


    SAXReader reader = new SAXReader();

    try {
      if (args.length == 1) {
        this.document = reader.read(args[0]);
        this.writer = new XMLWriter(System.out);
      } else if (args.length == 2) {
        this.document = reader.read(args[0]);
        this.writer = new XMLWriter(new File(args[1]));
      } else {
        this.document = reader.read(System.in);
        this.writer = new XMLWriter(System.out);
      }
    } catch (DocumentException e) {
      e.printStackTrace();
    }

/*
    // load rule set
    MyRulesModule conditionRuleModule = new MyRulesModule();
    conditionRuleModule.setFile("decisionrulesxml.xml");

    DigesterLoader ruleLoader = newLoader(conditionRuleModule);
    Digester ruleDigester = ruleLoader.newDigester();

    try {
      File ruleF = new File("resources/output.xml");
      this.rules = (Rules) ruleDigester.parse(ruleF);
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }
*/
    newSentences = XMLBuilder.sentences();
    newDocument = DocumentHelper.createDocument();//createDocument()でxml,html全体を表すDocument型のオブジェクトを作る
    newDocument.add(newSentences);

  }

  void perform () {
    List<Node> sentenceNodes = document.selectNodes("//sentence");
    for (Node node: sentenceNodes) {
      Element newSentence =
        XMLBuilder.sentence(((Element)node).attribute("id").getValue());
      List<Node> varNodes = node.selectNodes(".//phrase[@decision='variable']");//状態変数
      List<Node> valNodes = node.selectNodes(".//phrase[@decision='variable-value']");//変数値
      List<Node> subinfos = node.selectNodes(".//phrase[@action-decision='subinfo']");//action
      List<Node> transitions = node.selectNodes(".//phrase[@action-decision='transition']");//transition
      List<Node> inneroperations = node.selectNodes(".//phrase[@action-decision='inner-operation']");//inneroperation
//xmlへの出力処理(状態変数)
      Element variables = DocumentHelper.createElement("variables");
      for (Node varNode: varNodes) {
        Element variableE = DocumentHelper.createElement("variable");
        variableE.addAttribute("phraseId",((Element)varNode).attribute("id").getValue());
        variables.add(variableE);
      }
//xmlへの出力処理(変数値)
      Element variableValues = DocumentHelper.createElement("variable-values");
      for (Node valNode: valNodes) {
        Element valueE = DocumentHelper.createElement("value");
        valueE.addAttribute("phraseId",((Element)valNode).attribute("id").getValue());
        variableValues.add(valueE);
      }
//xmlへの出力処理(subinfo)
      Element subinfo = DocumentHelper.createElement("set_of_subinfo");
      for (Node subinfoNode: subinfos) {
        Element subinfoE = DocumentHelper.createElement("subinfo");
        subinfoE.addAttribute("phraseId",((Element)subinfoNode).attribute("id").getValue());
        subinfo.add(subinfoE);
      }
//xmlへの出力処理(transition)
      Element transition = DocumentHelper.createElement("transitions");
      for (Node transitionNode: transitions) {
        Element transitionE = DocumentHelper.createElement("transition");
        transitionE.addAttribute("phraseId",((Element)transitionNode).attribute("id").getValue());
        transition.add(transitionE);
      }

//xmlへの出力処理(transition)
      Element inneroperation = DocumentHelper.createElement("inner-operations");
      for (Node inneroperationNode: inneroperations) {
        Element inneroperationE = DocumentHelper.createElement("inner-operation");
        inneroperationE.addAttribute("phraseId",((Element)inneroperationNode).attribute("id").getValue());
        inneroperation.add(inneroperationE);
      }

     newSentence.add(variables);
     newSentence.add(variableValues);
     newSentence.add(subinfo);
     newSentence.add(transition);
     newSentence.add(inneroperation);
      newSentences.add(newSentence);
    }
  }

  public static void main(String[] args) {

    Extract extractor = new Extract();

    extractor.init(args);
    extractor.perform();
    extractor.write();
  }

  public void extract() {

	    Extract extractor = new Extract();

	    String[] filenames = {"annotatedtreeaction.xml", "results.xml"};
		extractor.init(filenames );
	    extractor.perform();
	    extractor.write();
  }

}
