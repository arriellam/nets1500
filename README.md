# nets1500

This project loads and analyzes the SNAP Higgs Twitter networks.

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

Compile and run the App class.
