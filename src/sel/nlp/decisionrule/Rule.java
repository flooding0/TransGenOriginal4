package sel.nlp.decisionrule;

import java.util.ArrayList;
import java.util.List;

public class Rule {
  private String decision;
  private List<String> conditions;
  private List<String> condition2s;
  private List<Match> matches;
  private String common;
//toStringはあらゆる型の内容を普通のテキストデータにして表示させることができる．
//型に新たに設定した要素などの確認をする際に使用できる．
  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();
    localStringBuffer.append("-- Rule:");
    localStringBuffer.append("\n");

    if (this.decision != null) {
      localStringBuffer.append("decision:");
      localStringBuffer.append("\n");
      localStringBuffer.append(this.decision);
      localStringBuffer.append("\n");
    }
    if (this.common != null) {
      localStringBuffer.append("common:");
      localStringBuffer.append("\n");
      localStringBuffer.append(this.common);
      localStringBuffer.append("\n");
    }

    localStringBuffer.append("conditions:");
    localStringBuffer.append("\n");
    int i=0;
    if (this.conditions != null) {
      for (String condition: this.conditions) {
        localStringBuffer.append("condition"+i+":");
        localStringBuffer.append("\n");
        i++;
        localStringBuffer.append(condition);
        localStringBuffer.append("\n");
      }
    }

    localStringBuffer.append("condition2s:");
    localStringBuffer.append("\n");
    i=0;
    if (this.condition2s != null) {
      for (String condition2: this.condition2s) {
        localStringBuffer.append("condition2"+i+":");
        localStringBuffer.append("\n");
        i++;
        localStringBuffer.append(condition2);
        localStringBuffer.append("\n");
      }
    }

    localStringBuffer.append("matches:");
    localStringBuffer.append("\n");
    i=0;
    if (this.matches != null) {
      for (Match match: this.matches) {
        localStringBuffer.append("match"+(i+1)+":");
        localStringBuffer.append("\n");
        i++;
        localStringBuffer.append(match.toString());
        localStringBuffer.append("\n");
      }
    }
    return localStringBuffer.toString();
  }

  public void setDecision(String param) {
    this.decision = param;
  }

  public String getDecision() {
    return this.decision;
  }
//condition1
  public void addCondition(String param) {
    if (this.conditions == null)
      this.conditions = new ArrayList<String> ();

    this.conditions.add(param);
  }

  public List<String> getConditions() {
    return this.conditions;
  }
//condition2
  public void addCondition2(String param) {
	    if (this.condition2s == null)
	      this.condition2s = new ArrayList<String> ();

	    this.condition2s.add(param);
  }

  public List<String> getCondition2s() {
	    return this.condition2s;
  }
//match
  public void addMatch(Match param) {
    if (this.matches == null)
      this.matches = new ArrayList<Match> ();

    this.matches.add(param);
  }

  public List<Match> getMatches() {
    return this.matches;
  }
//common
  public void setCommon(String param) {
    this.common = param;
  }

  public String getCommon() {
    return this.common;
  }

}