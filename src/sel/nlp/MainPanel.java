package sel.nlp;

public class MainPanel extends XboxrayoutPanel{



	public MainPanel() {
	}

	public void MainPanel_empty() {
		MainPanel main = new MainPanel();

		 Label2 numl = new Label2("");
		 Label2 coml = new Label2("なにか");
		 Textfield1 tx = new Textfield1("");

		String num = "";
		num += Gridpanel_compornent.sentence_num_list.size()+1;
		numl.setText(num);
 		main.add(numl);
		main.add(coml);
		main.add(tx);
		Gridpanel_compornent.addData(numl, coml,tx);
		Pre_result.texts.add("");
		Frame.gridpanel.add(main);
		Window_data.frm.setVisible(true);
	}
}
