import java.util.*;

public class DegreeAnalyzer {

    private Graph graph;
    private Map<Integer, Integer> inDegrees;

    public DegreeAnalyzer(Graph graph) {
        this.graph = graph;
        this.inDegrees = graph.computeInDegrees();
    }
    
    public double averageInDegree() {
        if (inDegrees.isEmpty()) {
            return 0.0;
        }

        long total = 0;
        for (int degree : inDegrees.values()) {
            total += degree;
        }

        return (double) total / inDegrees.size();
    }

    public int maxInDegree() {
        int max = 0;
        for (int degree : inDegrees.values()) {
            if (degree > max) {
                max = degree;
            }
        }

        return max;
    }

    public List<Map.Entry<Integer, Integer>> topInDegreeUsers(int limit) {
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(inDegrees.entrySet());

        entries.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        if (entries.size() > limit) {
            return entries.subList(0, limit);
        }

        return entries;
    }

    public void printInDegreeStats(String graphName) {
        double average = averageInDegree();
        int max = maxInDegree();
        List<Map.Entry<Integer, Integer>> topUsers = topInDegreeUsers(5);

        System.out.println("=== " + graphName + " In-Degree Stats ===");
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        System.out.printf("Average in-degree: %.4f%n", average);
        System.out.println("Max in-degree: " + max);
        System.out.println();

        System.out.println("Top 5 users by in-degree:");
        for (int i = 0; i < topUsers.size(); i++) {
            Map.Entry<Integer, Integer> entry = topUsers.get(i);
            System.out.println((i + 1) + ". User " + entry.getKey() + " - in-degree " + entry.getValue());
        }

        System.out.println();
    }
}