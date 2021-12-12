package sel.nlp;

import static org.apache.commons.digester3.binder.DigesterLoader.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sel.nlp.parsemain.Node;
import sel.nlp.parsemain.Phrase;
import sel.nlp.parsemain.Sentence;
import sel.nlp.parsemain.Token;
import sel.nlp.xml.MyRulesModule;
import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;

public class BuildTree {

  List<Sentence> sentences;
  Element newSentences;
  Document document;

  XMLWriter writer;

  void write() {
    writer.write(this.document, true);
  }

  void init (String [] args)  {
//    String inputFile  = "analysis.xml";
//    String outputFile = "tree.xml";


    MyRulesModule ruleModule = new MyRulesModule();
    ruleModule.setFile("prerulesxml.xml");
    DigesterLoader loader = newLoader(ruleModule);
    Digester digester = loader.newDigester();

    try {
      if (args.length == 1) {
        File input = new File(args[0]);
        this.sentences = (List<Sentence>) digester.parse(input);

        this.writer = new XMLWriter(System.out);
      } else if (args.length == 2) {
        File input = new File(args[0]);
        this.sentences = (List<Sentence>) digester.parse(input);

        File output = new File(args[1]);
        this.writer = new XMLWriter(output);
      } else {
        this.sentences = (List<Sentence>) digester.parse(System.in);
        this.writer = new XMLWriter(System.out);
      }
    } catch(FileNotFoundException e) {
      System.err.println("input file not found");
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }


    newSentences = XMLBuilder.sentences();
    document = DocumentHelper.createDocument();
    document.add(newSentences);

  }

  void perform () {
    int n = sentences.size();
    System.out.println(n+"個の文があるよ");
//    System.err.println("文の数は = "+n);

   for (int i=0; i< n; i++) {//sentenceの数だけループ(i=1から始めてもよいがfor文の始まりの数が右往左往するのは可読性が低いので0に統一)
    	//i+1はsentenceの番号
      int size = getNumberOfTokens(i+1);//Tokenの数を持ってくる
//      System.err.println((i)+"番目のsentence::"+"tokenの数は" + size);//文章の最初に""(空白文字)が入るのが通常の仕様らしい
      for(Phrase phrase:getStatements(i+1)) {//sentenceの持つtypeが"sentence"のphraseの数だけループ
    	  /*
        System.out.println(phrase);
        Phrase p1 = phrase.getPhrases().get(0);
        Phrase p2 = phrase.getPhrases().get(1);
        String p1id = p1.getId();
        String p2id = p2.getId();
        Node pp1 = getMatchedNode(i+1, p1id);
        Node pp2 = getMatchedNode(i+1, p2id);
        System.out.println("sub phrase1");
        System.out.println(pp1);
        System.out.println("sub phrase2");
        System.out.println(pp2);
        */

        Pair<Element,Integer> pair = buildNode(i+1, phrase.getId());//sentenceの番号とphraseの番号でリスト作成

        Element body = pair.getKey();//pairのkeyはElementだから問題ない->Element=Elementになってる
        if (pair.getValue() == size ) { // only output full parsed sentence
          Element sentence = XMLBuilder.sentence(String.valueOf(i+1));//Extractの出力に合わせるため+1する
          sentence.add(body);
          this.newSentences.add(sentence);
        }
      }
    }
  }

