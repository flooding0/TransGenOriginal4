package sel.nlp;

import static org.apache.commons.digester3.binder.DigesterLoader.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import sel.nlp.decisionrule.Match;
import sel.nlp.decisionrule.Rule;
import sel.nlp.decisionrule.Rules;
import sel.nlp.xml.MyRulesModule;
import sel.nlp.xml.XMLWriter;

public class AnnotateAction {

  Document document;
  Rules rules;

  XMLWriter writer;

  void write() {
    this.writer.write(this.document);
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

    // load rule set
    MyRulesModule conditionRuleModule = new MyRulesModule();
    conditionRuleModule.setFile("decisionrulesxml.xml");

    DigesterLoader ruleLoader = newLoader(conditionRuleModule);
    Digester ruleDigester = ruleLoader.newDigester();

    try {
      File ruleF = new File("resources/s_t.xml");
      this.rules = (Rules) ruleDigester.parse(ruleF);
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }

//    File file = new File(outputFile);
//    writer = new XMLWriter(file);
  }

  void perform () {

    for(Rule rule: this.rules.getRules()) {
      applyRule(rule);
    }

  }

  private void applyRule(Rule rule) {
    List<Node> nodes = document.selectNodes(rule.getCommon());
    for (Node node: nodes) {
      if (isValid(node, rule.getConditions()) && isValid2(node,rule.getCondition2s())) {
        applyMatches(node, rule.getMatches());
      } else {
        ;
      }
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

  //condition2(否定の条件)
  private boolean isValid2(Node node, List<String> condition2s) {
	  //node=コンテキストノード,condition2s=条件(複数の場合あり)
    if (condition2s == null)
      return true;
    //condition2が記述されていない場合trueを返す(条件はクリアされた)
    for (String cond2: condition2s) {
      List<Node> childrenNodes = node.selectNodes(cond2);
//      System.err.println(node.selectNodes(cond2).toString());
      //conditionの記述に適合するノードを見つけてchildrenNodesリストにぶち込む
      if (childrenNodes.size() == 0)//何もぶち込めなかったらcondition2sは満たされたと判断
        return true;
    }
    /* every condition is true */
    return false;
  }


  private void applyMatches(Node node, List<Match> matches) {
    if (matches == null)
      return;

    for(Match match: matches) {
      applyMatch(node, match);
    }
  }

  private void applyMatch(Node node, Match match) {
    List<Node> childrenNodes = node.selectNodes(match.getExpression());
    String decision = match.getDecision();

    //System.err.println("================="+decision);
    //System.err.println("==========size="+childrenNodes.size());

    for(Node nnode: childrenNodes) {
   //  System.err.println((Element) nnode);
      ((Element) nnode).addAttribute("action-decision", decision);

    }
  }


  public static void main(String[] args) {
	  File log= new File("log.txt");
      try {
		FileWriter log_writer = new FileWriter(log,true);
		log_writer.write("\n" + "annotateAction Finish");
		  log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

    AnnotateAction annotater = new AnnotateAction();
    annotater.init(args);
    annotater.perform();
    annotater.write();
  }

  public void annotateaction() {
	  File log= new File("log.txt");
      try {
		FileWriter log_writer = new FileWriter(log,true);
		log_writer.write("\n" + "annotateAction Finish");
		  log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

    AnnotateAction annotater = new AnnotateAction();
    String[] filenames = {"annotatedtree.xml","annotatedtreeaction.xml"};
	annotater.init(filenames);
    annotater.perform();
    annotater.write();
  }

}
