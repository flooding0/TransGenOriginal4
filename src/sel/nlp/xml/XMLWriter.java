package sel.nlp.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;

/**
 * This is the program that produces an xml file
 * @author kxo
 */
public class XMLWriter {
  private File output;
  private OutputStream os;

  public XMLWriter(File file) {
    this.output = file;
  }

  public XMLWriter(OutputStream param) {
    this.os = param;
  }


  public void write(Document document) {
    write(document, false);
  }

  public void write(Document document, boolean line) {
//lineの変数で改行するか否か選べる
/*
    try {
      output.delete();
      output.createNewFile();
    } catch (IOException e) {
       e.printStackTrace();
    }
*/
    FileOutputStream fos = null;
    OutputFormat format = new OutputFormat(" ", line, "UTF-8");
    org.dom4j.io.XMLWriter writer = null;

    try {
      if (output != null) {
        fos = new FileOutputStream(output);
        writer = new org.dom4j.io.XMLWriter(fos, format);
      } else {
//        fos = new FileOutputStream(output);
        writer = new org.dom4j.io.XMLWriter(os, format);
      }
      writer.write(document);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
       e.printStackTrace();
      }
    }
  }
}
