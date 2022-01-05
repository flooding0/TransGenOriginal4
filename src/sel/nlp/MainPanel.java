package sel.nlp;

public class MainPanel extends XboxrayoutPanel{




	public MainPanel() {
	}

	public void MainPanel_empty(String compornentName) {
		MainPanel main = new MainPanel();

		 sentencenumLabel numl = new sentencenumLabel("");
		 Label2 coml = new Label2(compornentName);
		 Textfield1 tx = new Textfield1("");
		 DeleteSwitch db = new DeleteSwitch();

		String num = "";
		num += CompornentData.sentence_num_list.size()+1;
		numl.setText(num);
		main.add(db);
 		main.add(numl);
		main.add(coml);
		main.add(tx);
		CompornentData.addData(db,numl, coml,tx);
		Pre_result.texts.add("");
		Frame.gridpanel.add(main);
		Window_data.frm.setVisible(true);
	}
	static void CreateMainPanel() {
		int i = 0;
		for (sentencenumLabel num: CompornentData.sentence_num_list) {
			num.setText(Integer.valueOf(i+1).toString());
	//コンポーネント名の数だけループ
			MainPanel p4 = new MainPanel();
			p4.add(CompornentData.DB_list.get(i));
		    p4.add(CompornentData.sentence_num_list.get(i));
			p4.add(CompornentData.compornent_name_list.get(i));
			p4.add(CompornentData.txlist.get(i));
			//3つの要素をp4にぶち込む
			Frame.gridpanel.add(p4);
			//グリッドパネルにp4を突っ込む
			i++;
		}

	}
}
