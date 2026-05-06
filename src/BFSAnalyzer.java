import java.util.*;

public class BFSAnalyzer {
    private Graph graph;
    private ArrayList<Integer> nodeList;
    private Random random;

    public BFSAnalyzer(Graph graph) {
        this.graph = graph;
        this.nodeList = new ArrayList<>(graph.getNodes());
        this.random = new Random();
    }

    public int shortestPathLength(int start, int target, int maxDepth) {
        if (start == target) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distance = new HashMap<>();

        queue.add(start);
        visited.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentDistance = distance.get(current);

            if (maxDepth != -1 && currentDistance >= maxDepth) {
                continue; // skip nodes beyond max depth
            }

            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distance.put(neighbor, currentDistance + 1);

                    if (neighbor == target) {
                        return currentDistance + 1;
                    }

                    queue.add(neighbor);
                }
            }
        }

        return -1; // no path found
    }

    public BFSResult runRandomPairExperiment(int trials, int bfsMaxDepth) {
        if (nodeList.size() < 2) {
            return new BFSResult(trials, 0, 0.0, 0.0);
        }

        int connectedCount = 0;
        long totalShortestPathLength = 0;

        for (int i = 0; i < trials; i++) {
            int start = getRandomNode();
            int target = getRandomNode();

            while (target == start) {
                target = getRandomNode();
            }

            int pathLength = shortestPathLength(start, target, bfsMaxDepth);

            if (pathLength != -1) {
                connectedCount++;
                totalShortestPathLength += pathLength;
            }
        }

        double connectedFraction = (double) connectedCount / trials;

        double averageShortestPath = 0.0;
        if (connectedCount > 0) {
            averageShortestPath = (double) totalShortestPathLength / connectedCount;
        }

        return new BFSResult(
            trials,
            connectedCount,
            connectedFraction,
            averageShortestPath
        );
    }

    private int getRandomNode() {
        int index = random.nextInt(nodeList.size());
        return nodeList.get(index);
    }

    public void printRandomPairExperiment(String graphName, int trials, int bfsMaxDepth) {
        BFSResult result = runRandomPairExperiment(trials, bfsMaxDepth);

        System.out.println("=== " + graphName + " BFS Random Pair Experiment ===");
        System.out.println("Trials: " + result.trials);
        System.out.println("Connected pairs: " + result.connectedPairs);
        System.out.printf("Fraction connected: %.4f%n", result.connectedFraction);
        System.out.printf("Average shortest path among connected pairs: %.4f%n", result.averageShortestPath);
        System.out.println();
    }

    private static class BFSResult {
        int trials;
        int connectedPairs;
        double connectedFraction;
        double averageShortestPath;

        BFSResult(int trials, int connectedPairs, double connectedFraction, double averageShortestPath) {
            this.trials = trials;
            this.connectedPairs = connectedPairs;
            this.connectedFraction = connectedFraction;
            this.averageShortestPath = averageShortestPath;
        }
    }
}
