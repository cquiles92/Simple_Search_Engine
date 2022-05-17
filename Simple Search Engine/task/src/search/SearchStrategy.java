package search;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    String[] searchQuery(List<String> peopleDirectory, Map<String, List<Integer>> reverseIndex);
}
