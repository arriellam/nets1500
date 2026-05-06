import java.util.*;

public class Graph {
    // representation of a directed graph
    private HashMap<Integer, ArrayList<Integer>> graph;
    private Set<Integer> nodes;
    private int edgeCount;

    public Graph() {
        graph = new HashMap<>();
        nodes = new HashSet<>();   
        edgeCount = 0; 
    }

    public void addEdge(int u, int v) {
         nodes.add(u);
        nodes.add(v);

        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        graph.get(u).add(v);
        edgeCount++;
    }

    public Integer getNodeCount() {
        return nodes.size();
    }

    public Integer getEdgeCount() {
        return edgeCount;
    }

    public List<Integer> getNeighbors(int node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }

    public Set<Integer> getNodes() {
        return nodes;
    }

    public Map<Integer, ArrayList<Integer>> getGraph() {
        return graph;
    }

    public Map<Integer, Integer> computeInDegrees() {
        Map<Integer, Integer> inDegrees = new HashMap<>();

        // Make sure every node appears, even if its in-degree is 0
        for (int node : getNodes()) {
            inDegrees.put(node, 0);
        }

        // For every edge from -> to, increment the in-degree of "to"
        for (int from : getNodes()) {
            for (int to : getNeighbors(from)) {
                inDegrees.put(to, inDegrees.getOrDefault(to, 0) + 1);
            }
        }

        return inDegrees;
    }

    public void degreeAnalysis(String graphName) {
        DegreeAnalyzer analyzer = new DegreeAnalyzer(this);
        analyzer.printInDegreeStats(graphName);
    }

    public void bfsAnalysis(String graphName, int trials, int bfsMaxDepth) {
        BFSAnalyzer analyzer = new BFSAnalyzer(this);
        analyzer.printRandomPairExperiment(graphName, trials, bfsMaxDepth);
}
}