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

    public void addRoad(String loc1, String loc2) {
        if (locations.contains(loc1) && locations.contains(loc2)) {
            adjacencyList.get(loc1).add(loc2);
            adjacencyList.get(loc2).add(loc1); // Undirected graph
        }
    }

    public void removeLocation(String loc) {
        if (locations.contains(loc)) {
            for (String neighbor : new ArrayList<>(adjacencyList.get(loc))) {
                adjacencyList.get(neighbor).remove(loc);
            }
            adjacencyList.remove(loc);
            locations.remove(loc);
        }
    }

    public void removeRoad(String loc1, String loc2) {
        if (locations.contains(loc1) && locations.contains(loc2)) {
            adjacencyList.get(loc1).remove(loc2);
            adjacencyList.get(loc2).remove(loc1);
        }
    }

    public void displayConnections() {
        for (String loc : locations) {
            System.out.print(loc + " connected to: ");
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(loc);
            visited.add(loc);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                for (String neighbor : adjacencyList.get(current)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                        System.out.print(neighbor + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public Set<String> getLocations() {
        return locations;
    }

    // Temporary test main - remove before final submission
    public static void main(String[] args) {
        Graph g = new Graph();
        g.addLocation("A");
        g.addLocation("B");
        g.addRoad("A", "B");
        g.displayConnections(); // Should print: A connected to: B / B connected to: A
    }
}