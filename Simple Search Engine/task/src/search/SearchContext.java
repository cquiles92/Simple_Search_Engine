package search;

import java.util.List;
import java.util.Map;

public class SearchContext {
    SearchStrategy matchStrategy;

    SearchContext(String selection) {
        switch (selection) {
            case "ANY":
                matchStrategy = new MatchAnySearchStrategy();
                break;
            case "ALL":
                matchStrategy = new MatchAllSearchStrategy();
                break;
            case "NONE":
                matchStrategy = new MatchNoneSearchStrategy();
        }
    }

    public String[] search(List<String> peopleDirectory, Map<String, List<Integer>> reverseIndex) {
        System.out.println("Enter a name or email to search all suitable people.");
        return matchStrategy.searchQuery(peopleDirectory, reverseIndex);
    }
}
