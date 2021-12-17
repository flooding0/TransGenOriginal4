package sel.nlp;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;



public class Read {

	static File analyzed_file = new File("input2.txt");
	static File xml_input = new File("input2.xml");
	static int fin_row = 0;
	static Document input_document;
	//入力ファイル設定

public static void main(String[] args) {
	SAXReader reader = new SAXReader();
	try {
		input_document = reader.read(xml_input);
		List<Node> sentenceNodes = input_document.selectNodes("//sentence");
		for(Node sentenceid : sentenceNodes) {
			Gridpanel_compornent.sentence_num_list.add(new sentencenumLabel(((Element)sentenceid).attribute("id").getValue()));
		}
		for(Node node : sentenceNodes) {
			List<Node> compnamenode = node.selectNodes("./compname");
			List<Node> requirementnode = node.selectNodes("./requirement");

			for(Node compname : compnamenode) {
				Gridpanel_compornent.compornent_name_list.add(new Label2(((Element)compname).getText()));
				//System.out.println(((Element)compname).getText());
			}
			for(Node requirement : requirementnode) {
				Gridpanel_compornent.txlist.add(new Textfield1(((Element)requirement).getText()));
				//System.out.println(((Element)requirement).getText());
			}
		}

	} catch (DocumentException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}
	try{
		int i = 0;
	for (Label2 label: Gridpanel_compornent.compornent_name_list) {
//コンポーネント名の数だけループ
		MainPanel p4 = new MainPanel();
	    p4.add(Gridpanel_compornent.sentence_num_list.get(i));
		p4.add(Gridpanel_compornent.compornent_name_list.get(i));
		p4.add(Gridpanel_compornent.txlist.get(i));
		//3つの要素をp4にぶち込む
		Frame.gridpanel.add(p4);
		//グリッドパネルにp4を突っ込む
		i++;
	}

	for (Label2 label : Gridpanel_compornent.compornent_name_list) {
		Pre_result.texts.add("");
	}

	    Window_data.frm.setVisible(true);

    }finally{
    }
}


public void show_result() {

	int i = 0;
	for (sentencenumLabel label: Gridpanel_compornent.sentence_num_list) {
		//コンポーネント名の数だけループ
				MainPanel p4 = new MainPanel();
			    p4.add(Gridpanel_compornent.sentence_num_list.get(i));
				p4.add(Gridpanel_compornent.compornent_name_list.get(i));
				p4.add(Gridpanel_compornent.txlist.get(i));
				System.out.println(Gridpanel_compornent.txlist.get(i).getText());
				//3つの要素をp4にぶち込む
				Frame.gridpanel.add(p4);

				//グリッドパネルにp4を突っ込む
				i++;
			}

	Frame.resultspace.removeAll();
}



public void extract_difference() {
	//変更のある文を特定して、show_resultを呼び出してresultspaceを更新する
	int i=0;
	for (Textfield1 tx :Gridpanel_compornent.txlist) {
		if(!Pre_result.texts.get(i).equals(tx.getText())) {
			System.out.println(tx.getText());
			//変更のあった文を出力
		}
		i++;
	}
	Pre_result.texts.clear();
	for (Textfield1 tx : Gridpanel_compornent.txlist) {
		Pre_result.texts.add(tx.getText());
	}
	//この時点でtextsの中にtextfieldの新情報がぶち込まれる

	show_result();
	Window_data.frm.setVisible(true);
}

public void rewrite_input2() {
	//input2ファイルを新しく入力されたテキストフィールドの文章に書き直す

    Xml_write.input_write();
    Text_write.text_rewrite(analyzed_file);
}
}
