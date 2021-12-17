package sel.nlp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Text_write {

	static void text_rewrite(File analyzed_file) {
		try {
			FileWriter rewriter = new FileWriter(analyzed_file);
			for (Textfield1 tx : Gridpanel_compornent.txlist) {
				rewriter.write(tx.getText()+"\n");
			}
			rewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
