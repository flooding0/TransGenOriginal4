package sel.nlp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Read {

	static File input_file = new File("input2.txt");

public static void main(String[] args) {
    String excelread = "read.xlsx";
	XSSFWorkbook workbook  = null;
	try{
		int data_size = 0 ;
		workbook = new XSSFWorkbook(excelread);
		Sheet sheet = workbook.getSheetAt(0);
		String variable = null ;
		FileWriter writer = new FileWriter(input_file);
	for (int i=2 ; sheet.getRow(i).getCell(1).getStringCellValue()!="" ;i++) {
			data_size ++;
	}
		window_data.gridpanel.set_size(data_size);
	for (int i=2 ; sheet.getRow(i).getCell(1).getStringCellValue()!="" ;i++) {
		Row row0 = sheet.getRow(i);
		Cell cell0 = row0.getCell(0);
		if(cell0.getStringCellValue()!="") {
			variable = cell0.getStringCellValue();
		}
		Cell cell1 = row0.getCell(1);
		writer.write(cell1.getStringCellValue() + "\n");
		//input_fileにexcelのデータをぶち込む
		Textfield1 tb = new Textfield1(cell1.getStringCellValue());
		label2 variable_name = new label2(variable+" ");
		window_data.txmap.put(variable_name,tb);
	}
	for (Entry<label2, Textfield1> entry : window_data.txmap.entrySet()) {
		panel4 p4 = new panel4();
		p4.add(entry.getKey());
		p4.add(entry.getValue());
		window_data.gridpanel.add(p4);
	}
	for (Textfield1 tx : window_data.txmap.values()) {
		result_data.texts.add("");
	}
		window_data.frontpanel.add(window_data.gridpanel);
		RefreshButton refresh_btn = new RefreshButton();
	    window_data.frontpanel.add(refresh_btn);
	    label2 l2 = new label2("");
	    window_data.resultspase.add(l2);
		window_data.frontpanel.add(window_data.resultspase);
	    window_data.frm.add(window_data.frontpanel);
	    window_data.frm.setVisible(true);
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

	File log= new File("log.txt");
    try {
		FileWriter log_writer = new FileWriter(log);
		log_writer.write("Read Finish" + "\n");
		log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
}



public void add_result(String text) {
	result_data.result.add(text);
//	result_label.setText(text);
//	frontpanel.add(result_label);
//	frm.setVisible(true);
}
public void show_result_below() {
	//resultspaceを更新する
	window_data.resultspase.removeAll();
	for(String result : result_data.result) {
		resultlabel result_label =  new resultlabel();
		result_label.setText(result);
		window_data.resultlabellist.add(result_label);
	}
	for(resultlabel resultlabel : window_data.resultlabellist) {
		window_data.resultspase.add(resultlabel);
	}
	window_data.frontpanel.add(window_data.resultspase);
	System.out.println(result_data.result);
}

public void show_result2() {

}

public void extract_difference() {
	//変更のある文を特定して、show_resultを呼び出してresultspaceを更新する
	int i=0;
	for (Entry<label2, Textfield1> entry : window_data.txmap.entrySet()) {
		if(!result_data.texts.get(i).equals(entry.getValue().getText())) {
			System.out.println(entry.getValue().getText());
		}
		i++;
	}
	result_data.texts.clear();
	for (Textfield1 tx : window_data.txmap.values()) {
		result_data.texts.add(tx.getText());
	}
	//この時点でtextsの中にtextfieldの新情報がぶち込まれる
	show_result_below();
	window_data.frm.setVisible(true);
	result_data.result.clear();
	window_data.resultlabellist.clear();
}

public void rewrite_input2() {
	//input2ファイルを新しく入力されたテキストフィールドの文章に書き直す

		try {
			FileWriter rewriter = new FileWriter(input_file);
			for (Entry<label2, Textfield1> entry : window_data.txmap.entrySet()) {
					rewriter.write(entry.getValue().getText()+"\n");
			}
			rewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

}
}
