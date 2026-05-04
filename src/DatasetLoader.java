import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatasetLoader {
    private final Graph mentionsGraph;
    private final Graph socialGraph;
    private final Graph retweetGraph;
    private final Graph replyGraph;

    public DatasetLoader() {
        mentionsGraph = loadGraph("data\\higgs-mention_network.edgelist");
        socialGraph = loadGraph("data\\higgs-social_network.edgelist");
        retweetGraph = loadGraph("data\\higgs-retweet_network.edgelist");
        replyGraph = loadGraph("data\\higgs-reply_network.edgelist");
    }

    public Graph getMentionsGraph() {
        return mentionsGraph;
    }

    public Graph getSocialGraph() {
        return socialGraph;
    }

    public Graph getRetweetGraph() {
        return retweetGraph;
    }

    public Graph getReplyGraph() {
        return replyGraph;
    }

    private Graph loadGraph(String filePath) {
        Graph graph = new Graph();

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                try (Scanner lineScanner = new Scanner(line)) {
                    if (!lineScanner.hasNextInt()) {
                        continue;
                    }

                    int source = lineScanner.nextInt();
                    if (!lineScanner.hasNextInt()) {
                        continue;
                    }

                    int target = lineScanner.nextInt();
                    graph.addEdge(source, target);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Could not load graph data from " + filePath, e);
        }

        return graph;
    }
}