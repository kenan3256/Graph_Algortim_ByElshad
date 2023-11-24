package org.example.algo;

import org.example.model.Edge;
import org.example.model.Graph;
import org.example.model.Vertex;

import static org.example.util.GraphConst.INFINITY;

public final class BellmanFord extends SingleSourceShortestPathAlgo {

    public BellmanFord(Graph graph) {
        super(graph);
    }

    /**
     * This method runs Bellman-Ford algorithm.
     *
     * @param startVIdx index of source vertex - starting vertex
     * @return true if a negative cycle was not found, false otherwise
     */
    public boolean run(int startVIdx) {
        processGraph(startVIdx);

        for (int i = 0; i < graph.countOfVertices() - 1; i++) {
            for (Edge edge : graph.getEdges()) {
                if (graph.getVertexByIdx(edge.getSrcVIdx()).getMinDist() != INFINITY) {
                    update(graph, edge);
                }
            }
        }
        // checks if there exist negative cycles in graph G
        for (Edge edge : graph.getEdges()) {
            if (graph.getVertexByIdx(edge.getSrcVIdx()).getMinDist() != INFINITY
                    && graph.getVertexByIdx(edge.getDestVIdx()).getMinDist() > graph.getVertexByIdx(edge.getSrcVIdx()).getMinDist() + edge.getWeight()) {
                System.out.println("Graph contains a negative-weight cycle, no solution found.");
                return false;
            }
        }
        return true;
    }

    private void update(Graph graph, Edge edge) {
        Vertex srcVertex = graph.getVertexByIdx(edge.getSrcVIdx());
        Vertex destVertex = graph.getVertexByIdx(edge.getDestVIdx());
        int minDist = Math.min(destVertex.getMinDist(), srcVertex.getMinDist() + edge.getWeight());
        if (destVertex.getMinDist() != minDist) {
            destVertex.setMinDist(minDist);
            destVertex.setPrev(srcVertex);
        }
    }
}
