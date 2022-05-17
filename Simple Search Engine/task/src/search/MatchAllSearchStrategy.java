package search;

import java.util.*;

public class MatchAllSearchStrategy implements SearchStrategy {

    @Override
    public String[] searchQuery(List<String> peopleDirectory, Map<String, List<Integer>> reverseIndex) {
        Set<Integer> indexOfNames = new HashSet<>();
        List<String> results = new ArrayList<>();

        String[] currentSearch = new Scanner(System.in).nextLine().trim().toLowerCase().split(" ");

        String[] itemsInReverseIndex = reverseIndex.keySet().toArray(new String[0]);

        //create initial set from first item
        for (String key : itemsInReverseIndex) {
            if (key.toLowerCase().contains(currentSearch[0])) {
                indexOfNames.addAll(reverseIndex.get(key));
                break;
            }
        }

        //keep only the results from reverse index search where they intersect for each term
        //i.e.  item[1] = <1,2,3,4,5>
        //      item[2] = <2,5,7>
        //      result  = <2,5>
        for (String query : currentSearch) {
            for (String item : itemsInReverseIndex) {
                if (item.toLowerCase().contains(query)) {
                    indexOfNames.retainAll(reverseIndex.get(item));
                }
            }
        }

        //add all the filtered results to list
        for (Integer index : indexOfNames) {
            results.add(peopleDirectory.get(index));
        }

        return results.toArray(new String[0]);
    }
}
