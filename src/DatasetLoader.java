import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

public class DatasetLoader {
    private static final String MENTIONS_PATH = "data\\higgs-mention_network.edgelist";
    private static final String SOCIAL_PATH = "data\\higgs-social_network.edgelist";
    private static final String RETWEET_PATH = "data\\higgs-retweet_network.edgelist";
    private static final String REPLY_PATH = "data\\higgs-reply_network.edgelist";

    private Graph mentionsGraph;
    private Graph socialGraph;
    private Graph retweetGraph;
    private Graph replyGraph;

    public DatasetLoader() {
    }

    public Graph getMentionsGraph() {
        if (mentionsGraph == null) {
            mentionsGraph = loadGraph(MENTIONS_PATH);
        }
        return mentionsGraph;
    }

    public Graph getSocialGraph() {
        if (socialGraph == null) {
            socialGraph = loadGraph(SOCIAL_PATH);
        }
        return socialGraph;
    }

    public Graph getRetweetGraph() {
        if (retweetGraph == null) {
            retweetGraph = loadGraph(RETWEET_PATH);
        }
        return retweetGraph;
    }

    public Graph getReplyGraph() {
        if (replyGraph == null) {
            replyGraph = loadGraph(REPLY_PATH);
        }
        return replyGraph;
    }

    private Graph loadGraph(String filePath) {
        Graph graph = new Graph();
        System.out.println("Loading graph data from " + filePath + "...");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                StringTokenizer tokenizer = new StringTokenizer(line);
                if (!tokenizer.hasMoreTokens()) {
                    continue;
                }

                String sourceToken = tokenizer.nextToken();
                if (!tokenizer.hasMoreTokens()) {
                    continue;
                }

                String targetToken = tokenizer.nextToken();

                try {
                    int source = Integer.parseInt(sourceToken);
                    int target = Integer.parseInt(targetToken);
                    graph.addEdge(source, target);
                } catch (NumberFormatException ignored) {
                    // Skip malformed lines and keep loading the remaining graph.
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Could not load graph data from " + filePath, e);
        }

        return graph;
    }
}