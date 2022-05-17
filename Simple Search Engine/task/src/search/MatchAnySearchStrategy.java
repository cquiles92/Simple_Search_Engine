package search;


import java.util.*;

public class MatchAnySearchStrategy implements SearchStrategy {


    @Override
    public String[] searchQuery(List<String> peopleDirectory, Map<String, List<Integer>> reverseIndex) {
        Set<Integer> indexOfNames = new HashSet<>();
        List<String> results = new ArrayList<>();

        String[] currentSearch = new Scanner(System.in).nextLine().trim().toLowerCase().split(" ");


        //get any matching item in keySet and add it to indexList
        String[] itemsInReverseIndex = reverseIndex.keySet().toArray(new String[0]);
        for (String query : currentSearch) {
            for (String item : itemsInReverseIndex) {
                if (item.toLowerCase().contains(query)) {
                    indexOfNames.addAll(reverseIndex.get(item));
                }
            }
        }

        //create return array
        for (Integer integer : indexOfNames) {
            results.add(peopleDirectory.get(integer));
        }

        return results.toArray(new String[0]);
    }
}
