package hackerrank;

import netscape.security.UserTarget;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class EvenTree {

    // Complete the evenForest function below.

    static void assignWeights(Graph graph, boolean[]visited, int[] weights, int curr) {

            visited[curr] = true;

            for(int dest : graph.getAdjacency(curr)) {
                if(!visited[dest]) {
                    assignWeights(graph, visited, weights, dest);
                    weights[curr] += weights[dest];
                }
            }
    }

    static int evenForest(int t_nodes, int t_edges, Graph graph) {

        int countCuts = 0;
        boolean[] visited = new boolean[t_nodes + 1];
        int[] weights = new int[t_nodes + 1];

        Arrays.fill(weights, 1);

        assignWeights(graph, visited, weights, 1);
        for(int i = 2; i < weights.length; ++i) {

            if(weights[i] % 2 == 0) countCuts++;
        }

        return countCuts;
    }
    
    public static void main(String[] args) throws IOException {

    }

    public static class Graph {

        private final ArrayList<List<Integer>> adjacencyList = new ArrayList<>();

        Graph(int nodesSize) {
            for(int i = 0; i <= nodesSize; ++i) {
                adjacencyList.add(new LinkedList<>());
            }
        }

        int getGraphSize() {
            return adjacencyList.size();
        }

        void addEdge(int from, int to) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }

        List<Integer> getAdjacency(int node) {
            return adjacencyList.get(node);
        }
    }
}