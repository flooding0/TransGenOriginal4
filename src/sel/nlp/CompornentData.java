package sel.nlp;

import java.util.ArrayList;
import java.util.List;

public class CompornentData {
	static List<DeleteSwitch> DB_list = new ArrayList<DeleteSwitch>();
	static List<sentencenumLabel> sentence_num_list = new ArrayList<>();
	static List<Label2> compornent_name_list = new ArrayList<Label2>();
	static List<Textfield1> txlist = new ArrayList<Textfield1>();
	static List<String> compstring = new ArrayList<String>();

	static void addData (DeleteSwitch db ,sentencenumLabel num, Label2 name, Textfield1 tx) {
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
