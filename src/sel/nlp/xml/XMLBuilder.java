package sel.nlp.xml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * This is the program that produces an xml file
 * @author kxo
 */
public class XMLBuilder  {

  public static Element sentence(String id) {
    Element xml = DocumentHelper.createElement("sentence");
    xml.addAttribute("id", id);
    return xml;
  }

  public static Element sentences() {
    Element xml = DocumentHelper.createElement("sentences");
    return xml;
  }


  public static Element word(String text) {
    Element xml = DocumentHelper.createElement("word");
    xml.addText(text);
    return xml;
  }

  public static Element morpheme(String text) {
    Element xml = DocumentHelper.createElement("morpheme");
    xml.addText(text);
    return xml;
  }

  public static Element subInfo(String text) {
	    Element xml = DocumentHelper.createElement("subInfo");
	    xml.addText(text);
	    return xml;
	  }


  public static Element pos(String text) {
    Element xml = DocumentHelper.createElement("part-of-speech");
    xml.addText(text);
    return xml;
  }

  public static Element token(String id) {
    Element xml = DocumentHelper.createElement("token");
    xml.addAttribute("id", id);
    return xml;
  }

  public static Element phrase(String id, String type,String subinfo,
                              Element phrase1, Element phrase2) {
    Element xml = DocumentHelper.createElement("phrase");
    xml.addAttribute("id", id);
    xml.addAttribute("type", type);
    xml.addAttribute("subinfo", subinfo);
    xml.add(phrase1);
    xml.add(phrase2);
    return xml;
  }
  public static Element phrase2(String id, String type,
          Element phrase1, Element phrase2, Element subinfo) {
	  Element xml = DocumentHelper.createElement("phrase");
	  xml.addAttribute("id", id);
	  xml.addAttribute("type", type);
	  xml.add(phrase1);
	  xml.add(phrase2);
	  xml.add(subinfo);
	  return xml;
  }

  public static Element phrase(String id, String text) {
    Element xml = DocumentHelper.createElement("phrase");
    xml.addAttribute("id", id);
    xml.addText(text);
    return xml;
  }
}
