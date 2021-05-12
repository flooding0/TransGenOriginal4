package sel.nlp.parsemain;

import sel.nlp.parsemain.*;

public class Token extends Node {
  private String pos; // part of speech
  private String morpheme;
  private String word;
  private String id;

  private String kind;

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();

    if (this.id != null) {
      localStringBuffer.append("id:\n");
      localStringBuffer.append(this.id);
    }

    if (this.pos != null) {
      localStringBuffer.append("part of speech:\n");
      localStringBuffer.append(this.pos);
    }

    if (this.morpheme != null) {
      localStringBuffer.append("morpheme:\n");
      localStringBuffer.append(this.morpheme);
    }

    if (this.word != null) {
      localStringBuffer.append("word:\n");
      localStringBuffer.append(this.word);
    }

    return localStringBuffer.toString();
  }

  public void setId(String param) {
    this.id = param;
  }

  public String getId() {
    return this.id;
  }

  public void setWord(String param) {
    this.word = param;
  }

  public String getWord() {
    return this.word;
  }

  public void setMorpheme(String param) {
    this.morpheme = param;
  }

  public String getMorpheme() {
    return this.morpheme;
  }

  public void setPos(String param) {
    this.pos = param;
  }

  public String getPos() {
    return this.pos;
  }


// for variable
  public String getKind(){
    return this.kind;
  }

  public void setKind(String param){
    this.kind = param;
  }

}