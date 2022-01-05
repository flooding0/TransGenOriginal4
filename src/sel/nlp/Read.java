package sel.nlp;

import java.io.File;

import org.dom4j.Document;



public class Read {

	static File analyzed_file = new File("input2.txt");
	static File xml_input = new File("input2.xml");
	static Document input_document;
	//入力ファイル設定

public static void main(String[] args) {
		xml_to_list.perform();
		MainPanel.CreateMainPanel();

		for (Label2 label : CompornentData.compornent_name_list) {
			Pre_result.texts.add("");
		}

	    Window_data.frm.setVisible(true);

}


public void show_result() {
	MainPanel.CreateMainPanel();
	Frame.resultspace.removeAll();
}

public void extract_difference() {
	//変更のある文を特定して、show_resultを呼び出してresultspaceを更新する
	int i=0;
	for (Textfield1 tx :CompornentData.txlist) {
		if(!Pre_result.texts.get(i).equals(tx.getText())) {
			System.out.println(tx.getText());
			//変更のあった文を出力
		}
		i++;
	}
	Pre_result.texts.clear();
	for (Textfield1 tx : CompornentData.txlist) {
		Pre_result.texts.add(tx.getText());
	}
	//この時点でtextsの中にtextfieldの新情報がぶち込まれる
}

public void rewrite_input2() {
	//input2ファイルを新しく入力されたテキストフィールドの文章に書き直す

    Xml_write.input_write();
    Text_write.text_rewrite(analyzed_file);
}
}
