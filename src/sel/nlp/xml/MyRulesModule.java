package sel.nlp.xml;
import sel.nlp.xml.*;

import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;

/**
 * This is the program that takes an xml file
 * and also takes a file called rules and creates the java object
 * complying to the class structure specified here.
 * @author kxo
 */
public class MyRulesModule extends FromXmlRulesModule  {

  String xmlFile;

  public void setFile(String inputF) {
    this.xmlFile = inputF;
  }

  @Override
  protected void loadRules()  {
    loadXMLRules(getClass().getResource(xmlFile));
//    loadXMLRules(getClass().getResource("parserulesxml.xml"));
  }
}