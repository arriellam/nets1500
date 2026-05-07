# Empirical Analysis of Network Structure Across Twitter Interaction Types

Nets 1500 Final Project

**Project Category**: Empirical Analysis

**Authors:** Sheila Bukenya, Evan Grove, Arriella Mafuta

NOTE: The Summary Report is in attached in this repository as well.

## Overview

Our project is an empirical analysis of the SNAP Higgs Twitter dataset. We compare four different twitter networks, the follower (social) network, retweet network, reply network and mention network. And each network is represented as a directed weightless graph where users are the nodes and the interactions are the edges. The goal of our project is to understand how different types of relationships create different network structures.

First, we use in-degree analysis to measure how attention is distributed across each network. We also do a BFS random pair analysis experiment to estimate how connected each graph is and how long shortest paths between reachable users are. We also compute clustering coefficients to compare how much local community structure exists in each graph. 

## Work Distribution

- Arriella: Implemented the in-degree analysis code, including calculating average in-degree, maximum in-degree, top users by in-degree, and concentration ratios. Wrote the in-degree analysis section of the report and interpreted the results.

- Sheila: Implemented the BFS/random-pair shortest path analysis code, including testing whether randomly selected users were connected and calculating the average shortest path length among connected pairs. Wrote the BFS analysis section of the report.

- Evan: Implemented the clustering coefficient analysis code, including computing local clustering values and comparing average clustering across the four graphs. Wrote the clustering analysis section of the report and helped interpret the community-structure results.

## AI-usage

Used to explore existing datasets and make our brainstorming ideas more feasible.

## Dataset Setup

The mention, reply, and retweet graphs are stored in this repository. The social graph is not stored in Git because it exceeds GitHub's file size limit.

Download the social graph from the [SNAP Higgs Twitter dataset page](https://snap.stanford.edu/data/higgs-twitter.html). Its at the very bottom of the page.

1. Download `social_network.edgelist.gz` from the SNAP page.
2. Extract `social_network.edgelist.gz`  and add the `higgs-social_network.edgelist` file into the `data` folder.

After setup, the repository should contain these files:

- `data/higgs-social_network.edgelist`
- `data/higgs-retweet_network.edgelist`
- `data/higgs-reply_network.edgelist`
- `data/higgs-mention_network.edgelist`

## Run

Clone this repository in vscode. Compile and run the App class. Results are printed in the terminal.

## File Details

In the src folder, we have the following files and their functionality:

- `App.java`: This is the class containing main function. It instantiates the DatasetLoader. It then runs the three analyses and outputs the results to the terminal for each network.
- `DatasetLoader`: Reads the edgelists of the four networks and constructs the directed graphs for each network.
- `Graph.java`: Simple arraylist representation of a graph.
- `DegreeAnalyzer`: Computes the average in-degree in each graph and compare the nodes in-degree distribution in each graph to understand how frequent and concentrated each type of interaction is.
  
- `ClusteringAnalyzer`: Computes and average the clustering coefficients of all the nodes in each group to measure how much triadic closure is in effect.
- `BFS Analyzer`:  Picks 2 random nodes, run BFS, check if the 2 nodes are connected and find the shortest path if they are, repeat 10,000 times, keep track of the fraction of nodes connected and the average shortest path of connected nodes.