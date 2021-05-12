package sel.nlp.parsemain;

import java.util.ArrayList;
import java.util.List;

public class Phrase extends Node {
// for later
  private List<Node> nodes;
//  private List<Phrase> phrases;
  private String id;
  private String text;
  private String type;
  private String subinfo;
//  private String type2;

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();

    if (this.id != null) {
      localStringBuffer.append("id:\n");
      localStringBuffer.append(this.id+"\n");
    }

    if (this.type != null) {
      localStringBuffer.append("type:\n");
      localStringBuffer.append(this.type+"\n");
    }

//    if (this.type2 != null) {
//        localStringBuffer.append("type2:\n");
//        localStringBuffer.append(this.type2+"\n");
//      }

    if (this.text != null) {
      localStringBuffer.append("cont:\n");
      localStringBuffer.append(this.text+"\n");
    }
/*
    if (this.phrases != null) {
      for (Phrase phrase: this.phrases) {
        localStringBuffer.append(phrase.toString());
      }
    }
*/
    if (this.nodes != null) {
      for (Node node: this.nodes) {
        localStringBuffer.append(node.toString());
      }
    }


    return localStringBuffer.toString();
  }

  public void setId(String param) {
    this.id = param;
  }

  public String getId() {
    return this.id;
  }

  public void setType(String param) {
    this.type = param;
  }

  public String getType() {
    return this.type;
  }

//  public void setType2(String param) {
//	    this.type2 = param;
//	  }
//
//	  public String getType2() {
//	    return this.type2;
//	  }

/*
  public void addPhrase(Phrase param) {
    if (this.phrases == null)
      this.phrases = new ArrayList<Phrase>();
    this.phrases.add(param);
  }

  public List<Phrase> getPhrases() {
    return this.phrases;
  }
*/
  public void setValue(String val) {
    this.text = val;
  }

  public String getValue() {
    return this.text;
  }

  public void addNode(Node param) {
    if (this.nodes == null)
      this.nodes = new ArrayList<Node>();
    this.nodes.add(param);
  }

  public List<Node> getNodes() {
    return this.nodes;
  }

public String getSubinfo() {
	return subinfo;
}

public void setSubinfo(String subinfo) {
	this.subinfo = subinfo;
}
}