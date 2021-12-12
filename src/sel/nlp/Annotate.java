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

public class Annotate {

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
    //ルールの読み込みルールを箱に詰める
    MyRulesModule conditionRuleModule = new MyRulesModule();
    conditionRuleModule.setFile("decisionrulesxml.xml");//箱に詰める

    //読み込みルールの入った箱を解析器が受け取る
    DigesterLoader ruleLoader = newLoader(conditionRuleModule);//受け取る
    Digester ruleDigester = ruleLoader.newDigester();//出発準備

    try {
      File ruleF = new File("resources/variables.xml");
      this.rules = (Rules) ruleDigester.parse(ruleF);
      //準備完了した解析器がvariables.xmlの中に記述されているルールを解析する．
      //解析したルールはAnnotate.java内で定義されたRules型のobject『rules』に格納される．
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }

//    File file = new File(outputFile);
//    writer = new XMLWriter(file);
  }

  void perform () {
	  //まずthis.rulesをruleごとに分解する
	  //getRules()でArrayList<Rule>()でルールを持ってくる．
    for(Rule rule: this.rules.getRules()) {
    	//Ruleという型は  private String decision;
    	// private List<String> conditions;
    	// private List<String> condition2s;
    	// private List<Match> matches;
    	// private String common;
    	//で構成されている．
      applyRule(rule);
    }

  }

  private void applyRule(Rule rule) {
	  //System.err.println(rule.toString());
    List<Node> nodes = document.selectNodes(rule.getCommon());//ここでcommonのパスまで移動
    for (Node node: nodes) {
    	//次のif文でconditionで記述した条件を満たすか確認する．
      if (isValid(node, rule.getConditions())  && isValid2(node,rule.getCondition2s())) {
    	  applyMatches(node, rule.getMatches());
      } else {
        ;
      }
    }
  }

//↓condition(肯定の条件)
  private boolean isValid(Node node, List<String> conditions) {
	  //node=コンテキストノード,conditions=条件(複数の場合あり)
    if (conditions == null)
      return true;
    //conditionが記述されていない場合trueを返す(条件はクリアされた)
    for (String cond: conditions) {
      List<Node> childrenNodes = node.selectNodes(cond);
      //conditionの記述に適合するノードを見つけてchildrenNodesリストにぶち込む
      if (childrenNodes.size() == 0)//何もぶち込めなかったらconditionは満たされなかったと判断
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
      System.err.println("condition2");
      System.err.println(node.selectNodes(cond2).toString());
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
//matchが記載されていない場合処理終了
    for(Match match: matches) {
    //matchタグの数だけ処理する
      applyMatch(node, match);
      //↓apply matchのメソッドの内容
    }
  }


  private void applyMatch(Node node, Match match) {
	  //指定されたmatchタグ一つの処理を行う
    List<Node> childrenNodes = node.selectNodes(match.getExpression());
    //matchの記述に適合するノードを全てリストにぶち込む
    String decision = match.getDecision();
  for(Node nnode: childrenNodes) {
    	//ここでchildrenNodes(複数の要素を持つリスト)で指定された要素にdecision属性を付加している
      ((Element) nnode).addAttribute("decision", decision);
    }
  }


  public static void main(String[] args) {

    Annotate annotater = new Annotate();

    annotater.init(args);
    annotater.perform();
    annotater.write();

    File log= new File("log.txt");
    try {
		FileWriter log_writer = new FileWriter(log,true);
		log_writer.write("\n" + "Annotate Finish");
		log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
  }

  public void annotate() {

	    Annotate annotater = new Annotate();

	    String[] filenames = {"classifiedtree.xml","annotatedtree.xml"};
		annotater.init(filenames);
	    annotater.perform();
	    annotater.write();


	  }
}
