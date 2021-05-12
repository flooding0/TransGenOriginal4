package sel.nlp;

import static org.apache.commons.digester3.binder.DigesterLoader.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.dom4j.Document;
//import org.dom4j.io.XMLWriter;
import org.dom4j.DocumentHelper;

//import org.w3c.dom.Document;
//import org.jdom2.Element;

import org.dom4j.Element;

import sel.nlp.parserule.Condition;
import sel.nlp.parserule.Rule;
import sel.nlp.parserule.RuleSet;
import sel.nlp.xml.MyRulesModule;
import sel.nlp.xml.XMLBuilder;
import sel.nlp.xml.XMLWriter;

public class Analyse {

  Tokenizer tokenizer;
  int tokenSize;
  int sentenceID = 1;
  Element sentence;
  Element sentences;
  Document document;
  RuleSet parseRules;

  List<String> words;
  List<String> poses;
  List<String> morphemes;
  List<String> set_of_subinfo;

  BufferedReader reader;

  BufferedReader getReader() {
    return this.reader;
  }

  XMLWriter writer;

  void write() {
    writer.write(this.document, true);
  }

  void init (String [] args)  {
    /*
       // Search mode
        builder.mode(Mode.SEARCH);
        Tokenizer search = builder.build();
        List<Token> tokenSearch = search.tokenize(parseWord);

        // Extends mode
        builder.mode(Mode.EXTENDED);
        Tokenizer extended = builder.build();
    */
    String dic = "resources/userdict.txt";
    try {if (args.length == 1) {
        this.reader = new BufferedReader(new FileReader(args[0]));
        this.writer = new XMLWriter(System.out);
      } else if (args.length == 2) {
        this.reader = new BufferedReader(new FileReader(args[0]));
        this.writer = new XMLWriter(new File(args[1]));
      } else if (args.length >= 3) {
        this.reader = new BufferedReader(new FileReader(args[0]));
        this.writer = new XMLWriter(new File(args[1]));
        dic = args[2];
      } else {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new XMLWriter(System.out);
      }
    } catch(FileNotFoundException e) {
      System.err.println("input file is not found");
    }

    try {
      tokenizer = Tokenizer.builder().userDictionary(dic).build();
    } catch (FileNotFoundException  e) {
      System.err.println("input file is not found.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    MyRulesModule ruleModule = new MyRulesModule();
    ruleModule.setFile("parserulesxml.xml");
    DigesterLoader loader = newLoader(ruleModule);
    Digester digester = loader.newDigester();

    try {
      File input = new File("resources/parseRules.xml");
      this.parseRules = (RuleSet) digester.parse(input);
    } catch(Exception e) {
      System.err.println("Exception occurred.");
      e.printStackTrace();
    }

//    System.out.println(this.parseRules.getAliases());

// output
    this.sentences = XMLBuilder.sentences();
    this.document = DocumentHelper.createDocument();
    this.document.add(this.sentences);

  }

  void initSentence() {
    this.sentence = XMLBuilder.sentence(String.valueOf(sentenceID));
    this.sentences.add(sentence);
    tokenSize = 1;
    sentenceID++;
  }

  void performMorphologicalAnalysis (String line) {

    words     = new ArrayList<String>();
    poses     = new ArrayList<String>();
    morphemes = new ArrayList<String>();
    set_of_subinfo = new ArrayList<String>();

    List<Token> result = tokenizer.tokenize(line);

    for (Token token : result) {
      String word = token.getSurfaceForm();
      String tags = token.getAllFeatures();
      String partOfSpeech = (tags.split(",", 0))[0];
      String morpheme = (tags.split(",", 0))[1];

      words.add(word);
      poses.add(partOfSpeech);
      morphemes.add(morpheme);
      set_of_subinfo.add(null);

      //System.err.println("word="+word+" pos:"+ partOfSpeech +" info:"+ morpheme);

        // make xml tag
      Element xtoken = XMLBuilder.token(String.valueOf(tokenSize));
      sentence.add(xtoken);

        // make xml tag
      Element xword = XMLBuilder.word(word);
      xtoken.add(xword);


      String attr = translateAlias(partOfSpeech);

        // make xml tag
//      Element xpos = XMLBuilder.pos(partOfSpeech);
      Element xpos = XMLBuilder.pos(attr);
      xtoken.add(xpos);

      attr = translateAlias(morpheme);
        // make xml tag
//      Element xmorpheme = XMLBuilder.morpheme(morpheme);
      Element xmorpheme = XMLBuilder.morpheme(attr);
      xtoken.add(xmorpheme);

      tokenSize++;
    }
  }

  void parse () {
    int nextTokenId = 1;

/*
  Box[x][y][z]
   x: column
   y: row
   z: parse result
*/


    /* initialize */
    Box boxes[][][] = new Box[tokenSize][tokenSize][tokenSize];
    for (int i = 0; i < tokenSize ; i++) {
      for (int j = 0; j < tokenSize ; j++) {
        for (int k = 0; k < tokenSize; k++) {
          boxes[i][j][k] = new Box();
        }
      }
    }

    /* initial setting 一巡目*/
    for (int i = 0; i < tokenSize - 1; i++) {
      boxes[i][i][0].phrase   = words.get(i);//string の単語が入る
      boxes[i][i][0].pos      = poses.get(i);
      boxes[i][i][0].morpheme = morphemes.get(i);
      boxes[i][i][0].subInfo = set_of_subinfo.get(i);
      boxes[i][i][0].id = nextTokenId;
      nextTokenId ++;
    }


    /* stat parsing */
//    for (int d = 1; d <= tokenSize ; d++) {
//          for (int i = 0; i < tokenSize - d; i++) {
    for (int d = 0; d < tokenSize ; d++) {
      for (int i = 0; i < tokenSize - d - 1; i++) {

        int j = i + 1 + d;//tokensize
        int ii = i + 1;
        int jj = i;

        for (int k = i; k < j; k++) {

          int x=0;
          int y=0;

          while (boxes[i][jj][x].pos != null) {
            while (boxes[ii][j][y].pos != null) {
              int z = 0;
              List<String>result =
                synthesis(boxes[i][jj][x].pos, boxes[ii][j][y].pos,
                          boxes[i][jj][x].morpheme, boxes[ii][j][y].morpheme,
                          boxes[i][jj][x].phrase,   boxes[ii][j][y].phrase,
                          boxes[i][jj][x].subInfo,   boxes[ii][j][y].subInfo
                          );

              if (!result.contains("none")) {//合成が成功したとき
                while (boxes[i][j][z].pos != null)
                  z++;
                boxes[i][j][z].pos      = result.get(0);//合成語の品詞
                boxes[i][j][z].subInfo  = result.get(1);//合成後のサブ情報
                  boxes[i][j][z].phrase = //合成語のテキスト
                  boxes[i][jj][x].phrase +
                  boxes[ii][j][y].phrase;
                boxes[i][j][z].id = nextTokenId;//合成後のID
                Element target = null;
                String theNextTokenId = String.valueOf(nextTokenId);
                String attr = translateAlias(boxes[i][j][z].pos);//合成した文節の名前(英訳処理)
                String subinfo = boxes[i][j][z].subInfo;

                if (attr != null) {//attrがnullになることはない
                  String a1 = String.valueOf(boxes[i][jj][x].id);//合成対象1のid
                  Element token1 =//ここでエレメント生成
                    XMLBuilder.phrase(a1, boxes[i][jj][x].phrase);
                  String a2 = String.valueOf(boxes[ii][j][y].id);//合成対象2のid
                  Element token2 =//ここでエレメント生成
                    XMLBuilder.phrase(a2, boxes[ii][j][y].phrase) ;
                  //サブ情報の有無によって、targetの構成を変更する必要がある。
                  target =
                    XMLBuilder.phrase(theNextTokenId, attr,subinfo, token1, token2);//文節id,新文節名,パーツ1,パーツ2
                  sentence.add(target);
                } else {
                  ; // nothing to do
                }

                nextTokenId++;
              }
              y++;
            }
            x++;
          }
          ii++;
          jj++;
        }
      }
    }
  }



  private String translateAlias(String japanese) {
    Map<String, String> map = this.parseRules.getAliases();

    if (map.containsKey(japanese))
      return map.get(japanese);//japanese→english
    else
      return japanese;
  }

  int  count_rule() {
	  int count=0;
	  for (Rule rule: this.parseRules.getRules()) {
		  count ++;
	  }
	  System.err.println(count);
	return count;
	  }

  private List<String> synthesis (String pos1, String pos2,
                           String morph1, String morph2,
                           String word1, String word2,
                           String subinfo1, String subinfo2) {

    String none = "none";
    List<String> sys_result_none= new ArrayList<String>();
    sys_result_none.add(none);

    if ((pos1 == null) || (pos2 == null) ||
//        (morph1 == null) || (morph2 == null) ||
        (word1 == null) || (word2 == null)
      )
      return sys_result_none;
    for (Rule rule: this.parseRules.getRules()) {
      List<String> sys_result= new ArrayList<String>();
      String goal = rule.getGoal();
      String sub = rule.getSubinfo();
      sys_result.add(goal);
      sys_result.add(sub);
      Condition phrase1 = null;
      Condition phrase2 = null;
      for (Condition cond: rule.getConditions()) {//２つ入ってるから2回回す
        if (cond.getSeq().equals("0")) {
          phrase1 = cond;
        } else { // cond.getSeq().euquals("1")
          phrase2 = cond;
        }
      }
//ここで合成規則を持ってくる
      String rule1Pos = phrase1.getValue();//品詞
      String rule2Pos = phrase2.getValue();
      String rule1Morpheme = phrase1.getMorpheme();//形態素
      String rule2Morpheme = phrase2.getMorpheme();
      String rule1Word = phrase1.getText();//テキスト
      String rule2Word = phrase2.getText();
      String rule1suninfo = phrase1.getSubinfo();
      String rule2suninfo = phrase2.getSubinfo();
//ここら辺に構文解析のルールを追加する
//構文解析をもっと精密にする
//形態素、テキスト情報による除外機能
//phraseへのサブ情報付加等
      if (isPairMatched(rule1Pos, rule2Pos, pos1, pos2) &&
          isPairMatched(rule1Morpheme, rule2Morpheme, morph1, morph2) &&
          isPairMatched(rule1Word, rule2Word, word1, word2)&&
          isPairMatched(rule1suninfo, rule2suninfo, subinfo1, subinfo2)) {
            return sys_result;
      } else {
        ; // to next search
      }
    }
    return sys_result_none;
  }


  private boolean isPairMatched (String rule1, String rule2,
                               String key1, String key2) {

    if ((rule1 == null) && (rule2 == null)) {
      return true; // this logic is very important to reduce the complexity
    } else if (rule1 == null) { // and (rule2!= null)
      if (rule2.equals(key2))
        return true;
      else
        return false;
    } else if (rule2 == null) { // and (rule1!= null)
      if (rule1.equals(key1))
        return true;
      else
        return false;
    } else {
      if ((rule1.equals(key1)) && (rule2.equals(key2)))
        return true;
      else
        return false;
    }
  }

  public static void main(String[] args) {

    Analyse analyser = new Analyse();
    analyser.init(args);
    BufferedReader reader = analyser.getReader();

    try {
//      int count = 0;//ここに解析したい文の番号を入れる。いらなかったら下のif文とともにコメントアウト

        File log= new File("log.txt");
//        FileWriter log_writer = new FileWriter(log);
        FileWriter log_writer = new FileWriter(log,true);
      String line;
      int count = analyser.count_rule();//ルール数のカウント
      while ((line = reader.readLine()) != null) {
    	  //指定した文のみ解析
//    	  if(count==9) {
//    		  analyser.initSentence();
//    		  analyser.performMorphologicalAnalysis(line);
//    		  analyser.parse();
//    	  }
//    	  count++;
    	  //↑↓どっちか必ずコメントアウト

    	//全文解析
    		  analyser.initSentence();
    		  analyser.performMorphologicalAnalysis(line);
    		  analyser.parse();
         }
      String cnt = "" + count;
      log_writer.write(cnt);
      log_writer.write("\n"+"Analyse Finish");
	  log_writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    analyser.write();
  }

  public void analysis() {
	  String[] filenames = {"input2.txt","analysis.xml"};
	    Analyse analyser = new Analyse();
	    analyser.init(filenames);
	    BufferedReader reader = analyser.getReader();

	    try {
//	      int count = 0;//ここに解析したい文の番号を入れる。いらなかったら下のif文とともにコメントアウト

	        File log= new File("log.txt");
//	        FileWriter log_writer = new FileWriter(log);
	        FileWriter log_writer = new FileWriter(log,true);
	      String line;
	      int count = analyser.count_rule();//ルール数のカウント
	      while ((line = reader.readLine()) != null) {
	    	  //指定した文のみ解析
//	    	  if(count==9) {
//	    		  analyser.initSentence();
//	    		  analyser.performMorphologicalAnalysis(line);
//	    		  analyser.parse();
//	    	  }
//	    	  count++;
	    	  //↑↓どっちか必ずコメントアウト

	    	//全文解析
	    		  analyser.initSentence();
	    		  analyser.performMorphologicalAnalysis(line);
	    		  analyser.parse();
	         }
	      String cnt = "" + count;
	      log_writer.write(cnt);
	      log_writer.write("\n"+"Analyse Finish");
		  log_writer.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    analyser.write();
  }
}
