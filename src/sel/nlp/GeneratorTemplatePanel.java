package sel.nlp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JLabel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
/*
 * @brief LTL変換への日本語テンプレート生成
 * @
 */
public class GeneratorTemplatePanel{

    Map<String, Map<String, LTLPattern>> propertyPatternMap = new LinkedHashMap<>();
    Map<String, List<String>> mapSentence2Variables = new TreeMap<>();
	public  Map<String, Map<String, LTLPattern>> getPropertyPatternMap() {
		return propertyPatternMap;
	}

	public GeneratorTemplatePanel(String filePath){
		analyzeRule(filePath);
	}
	public TemplateLTLPanel generateTemplatePanel(String patternClass, String pattern, String scope) {
		TemplateLTLPanel panel = new TemplateLTLPanel(scope);
		panel.setLTLPattern(propertyPatternMap.get(patternClass).get(pattern));
		for(Pair<Boolean, String> unit : propertyPatternMap.get(patternClass).get(pattern).getTemplateListOfScope(scope)) {
			if (unit.getKey()) { // case of variables.
				Textfield1 txtfield = new Textfield1(unit.getValue());
				System.out.println("variable: "+unit.getValue());
				panel.setVariablefield(txtfield, unit.getValue());
			}
			else { // case of text
				System.out.println("text: "+unit.getValue());
				JLabel label = new JLabel(unit.getValue());
				panel.add(label);
			}
		}
		return panel;
	}
	// XMLファイルからテンプレート取得
	private void analyzeRule(String filePath) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		  try {
			doc = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		List<Node> nodes = doc.selectNodes("/ltl_gen_rules/*");
		for(Iterator<Node>scopeNodeitr = nodes.iterator();scopeNodeitr.hasNext();) {
	        Node node =scopeNodeitr.next();
	        propertyPatternMap.put(node.getName(), new LinkedHashMap<>());
	    }
		for(Entry<String, Map<String, LTLPattern>> entry:propertyPatternMap.entrySet()) {
		    Map<String, LTLPattern> patternMap = entry.getValue();
			nodes = doc.selectNodes("/ltl_gen_rules/"+entry.getKey()+"/*");
			for(Iterator<Node> nodeItr = nodes.iterator(); nodeItr.hasNext();) {
		        Node node = nodeItr.next();

				patternMap.put(node.getName(), new LTLPattern(node.getName()));
				patternMap.get(node.getName()).patternSemantic = ((Element)node).attributeValue("semantic");

				LTLPattern nowPattern = patternMap.get(node.getName());

				List<Node> scopeNodes = doc.selectNodes(node.getPath()+"/templates/*");
				for(Iterator<Node>scopeNodeitr = scopeNodes.iterator();scopeNodeitr.hasNext();) { // loop for template of patern
			        Node scopeNode =scopeNodeitr.next();
			        Element temp_elem = (Element)scopeNode;
					nowPattern.patternTemplates.add(new Pair<Boolean, String>(temp_elem.attributeValue("id").equals("variable")?true:false, scopeNode.getStringValue()));
			    }

				scopeNodes = doc.selectNodes(node.getPath()+"/*");
				for(Iterator<Node>scopeNodeitr = scopeNodes.iterator();scopeNodeitr.hasNext();) { // loop for ltl and semantic in each scope
			        Node scopeNode =scopeNodeitr.next();
			        Element temp_elem = (Element)scopeNode;
					nowPattern.LTLs.put(scopeNode.getName(), temp_elem.attributeValue("ltl"));
					nowPattern.scopeSemantics.put(scopeNode.getName(), temp_elem.attributeValue("semantic"));
			    }

				for(String scope:nowPattern.scopeList) {
					scopeNodes = doc.selectNodes(node.getPath()+"/"+scope+"/templates/*");
					for(Iterator<Node>scopeNodeitr = scopeNodes.iterator();scopeNodeitr.hasNext();) { // loop for template in each scope
				        Node scopeNode =scopeNodeitr.next();
				        Element temp_elem = (Element)scopeNode;
				        nowPattern.scopeTemplates.get(scope).add(new Pair<Boolean, String>(temp_elem.attributeValue("id").equals("variable")?true:false, scopeNode.getStringValue()));
				    }
				}
		    }
		}
	}
	private class WordToken{
		public String word;
		public int id;
        public WordToken(String argWord, int argId){
            word = argWord;
            id = argId;
        }
	}
    // "AまでBでない"という文章から抽出された変数がA, Bどちらのものか判別する
    // results.xmlから各文の変数idを引っ張ってくる (sentences/sentence/variables/phraseId)
    // classifiedtree.xmlから各変数のidからその変数が文のどこにあるか特定する(このphraseは[2,6]の場所だよみたいな)(sentences/sentence/phrase/...)
    // DFSで当該idまでたどり着いたらそこから手前の文章を構築して, 文字数とかで場所を特定する
    // 場所を特定後はその場所がA, Bどちらに属しているか判定する
	public void extractVariableTag(String resultPath, String annotatedPath) {
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(resultPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List<Node> nodes = doc.selectNodes("/sentences/sentence/variables/variable");
		for(Iterator<Node>scopeNodeitr = nodes.iterator();scopeNodeitr.hasNext();) {
	        Node node = scopeNodeitr.next();
            Node sentence_node = node.getParent().getParent();
            String sentenceId = ((Element)sentence_node).attributeValue("id");
            if (!mapSentence2Variables.containsKey(sentenceId))
	           mapSentence2Variables.put(sentenceId, new ArrayList<String>());
            mapSentence2Variables.get(sentenceId).add(((Element)node).attributeValue("phraseId"));
	    }
        for(Entry<String, List<String>> entry: mapSentence2Variables.entrySet()){
            System.out.println(entry.getKey()+":");
            for(String i: entry.getValue()){
                System.out.println("\t"+i);
            }
        }

		try {
			doc = reader.read(annotatedPath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Node rootNode = (Node)doc.getRootElement();
        for(Entry<String, List<String>> entry: mapSentence2Variables.entrySet()){
            System.out.println("sentence id="+entry.getKey());
    		Node sentenceNode = rootNode.selectSingleNode("./sentence[@id='" + entry.getKey() + "']");
    		for(String phraseId: entry.getValue()) {
        		Node value_components = sentenceNode.selectSingleNode(".//phrase[@id='" + phraseId + "']");
                List<WordToken> variablePhraseTokens = new ArrayList<WordToken>();
                int wordCount = countPhrase(value_components, variablePhraseTokens);
                Node temp_node = value_components;
                while(temp_node.getParent().getName().equals("phrase"))
                    temp_node = temp_node.getParent();

                List<WordToken> PhraseTokens = new ArrayList<WordToken>();
                System.out.println(countPhrase(temp_node, PhraseTokens));
                int cnt = 0;
                String sentence = "";
                Boolean is_reached = false;
                for(WordToken phrase: PhraseTokens){
                    sentence += phrase.word;
                    if (phrase.id == variablePhraseTokens.get(0).id){
                        System.out.println(sentence);
                        System.out.println("position is ("+cnt+", "+(cnt+wordCount)+")");
                        System.out.print("phraseid:"+phraseId+"={"+sentence.substring(0, cnt)+"\"");
                        is_reached = true;
                    }
                    else if (!is_reached)
                        cnt += phrase.word.length();
                }
                System.out.println(sentence.substring(cnt, cnt+wordCount)+"\""+sentence.substring(cnt+wordCount, sentence.length())+"}");
    		}
    		System.out.println(((Element)sentenceNode).attributeValue("id"));
        }
	}
    private int countPhrase(Node phraseNode, List<WordToken>PhraseTokens){
        int counter = 0;

        List<Node> wordList = phraseNode.selectNodes(".//word");
        for(Node n: wordList){
            counter+=n.getStringValue().length();
            WordToken temp = new WordToken(n.getStringValue(), Integer.valueOf(((Element)(n.getParent())).attributeValue("id")));
            PhraseTokens.add(temp);
        }
        return counter;
    }
}
// Integer.valueOf(((Element)(wordList.get(i).getParent())).attributeValue("id"))
