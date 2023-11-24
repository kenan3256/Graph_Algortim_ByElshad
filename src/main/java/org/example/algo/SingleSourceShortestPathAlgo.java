package org.example.algo;

import org.example.model.Edge;
import org.example.model.Graph;
import org.example.model.Vertex;
import org.example.util.GraphConst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SingleSourceShortestPathAlgo {

    protected Graph graph;
    private int startVertexIdx;

    public SingleSourceShortestPathAlgo(Graph graph) {
        this.graph = graph;
    }

    public abstract boolean run(int startVIdx);

    public void displayPaths() {
        graph.getVertices()
                .forEach(vertex -> {
                    System.out.println(String.format("%d -- %d: ", startVertexIdx, vertex.getIdx()) + getPathFromSrcToDestVertex(vertex.getIdx()));
                });
        System.out.println();
    }

    public List<Edge> getPathFromSrcToDestVertex(int destVIdx) {
        List<Edge> path = new ArrayList<>();
        Vertex vertex = graph.getVertexByIdx(destVIdx), prevVertex;
        while ((prevVertex = vertex.getPrev()) != null) {
            path.add(graph.getEdge(prevVertex, vertex));
            vertex = prevVertex;
        }
        Collections.reverse(path);
        return path;
    }

    public List<Integer> getMinDistances() {
        return graph.getVertices().stream()
                .map(Vertex::getMinDist)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * This method sets the graph to its initial state for future execution of the algorithm.
     * It sets the minimal distances to infinity for all vertices except the starting vertex, whose minimal distance will be set to zero.
     *
     * @param startVertexIdx index of start vertex
     */
    protected void processGraph(int startVertexIdx) {
        this.startVertexIdx = startVertexIdx;
        graph.getVertices()
                .forEach(vertex -> {
                    vertex.setPrev(null);
                    vertex.setMinDist(GraphConst.INFINITY);
                });
        graph.getVertexByIdx(startVertexIdx).setMinDist(GraphConst.ZERO);
    }
}
