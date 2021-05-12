package sel.nlp.decisionrule;

import sel.nlp.decisionrule.*;

import java.util.ArrayList;
import java.util.List;

public class Rules {
  private List<Rule> rules = new ArrayList<Rule>();
  private String target;

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();
    localStringBuffer.append("-- RuleSet:");

    if (this.target != null) {
      localStringBuffer.append("target\n");
      localStringBuffer.append(this.target.toString());
    }

    if (this.rules != null) {
      localStringBuffer.append("\n");
      for (Rule rule: this.rules) {
        localStringBuffer.append(rule.toString());
      }
    }

    return localStringBuffer.toString();
  }

  public void addRule(Rule param) {
    this.rules.add(param);
  }

  public List<Rule> getRules() {
    return this.rules;
  }

  public void setTarget(String param) {
    this.target = param;
  }

  public String getTarget() {
    return this.target;
  }

}