  private Pair<Element,Integer> buildNode(int sentenceID, String phraseID) {
    Node node = getMatchedNode(sentenceID, phraseID);
    Element element = null;
    int n = 0;

    if (node instanceof Token) {
      Token token = (Token) node;

      element = XMLBuilder.token(token.getId());
      Element pos = XMLBuilder.pos(token.getPos());
      Element morpheme = XMLBuilder.morpheme(token.getMorpheme());
      Element word = XMLBuilder.word(token.getWord());

      element.add(word);
      element.add(pos);
      element.add(morpheme);
      n = 1;
    } else if (node instanceof Phrase) {
      Phrase phrase = (Phrase) node;
      Phrase p1 = ((Phrase) (phrase.getNodes().get(0)));
      Phrase p2 = ((Phrase) (phrase.getNodes().get(1)));
      String p1id = p1.getId();
      String p2id = p2.getId();

      Node pp1 = getMatchedNode(sentenceID, p1id);
      Node pp2 = getMatchedNode(sentenceID, p2id);
/*
      System.out.println("====sub phrase1");
      System.out.println(pp1.getId());
      System.out.println("====sub phrase2");
      System.out.println(pp2.getId());
*/
/*
      Element element1 = buildNode(sentenceID, p1id);
      Element element2 = buildNode(sentenceID, p2id);
      */
      Pair<Element, Integer> pair1 = buildNode(sentenceID, p1id);
      Pair<Element, Integer> pair2 = buildNode(sentenceID, p2id);

/*
        remove punctuations from tree because they are not needed
*/

      if ((pp2 instanceof Token) && ((Token) pp2).getPos().equals("punctuation")) {
//punctuation = 句読点
        element = pair1.getKey();
        n = pair1.getValue() + pair2.getValue();
        /* Important note: We calcurate n as if pair2 is used */

//        if (((Token) pp2).getMorpheme().equals("period"))
//          element.addAttribute("type", "sentence-end");
        //↑このif文は"～～"+" 。"で構成されているphraseの属性を全てsentenceにしてしまうため無効にした。
        //phraseの属性の処理はすべてAnalyse.javaで設定するように仕様を変更する20200108
      } else {
        element = XMLBuilder.phrase(phrase.getId(), phrase.getType(), phrase.getSubinfo(),
                                    pair1.getKey(), pair2.getKey());
        n = pair1.getValue() + pair2.getValue();//合成したphraseのTokenの数
      }
    }
    return new Pair<Element, Integer>(element, n);//Elementとそのphraseが持つTokenの数を返す。
  }

  private Node getMatchedNode(int sentenceID, String phraseID) {//sentenceはただのintegerTypeの変数(探索不可)
//このメソッドではint型のsentenceIDとSentence型のsentence.getId()でIdを照合してsentence型のメソッドに探索を委託している
    for (Sentence sentence: this.sentences) {//this.sentenceはList<sentence>::sentence下のtoken, phraseを探すために引っ張ってくる
      if (sentence.getId().equals(String.valueOf(sentenceID))) {
        for (Phrase phrase: sentence.getPhrases()) {//phrase照合
          if (phrase.getId().equals(phraseID))
            return phrase;
        }
        for (Token token: sentence.getTokens()) {//token照合
          if (token.getId().equals(phraseID))
            return token;
        }
      }
    }
    return null;
  }

//  private int getSizeSentences() {
//    return this.sentences.size();
//  }

  private List<Phrase> getStatements(int sentenceID) {//sentence, Typeが"sentence"のphraseを照合するメソッド
    List<Phrase> list = new ArrayList<Phrase> ();//返り値はList<phrase>
    for (Sentence sentence: this.sentences) {
      if (sentence.getId().equals(String.valueOf(sentenceID))) {
        for (Phrase phrase: sentence.getPhrases()) {
          if (phrase.getType().equals("sentence"))
            list.add(phrase);
        }
      }
    }
    return list;
  }


  private int getNumberOfTokens(int sentenceID) {//this.sentenceのtokenの数を持ってくる
    for (Sentence sentence: this.sentences) {
      if (sentence.getId().equals(String.valueOf(sentenceID))) {
        return sentence.getTokens().size();
      }
    }
    return 0;
  }


  public static void main(String[] args) {
    BuildTree builder = new BuildTree ();

    builder.init(args);
    builder.perform();
    builder.write();
    File log= new File("log.txt");
	  FileWriter log_writer;
	try {
		log_writer = new FileWriter(log,true);
		log_writer.write("\n" + "BuildTree Finish");
		  log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}

  }

  public void buildtree() {
	    BuildTree builder = new BuildTree ();

	    String[] filenames = {"analysis.xml","tree.xml"};
		builder.init(filenames );
	    builder.perform();
	    builder.write();
	    }

}
