import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;
    private Set<String> locations;

    public Graph() {
        adjacencyList = new HashMap<>();
        locations = new HashSet<>();
    }

    public void addLocation(String loc) {
        if (!locations.contains(loc)) {
            locations.add(loc);
            adjacencyList.put(loc, new ArrayList<>());
        }
    }
}