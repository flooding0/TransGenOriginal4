package sel.nlp;

import java.util.ArrayList;
import java.util.List;

public class Gridpanel_compornent {
	static List<sentencenumLabel> sentence_num_list = new ArrayList<>();
	static List<Label2> compornent_name_list = new ArrayList<Label2>();
	static List<Textfield1> txlist = new ArrayList<Textfield1>();

	static void addData (sentencenumLabel num, Label2 name, Textfield1 tx) {
		sentence_num_list.add(num);
		compornent_name_list.add(name);
		txlist.add(tx);
	}
}
