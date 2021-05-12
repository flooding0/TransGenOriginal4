package sel.nlp.decisionrule;

import sel.nlp.decisionrule.*;

public class Match {
  private String expression;
  private String decision;

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();

    if (this.decision != null) {
      localStringBuffer.append("decision:");
      localStringBuffer.append("\n");
      localStringBuffer.append(decision);
    }

    if (this.expression != null) {
      localStringBuffer.append("expression:");
      localStringBuffer.append("\n");
      localStringBuffer.append(expression);
    }

    return localStringBuffer.toString();
  }

  public void setExpression(String param) {
    this.expression = param;
  }

  public String getExpression() {
    return this.expression;
  }

  public void setDecision(String param) {
    this.decision = param;
  }

  public String getDecision() {
    return this.decision;
  }

}