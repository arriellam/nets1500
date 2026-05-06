public class App {
    public static void main(String[] args) {
        DatasetLoader datasetLoader = new DatasetLoader();
        Graph mentionsGraph = datasetLoader.getMentionsGraph();
        Graph socialGraph = new DatasetLoader().getSocialGraph();
        Graph retweetGraph = new DatasetLoader().getRetweetGraph();
        Graph replyGraph = new DatasetLoader().getReplyGraph();

        // Degree analysis
        mentionsGraph.degreeAnalysis("Mentions Graph");
        socialGraph.degreeAnalysis("Social Graph");
        retweetGraph.degreeAnalysis("Retweet Graph");
        replyGraph.degreeAnalysis("Reply Graph");

        // BFS analysis
        int trials = 10000;
        int bfsMaxDepth = -1; // no depth limit for BFS

        mentionsGraph.bfsAnalysis("Mentions Graph", trials, bfsMaxDepth);
        retweetGraph.bfsAnalysis("Retweet Graph", trials, bfsMaxDepth);
        replyGraph.bfsAnalysis("Reply Graph", trials, bfsMaxDepth);
        // limit BFS max depth  and trials for social graph due to its larger size
        socialGraph.bfsAnalysis("Social Graph", 100, 15);

        // Clustering analysis (placeholder for now)
    }
}
