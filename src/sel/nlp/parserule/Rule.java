package sel.nlp.parserule;

import java.util.ArrayList;
import java.util.List;

public class Rule {
  private String goal;
  private String subinfo;
  private List<Condition> conditions = new ArrayList<Condition>();

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();
    localStringBuffer.append("-- Rule:");
    localStringBuffer.append("\n");

    if (this.goal != null) {
      localStringBuffer.append("goal:");
      localStringBuffer.append("\n");
      localStringBuffer.append(goal);
      localStringBuffer.append("\n");
    }
    localStringBuffer.append("subInfos:");
    localStringBuffer.append("\n");
    int i=0;
    if (this.subinfo != null) {
        localStringBuffer.append("subInfo:");
        localStringBuffer.append("\n");
        localStringBuffer.append(subinfo);
        localStringBuffer.append("\n");
      }
      localStringBuffer.append("conditions:");
      localStringBuffer.append("\n");
      i=0;
    if (this.conditions != null) {
      for (Condition condition: this.conditions) {
        localStringBuffer.append("condition"+i+":");
        localStringBuffer.append("\n");
        i++;
        localStringBuffer.append(condition.toString());
      }
    }
    return localStringBuffer.toString();
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }
  public void setSubinfo(String subInfo) {
	    this.subinfo = subInfo;
	  }

  public void addCondition(Condition paramCondition) {
    this.conditions.add(paramCondition);
  }

  public List<Condition> getConditions() {
    return this.conditions;
  }

  public String getGoal() {
    return this.goal;
  }

  public String getSubinfo() {
	    return this.subinfo;
	  }

}