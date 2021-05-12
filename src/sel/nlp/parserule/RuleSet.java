package sel.nlp.parserule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleSet{
  private List<Rule> rules = new ArrayList<Rule>();
  private Map<String, String> aliases = new HashMap<String,String>();


//private List<Alias> aliases = new ArrayList<Alias>();

  public String toString() {
    StringBuilder localStringBuffer = new StringBuilder();
    localStringBuffer.append("-- RuleSet:");

    if (this.rules != null) {
      localStringBuffer.append("-- Rules:");
      localStringBuffer.append("\n");
      for (Rule rule: this.rules) {
        localStringBuffer.append(rule.toString());
      }
    }

    if (this.aliases != null) {
      localStringBuffer.append("-- Aliase:");
      localStringBuffer.append("\n");
      for (String key: this.aliases.keySet()) {
        localStringBuffer.append(key);
        localStringBuffer.append("\n");
        localStringBuffer.append(aliases.get(key));
        localStringBuffer.append("\n");
      }
    }

    return localStringBuffer.toString();
  }

  public void addRule(Rule paramRule) {
    this.rules.add(paramRule);
  }
/*
  public void addAlias(Alias param) {
    this.aliases.add(param);
  }
*/
  public void setAliases(Map<String, String> param) {
//    this.aliases.add(param);
    this.aliases = param;
  }

  public List<Rule> getRules() {
    return this.rules;
  }

  public Map<String, String> getAliases() {
    return this.aliases;
  }
}