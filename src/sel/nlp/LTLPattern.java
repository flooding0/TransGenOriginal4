package sel.nlp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LTLPattern {
	String patternName;
//	Map<String, LinkedHashMap<Boolean, String>> scopeTemplates = new HashMap<String, LinkedHashMap<Boolean, String>>();
	Map<String, ArrayList<Pair<Boolean, String>>> scopeTemplates = new HashMap<String, ArrayList<Pair<Boolean, String>>>(); // templates of each scope [scope_name][template_list]
	
	ArrayList<Pair<Boolean, String>> patternTemplates = new ArrayList<Pair<Boolean, String>>(); // templates of pattern
	String patternSemantic;
	Map<String, String> scopeSemantics = new HashMap<>(); // semantics of each scope
	Map<String, String> LTLs = new HashMap<>(); // LTL formula of each scope of the pattern
	
	List<String> scopeList = new ArrayList<String>(
		Arrays.asList("globally", "before_R", "after_Q", "between_Q_and_R", "after_Q_until_R")
	);
	
	
	public LTLPattern(String patternName) {
		this.patternName = patternName;
		for(String scope:scopeList) {
			scopeTemplates.put(scope, new ArrayList<Pair<Boolean, String>>());
		}
	}
	public ArrayList<Pair<Boolean, String>> getTemplateListOfScope(String selectedScope){
		return Stream.concat(this.scopeTemplates.get(selectedScope).stream(), this.patternTemplates.stream())
                .collect(Collectors.toCollection(ArrayList::new));
	}
}