package sel.nlp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Read {
	static Frame frm = new Frame();
	static Frontpanel frontpanel = new Frontpanel();
	static ResultSpace resultspase = new ResultSpace();
	static Map<label2,Textfield1> txmap = new LinkedHashMap<>();
	static List<String> texts = new ArrayList<String>();
	static List<String> result = new ArrayList<String>();
	static List<resultlabel> resultlabellist = new ArrayList<resultlabel>();
	static Gridpanel gridpanel = new Gridpanel();
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
		gridpanel.set_size(data_size);
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
		txmap.put(variable_name,tb);
	}
	for (Entry<label2, Textfield1> entry : txmap.entrySet()) {
		panel4 p4 = new panel4();
		p4.add(entry.getKey());
		p4.add(entry.getValue());
		gridpanel.add(p4);
	}
	for (Textfield1 tx : txmap.values()) {
		texts.add("");
	}

		frontpanel.add(gridpanel);
		RefreshButton refresh_btn = new RefreshButton();
	    frontpanel.add(refresh_btn);
	    label2 l2 = new label2("");
	    resultspase.add(l2);
		frontpanel.add(resultspase);
	    frm.add(frontpanel);
	    frm.setVisible(true);
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
		FileWriter log_writer = new FileWriter(log,true);
		log_writer.write("Read Finish" + "\n");
		log_writer.close();
	} catch (IOException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
}

public void create_input_label(Entry<label2, Textfield1> entry) {
		panel4 p4 = new panel4();
		p4.add(entry.getKey());
		p4.add(entry.getValue());
		gridpanel.add(p4);
}

public void add_result(String text) {
	result.add(text);
//	result_label.setText(text);
//	frontpanel.add(result_label);
//	frm.setVisible(true);
}
public void show_result_below() {
	//resultspaceを更新する
	resultspase.removeAll();
	for(String result : result) {
		resultlabel result_label =  new resultlabel();
		result_label.setText(result);
		resultlabellist.add(result_label);
	}
	for(resultlabel resultlabel : resultlabellist) {
		resultspase.add(resultlabel);
	}
	frontpanel.add(resultspase);
	System.out.println(result);
}

public void show_result_with_txf() {

}

public void extraword() {
	//変更のある文を特定して、show_resultを呼び出してresultspaceを更新する
	int i=0;
	for (Entry<label2, Textfield1> entry : txmap.entrySet()) {
		if(!texts.get(i).equals(entry.getValue().getText())) {
			System.out.println(entry.getValue().getText());
		}
		i++;
	}
	texts.clear();
	for (Textfield1 tx : txmap.values()) {
		texts.add(tx.getText());
	}
	//この時点でtextsの中にtextfieldの新情報がぶち込まれる
	show_result_below();
	frm.setVisible(true);
	result.clear();
	resultlabellist.clear();
}

public void rewrite_input2() {
	//input2ファイルを新しく入力されたテキストフィールドの文章に書き直す

		try {
			FileWriter rewriter = new FileWriter(input_file);
			for (Entry<label2, Textfield1> entry : txmap.entrySet()) {
					rewriter.write(entry.getValue().getText()+"\n");
			}
			rewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

}
}
