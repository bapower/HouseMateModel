/**
 * 
 */
package cscie97.asn3.knowledge.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Bry Power
 *
 */
public class KnowledgeGraph {
	
	// association for maintaining the active set of Predicates 
	private Map<String, Triple> tripleMap = new HashMap<String, Triple>();
		
	// association for maintaining a fast query lookup map
	private Map<String, Set<Triple>> queryMapSet = new HashMap<String, Set<Triple>>();
	
	// singleton instance of this class
	private static KnowledgeGraph instance = null;
	
	
	/**
	 * This method returns a reference to the single static 
	 * instance of the KnowledgeGraph
	 * @return knowledgeGraph instance
	 */
	protected KnowledgeGraph() {
	      // instantiation is protected
	}
	
	/**
	 *  instantiate or get the singleton instance of this class or 
	 * @return KnowledgeGraph instance
	 */
	public static KnowledgeGraph getInstance() {
	      if(instance == null) {
	         instance = new KnowledgeGraph();
	      }
	      return instance;
    }
			

	/**
	 * Public method for adding a Triple to the KnowledgeGraph.
	 * @param subjectIdentifier unique string identifier for the subject
	 * @param predicateIdentifier unique string identifier for the predicate
	 * @param objectIdentifier unique string identifier for the object
	 */
	public void importTriple(String subjectIdentifier, String predicateIdentifier, String objectIdentifier) {
        String tripleIdentifier = String.format("%s %s %s", subjectIdentifier, predicateIdentifier, objectIdentifier);
        Triple triple = new Triple(tripleIdentifier); 
		tripleMap.put(triple.getIdentifier().toLowerCase().replace(".", ""), triple);
		
		addPermutationToQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" "+objectIdentifier, triple);
		addPermutationToQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" ?", triple);
		addPermutationToQueryMapSet(subjectIdentifier+" ? "+objectIdentifier, triple);
		addPermutationToQueryMapSet(subjectIdentifier+" ? ?", triple);
		addPermutationToQueryMapSet("? "+predicateIdentifier+" "+objectIdentifier, triple);
		addPermutationToQueryMapSet("? "+predicateIdentifier+" ?", triple);
		addPermutationToQueryMapSet("? ? "+objectIdentifier, triple);
		addPermutationToQueryMapSet("? ? ?", triple);
    }
	
	/**
	 * 
	 * @param permutation
	 * @param triple
	 */
	private void addPermutationToQueryMapSet (String permutation, Triple triple) {
		if(!queryMapSet.containsKey(permutation.toLowerCase().replace(".", ""))) {
			Set<Triple> tripleSet = new HashSet<Triple>();
			tripleSet.add(triple);
			queryMapSet.put(permutation.toLowerCase().replace(".", ""), tripleSet);
		} else {
			Set<Triple> tripleSet = queryMapSet.get(permutation.toLowerCase().replace(".", ""));
			if(!tripleSet.contains(triple)){
				tripleSet.add(triple);
			}
			queryMapSet.put(permutation.toLowerCase().replace(".", ""), tripleSet);	
		}
	}
	
	/**
	 * Public method for removing a Triple to the KnowledgeGraph.
	 * @param subjectIdentifier unique string identifier for the subject
	 * @param predicateIdentifier unique string identifier for the predicate
	 * @param objectIdentifier unique string identifier for the object
	 */
	public void removeTriple(String subjectIdentifier, String predicateIdentifier, String objectIdentifier) {
        String tripleIdentifier = String.format("%s %s %s", subjectIdentifier, predicateIdentifier, objectIdentifier);
        Triple triple = tripleMap.get(tripleIdentifier);
        tripleMap.remove(tripleIdentifier.toLowerCase()); 
        
		removePermutationFromQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" "+objectIdentifier, triple);
		removePermutationFromQueryMapSet(subjectIdentifier+" "+predicateIdentifier+" ?", triple);
		removePermutationFromQueryMapSet(subjectIdentifier+" ? "+objectIdentifier, triple);
		removePermutationFromQueryMapSet(subjectIdentifier+" ? ?", triple);
		removePermutationFromQueryMapSet("? "+predicateIdentifier+" "+objectIdentifier, triple);
		removePermutationFromQueryMapSet("? "+predicateIdentifier+" ?", triple);
		removePermutationFromQueryMapSet("? ? "+objectIdentifier, triple);
		removePermutationFromQueryMapSet("? ? ?", triple);
    }
	
	/**
	 * 
	 * @param permutation
	 * @param triple
	 * 
	 */
	private void removePermutationFromQueryMapSet (String permutation, Triple triple) {
		queryMapSet.get(permutation).remove(triple);
	}
	
	/**
	 * 
	 * @param subject unique string identifier for the subject
	 * @param predicate unique string identifier for the predicate
	 * @param object unique string identifier for the object
	 * @return Set a set of matching triples or an empty set if none found
	 */
	public Set<Triple> executeQuery(String subject, String predicate, String object) {
		String key = subject.toLowerCase()+" "+predicate.toLowerCase()+" "+object.toLowerCase().replace(".", "");
		Set<Triple> tripleSet = queryMapSet.get(key);
        return ((tripleSet == null) ? new HashSet<Triple>() : tripleSet);
    }
	
	

	/**
	 * 
	 * @param identifier
	 * @return Triple
	 */
	Triple getTriple (String subject, String predicate, String object){
		String identifier = subject + " " + predicate + " " + object;
		Triple triple = tripleMap.get(identifier.toLowerCase());
		return ((triple == null) ? new Triple(identifier) : triple);
	}
}
