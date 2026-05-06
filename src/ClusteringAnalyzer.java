import java.util.*;

public class ClusteringAnalyzer {

    private static final int SAMPLE_SIZE = 10_000;

    private final Graph graph;
    private final List<Integer> sampleNodes;

    private final Map<Integer, Set<Integer>> outNeighbors = new HashMap<>();

    private final Map<Integer, Set<Integer>> inNeighbors = new HashMap<>();

    public ClusteringAnalyzer(Graph graph) {
        this.graph = graph;
        sampleNodes = chooseSampleNodes();
        buildAdjacencySets();
    }

    //Randomly samples 10,000 nodes
    private List<Integer> chooseSampleNodes() {
        List<Integer> nodes = new ArrayList<>(graph.getGraph().keySet());
        Collections.shuffle(nodes, new Random());
        return nodes.subList(0, Math.min(SAMPLE_SIZE, nodes.size()));
    }

    //Converts adj list into hash set so edge existence checks becomes approximately O(1) time
    private void buildAdjacencySets() {
        Set<Integer> sampleSet = new HashSet<>(sampleNodes);
        //Visit every edge and store outgoing edges
        for (int node : graph.getGraph().keySet()) {
            outNeighbors.put(node, new HashSet<>());
            if (sampleSet.contains(node)) {
                inNeighbors.put(node, new HashSet<>());
            }
        }
        //Store incoming edges for sampled nodes
        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.getGraph().entrySet()) {
            int u = entry.getKey();
            for (int v : entry.getValue()) {
                if (u != v) {
                    outNeighbors.get(u).add(v);

                    Set<Integer> sampledIncoming = inNeighbors.get(v);
                    if (sampledIncoming != null) {
                        sampledIncoming.add(u);
                    }
                }
            }
        }
    }

    //Compute clustering coefficient for one node
    public double localClusteringCoefficient(int node) {
        Set<Integer> neighbors = getDirectedNeighbors(node);
        int k = neighbors.size();
        //No link can exist between less than 2 neighbors -> clustering is 0
        if (k < 2) {
            return 0.0;
        }
        //Divide the actual directed links among neighbors by total possible directed links
        long linksBetweenNeighbors = countDirectedEdgesAmong(neighbors);
        return (double) linksBetweenNeighbors / ((double) k * (k - 1));
    }

    //Gets all immediate directed neighbors (incoming and outgoing) for a node
    private Set<Integer> getDirectedNeighbors(int node) {
        Set<Integer> neighbors = new HashSet<>();
        neighbors.addAll(outNeighbors.getOrDefault(node, Collections.emptySet()));
        neighbors.addAll(inNeighbors.getOrDefault(node, Collections.emptySet()));
        return neighbors;
    }

    //Counts the actual number of edges for a set of neighbors
    private long countDirectedEdgesAmong(Set<Integer> neighbors) {
        long edgeCount = 0;
        for (int from : neighbors) {
            Set<Integer> fromOut = outNeighbors.getOrDefault(from, Collections.emptySet());
            for (int to : fromOut) {
                if (from != to && neighbors.contains(to)) {
                    edgeCount++;
                }
            }
        }
        return edgeCount;
    }

    //Average 10,000 sampled clustering coefficients
    public double averageClusteringCoefficient() {
        if (sampleNodes.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (int node : sampleNodes) {
            total += localClusteringCoefficient(node);
        }
        return total / sampleNodes.size();
    }

    //Prints the sample size and average clustering coefficient for a graph
    public void printResults() {
        int sampleSize = sampleNodes.size();
        System.out.println("Sample size: " + sampleSize);
        System.out.println("Average clustering coefficient: " + averageClusteringCoefficient());
    }
}

