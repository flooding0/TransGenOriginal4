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
	//入力ファイル設定

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
		Window_data.gridpanel.set_size(data_size);

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
		Label2 variable_name = new Label2(variable+" ");
		Window_data.txmap.put(variable_name,tb);

//		System.out.println("***************"+ i + "***************");
//		System.out.println(cell1.getStringCellValue());
	}
	int sentence_number = 0;
	for (Entry<Label2, Textfield1> entry : Window_data.txmap.entrySet()) {
		String num = "";
		sentence_number++;
		num += sentence_number;
		XboxrayoutPanel p4 = new XboxrayoutPanel();
	    Label2 numlabel = new Label2(num);
	    p4.add(numlabel);
		p4.add(entry.getKey());
		p4.add(entry.getValue());
		Window_data.gridpanel.add(p4);
	}

	for (Textfield1 tx : Window_data.txmap.values()) {
		Result_data.texts.add("");
	}
		Window_data.frontpanel.add(Window_data.gridpanel);
		XboxrayoutPanel buttons = new XboxrayoutPanel();
		RefreshButton refresh_btn = new RefreshButton();
		SaveButton save_btn = new SaveButton();
	    buttons.add(refresh_btn);
	    buttons.add(save_btn);
	    Window_data.frontpanel.add(buttons);
	    Label2 l2 = new Label2("");
	    Window_data.resultspase.add(l2);
		Window_data.frontpanel.add(Window_data.resultspase);
	    Window_data.frm.add(Window_data.frontpanel);
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


public void show_result_below() {
//	for(Entry<String, LinkedHashMap<Label2, Label2>> entry : Resultdataset.resultmap.entrySet()) {
//		System.out.println(entry.getKey());
//		for(Entry<Label2,Label2> entry2 : entry.getValue().entrySet()) {
//			System.out.println(entry2.getKey().getText());
//			System.out.print(":"+entry2.getValue().getText());
//		}
//	}
	Window_data.resultspase.removeAll();
//	for(String text : Result_data.result) {
//		//ここのリストを変更すればGUI上のresultspaceの出力を変更できる
//		Label2 label = new Label2(text);
//		Window_data.resultspase.add(label);
//	}
	for(Entry<Label2,Label2> entry : Result_data.results.entrySet()) {
		XboxrayoutPanel panel = new XboxrayoutPanel();
		panel.add(entry.getKey());
		panel.add(entry.getValue());
	Window_data.resultspase.add(panel);
	}
	Window_data.frontpanel.add(Window_data.resultspase);
}



public void extract_difference() {
	//変更のある文を特定して、show_resultを呼び出してresultspaceを更新する
	int i=0;
	for (Entry<Label2, Textfield1> entry : Window_data.txmap.entrySet()) {
		if(!Result_data.texts.get(i).equals(entry.getValue().getText())) {
			System.out.println(entry.getValue().getText());
			//変更のあった文を出力
		}
		i++;
	}
	Result_data.texts.clear();
	for (Textfield1 tx : Window_data.txmap.values()) {
		Result_data.texts.add(tx.getText());
	}
	//System.out.println(Result_data.texts);
	//この時点でtextsの中にtextfieldの新情報がぶち込まれる

	show_result_below();
	Window_data.frm.setVisible(true);
	Result_data.result.clear();
	Result_data.results.clear();
	Window_data.resultlabellist.clear();
}

public void rewrite_input2() {
	//input2ファイルを新しく入力されたテキストフィールドの文章に書き直す

		try {
			FileWriter rewriter = new FileWriter(input_file);
			for (Entry<Label2, Textfield1> entry : Window_data.txmap.entrySet()) {
					rewriter.write(entry.getValue().getText()+"\n");
			}
			rewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

}
}
