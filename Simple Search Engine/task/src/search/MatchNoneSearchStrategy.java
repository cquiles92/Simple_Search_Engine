package search;

import java.util.*;

public class MatchNoneSearchStrategy implements SearchStrategy {
    @Override
    public String[] searchQuery(List<String> peopleDirectory, Map<String, List<Integer>> reverseIndex) {
        Set<Integer> indexOfNames = new HashSet<>();
        List<String> results = new ArrayList<>(List.copyOf(peopleDirectory));

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

        //create list of names to remove
        List<String> namesToRemove = new ArrayList<>();
        for (Integer index : indexOfNames) {
            namesToRemove.add(peopleDirectory.get(index));
        }

        //remove names
        for (String name : namesToRemove) {
            results.remove(name);
        }

        return results.toArray(new String[0]);
    }
}
