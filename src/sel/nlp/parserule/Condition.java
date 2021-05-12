package sel.nlp.parserule;

public class Condition {
  private String condition;
  private String text = null;
  private String morpheme = null;
  private String seq;
  private String subinfo;


  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();

    if (this.condition != null) {
      localStringBuffer.append(this.condition);
      localStringBuffer.append("\n");
    }
    if (this.text != null) {
      localStringBuffer.append("text:");
      localStringBuffer.append(this.text);
      localStringBuffer.append("\n");
    }
    if (this.morpheme != null) {
      localStringBuffer.append("morpheme:");
      localStringBuffer.append(this.morpheme);
      localStringBuffer.append("\n");
    }
    if (this.seq != null) {
      localStringBuffer.append("sequence:");
      localStringBuffer.append(this.seq);
      localStringBuffer.append("\n");
    }
    return localStringBuffer.toString();
  }

  public void setText(String param) {
    this.text = param;
  }

  public String getText() {
    return this.text;
  }

  public void setSeq(String param) {
    this.seq = param;
  }

  public String getSeq() {
    return this.seq;
  }

  public void setValue(String param) {
    this.condition = param;
  }

  public String getValue() {
    return this.condition;
  }

  public void setMorpheme(String param) {
    this.morpheme = param;
  }

  public String getMorpheme() {
    return this.morpheme;
  }

public String getSubinfo() {
	return subinfo;
}

public void setSubinfo(String subinfo) {
	this.subinfo = subinfo;
}

}