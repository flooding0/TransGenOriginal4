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
	static Map<label2,Textfield1> txmap = new LinkedHashMap<>();
	static List<String> texts = new ArrayList<String>();
	static resultlabel result_label =  new resultlabel();
	static List<String> result = new ArrayList<String>();

public static void main(String[] args) {
    String excelread = "read.xlsx";
	XSSFWorkbook workbook  = null;
	try{
		File input_file = new File("input2.txt");
		int data_size = 0 ;
		workbook = new XSSFWorkbook(excelread);
		Sheet sheet = workbook.getSheetAt(0);
		String variable = null ;
		FileWriter writer = new FileWriter(input_file);
	for (int i=2 ; sheet.getRow(i).getCell(1).getStringCellValue()!="" ;i++) {
			data_size ++;
	}
		Gridpanel gridpanel = new Gridpanel(data_size + 1);
		//Textfield1[] text = new Textfield1[data_size + 1];
	for (int i=2 ; sheet.getRow(i).getCell(1).getStringCellValue()!="" ;i++) {
		Row row0 = sheet.getRow(i);
		Cell cell0 = row0.getCell(0);
		if(cell0.getStringCellValue()!="") {
			variable = cell0.getStringCellValue();
		}
		Cell cell1 = row0.getCell(1);
//		System.out.println(variable);
//		System.out.println(cell1.getStringCellValue());
//		label2 label = new label2(variable+" : "+cell1.getStringCellValue());
//		p1.add(label);
		Textfield1 tb = new Textfield1(cell1.getStringCellValue());
		writer.write(cell1.getStringCellValue() + "\n");
		label2 variable_name = new label2(variable+" ");
		txmap.put(variable_name,tb);
//		p4.add(variable_name);
//		p4.add(tb);
//		gridpanel.add(p4);
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

public void add_result(String text) {
	result.add(text);
//	result_label.setText(text);
//	frontpanel.add(result_label);
//	frm.setVisible(true);
}
public void show_result() {
	for(String result : result) {
		result_label.setText(result);
	}
	frontpanel.add(result_label);
	System.out.println(result);
}

public void print() {
	System.out.println("set result");
}

public void extraword() {
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
	show_result();
	frm.setVisible(true);
	result.clear();
}
}
