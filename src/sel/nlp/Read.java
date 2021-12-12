package sel.nlp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Read {

	static File input_file = new File("input2.txt");
	static int fin_row = 0;
	//入力ファイル設定

public static void main(String[] args) {
    String excelread = "read3.xlsx";
	XSSFWorkbook workbook  = null;
	try{
		workbook = new XSSFWorkbook(excelread);
		Sheet sheet = workbook.getSheetAt(0);
		String variable = null ;
		FileWriter writer = new FileWriter(input_file);
		int sentence_number = 0;
	for (int i=2 ; sheet.getRow(i).getCell(1).getStringCellValue()!="" ;i++) {
		String num = "";
		//文番号取得
		sentence_number++;
		num+=sentence_number;
		Label2 numlabel = new Label2(num);
		//要素名取得
		Row row0 = sheet.getRow(i);
		Cell cell0 = row0.getCell(0);
		if(cell0.getStringCellValue()!="") {
			variable = cell0.getStringCellValue();
		}else {

		}
		//input_fileにexcelのデータをぶち込む
		Cell cell1 = row0.getCell(1);
		writer.write(cell1.getStringCellValue() + "\n");
		//文の記述をexcelからtextfield上に取得
		Textfield1 tb = new Textfield1(cell1.getStringCellValue());
		Label2 variable_name = new Label2(variable+" ");
		Gridpanel_compornent.sentence_num_list.add(numlabel);
		Gridpanel_compornent.compornent_name_list.add(variable_name);
		Gridpanel_compornent.txlist.add(tb);
		fin_row = i+1;
	}

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
	    writer.close();
    }catch(IOException e){
      System.out.println(e.toString());
    }finally{
      try {
        if (workbook != null) {
            	workbook.close();
          }
      }catch(IOException e){
        System.out.println(e.toString());
      }
    }
    System.out.println(fin_row);
    //Excel_write.excel_write();
}


public void show_result() {

	int i = 0;
	for (Label2 label: Gridpanel_compornent.sentence_num_list) {
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

		try {
			FileWriter rewriter = new FileWriter(input_file);
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
