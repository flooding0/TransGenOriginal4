package sel.nlp.parsemain;

import sel.nlp.parsemain.*;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
  private List<Token> tokens = new ArrayList<Token> ();
  private List<Phrase> phrases = new ArrayList<Phrase> ();
  private String id;
  private String decision;

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();

    if (this.id != null) {
      localStringBuffer.append("id:\n");
      localStringBuffer.append(this.id+"\n");
    }

    if (this.decision != null) {
      localStringBuffer.append("decision:\n");
      localStringBuffer.append(this.decision+"\n");
    }

    if (this.tokens != null) {
      for (Token token: this.tokens) {
        localStringBuffer.append(token.toString());
      }
    }

    if (this.phrases != null) {
      for (Phrase phrase: this.phrases) {
        localStringBuffer.append(phrase.toString());
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

  public void addToken(Token param) {
    this.tokens.add(param);
  }

  public List<Token> getTokens() {
    return this.tokens;
  }

  public void addPhrase(Phrase param) {
    this.phrases.add(param);
  }

  public void addNode(Node param) {
    this.phrases.add((Phrase) param);
  }

  public List<Phrase> getPhrases() {
    return this.phrases;
  }

  public void setDecision(String param) {
    this.decision = param;
  }

  public String getDecision() {
    return this.decision;
  }
}