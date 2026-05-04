import java.util.*;

public class Graph {
    // representation of a directed graph
    HashMap<Integer, ArrayList<Integer>> graph;


    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        if (!graph.containsKey(u)) {
            graph.put(u, new ArrayList<>());
        }
        if (!graph.containsKey(v)) {
            graph.put(v, new ArrayList<>());
        }
        graph.get(u).add(v);
    }
}