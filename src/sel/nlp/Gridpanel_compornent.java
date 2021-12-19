package sel.nlp;

import java.util.ArrayList;
import java.util.List;

public class Gridpanel_compornent {
	static List<DeleteButton> DB_list = new ArrayList<DeleteButton>();
	static List<sentencenumLabel> sentence_num_list = new ArrayList<>();
	static List<Label2> compornent_name_list = new ArrayList<Label2>();
	static List<Textfield1> txlist = new ArrayList<Textfield1>();

	static void addData (DeleteButton db ,sentencenumLabel num, Label2 name, Textfield1 tx) {
		DB_list.add(db);
		sentence_num_list.add(num);
		compornent_name_list.add(name);
		txlist.add(tx);
	}

	static void deleteData () {
		DB_list.clear();
		sentence_num_list.clear();
		compornent_name_list.clear();
		txlist.clear();
		xml_to_list.perform();
		MainPanel.CreateMainPanel();
	}
}
