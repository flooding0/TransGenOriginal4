package sel.nlp;

import static org.apache.commons.digester3.binder.DigesterLoader.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import sel.nlp.decisionrule.Rule;
import sel.nlp.decisionrule.Rules;
import sel.nlp.xml.MyRulesModule;
import sel.nlp.xml.XMLWriter;

public class Classify {

  Document document;
  Rules rules;

  XMLWriter writer;

  void write() {
    this.writer.write(this.document);
  }

  void init (String [] args)  {
//    String inputFile  = "tree.xml";
//    String outputFile = "classifiedtree.xml";


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

    // load rule set
    MyRulesModule conditionRuleModule = new MyRulesModule();
    conditionRuleModule.setFile("decisionrulesxml.xml");

    DigesterLoader ruleLoader = newLoader(conditionRuleModule);
    Digester ruleDigester = ruleLoader.newDigester();

    try {
      File ruleF = new File("resources/classification.xml");
      this.rules = (Rules) ruleDigester.parse(ruleF);
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }
  }

  void perform () {

    List<Node> sentenceNodes = document.selectNodes(this.rules.getTarget());

    for(Node node: sentenceNodes) {
      Set<String> decisions = new HashSet<String>();
      for(Rule rule: this.rules.getRules()) {
        applyRule(node, rule, decisions);
      }
      String decisionList = toStringDecisions(decisions);
      ((Element) node).addAttribute("decision", decisionList);
    }

  }

  private void applyRule(Node node, Rule rule, Set<String> decisions) {
    String decision = rule.getDecision();
    if (isValid(node, rule.getConditions())) {
      decisions.add(decision);
    } else {
      decisions.add("other");
    }
  }

  private boolean isValid(Node node, List<String> conditions) {
    if (conditions == null)
      return true;
    for (String cond: conditions) {
      List<Node> childrenNodes = node.selectNodes(cond);
      if (childrenNodes.size() == 0)
        /* nothing was matched */
        return false;
    }
    /* every condition is true */
    return true;
  }

  private String toStringDecisions(Set<String> decisions) {

    // return list of decision in text format
    // it removes "other" if other decisions are involved.

    if (decisions.contains("other") && decisions.size() > 1)
      decisions.remove("other");

    StringBuilder sb = new StringBuilder();
    for (String decision: decisions)
      sb.append(decision+", ");
    int size = sb.length();
    sb.delete(size-2, size); // delete extra ", "
    return sb.toString();
  }

  public static void main(String[] args) {

    Classify classifier = new Classify();

    classifier.init(args);
    classifier.perform();
    classifier.write();
    File log= new File("log.txt");
    try {
		FileWriter log_writer = new FileWriter(log,true);
		log_writer.write("\n" + "Classify Finish");
		log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
  }

  public void classify() {

	    Classify classifier = new Classify();

	    String[] filenames = {"tree.xml","classifiedtree.xml"};
		classifier.init(filenames);
	    classifier.perform();
	    classifier.write();

	  }
}
