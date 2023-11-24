package org.example;

import org.example.algo.BellmanFord;
import org.example.model.DirectedGraph;
import org.example.util.GraphConst;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BellmanFordOnDirectedGraphTest {

    private static final DirectedGraph GRAPH_1 = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
//           0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17
            {0, 9, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 9, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 5, 8, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 7, 9, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 2, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 1, 0, 0, 4, 0}
    });
    private static final DirectedGraph GRAPH_2 = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
//           0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17
            {0, 0, 4, 0, 9, 0, 0, 5, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 9, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 7, 6, 0, 0, 0, 9, 0, 0, 9, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0}
    });
    private static final DirectedGraph GRAPH_3 = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
            {0, 3, 0, 0, 1, 0, 0, 0},
            {2, 0, 0, 2, 0, 9, 9, 0},
            {0, 2, 0, 0, 5, 0, 0, 0},
            {0, 7, 0, 0, 0, 0, 0, 8},
            {0, 0, 4, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 0, 0, 4, 4, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0}
    });
    private static final DirectedGraph GRAPH_4 = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
            {0, 1, 8, 0, 0, 0, 0, 0},
            {0, 0, 2, 7, 0, 0, 0, 0},
            {0, 3, 0, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, 0, 9, 0, 9},
            {0, 0, 0, 0, 0, 0, 0, 3},
            {0, 9, 0, 5, 0, 0, 8, 0},
            {0, 0, 6, 0, 6, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    });

    @Test
    public void test1ForGraph1() {
        BellmanFord bellman = new BellmanFord(GRAPH_1);
        bellman.run(9);
        var actual = bellman.getMinDistances();
        var expected = List.of(GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 16, 8, GraphConst.INFINITY, 9, 0, GraphConst.INFINITY, GraphConst.INFINITY, 5, 8, GraphConst.INFINITY, 12, 10, GraphConst.INFINITY);
        assertEquals(expected, actual);
    }

    @Test
    public void test2ForGraph1() {
        BellmanFord bellman = new BellmanFord(GRAPH_1);
        bellman.run(8);
        var actual = bellman.getMinDistances();
        var expected = List.of(GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 0, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY);
        assertEquals(expected, actual);
    }

    @Test
    public void test1ForGraph2() {
        BellmanFord bellman = new BellmanFord(GRAPH_2);
        bellman.run(0);
        var actual = bellman.getMinDistances();
        var expected = List.of(0, 5, 4, GraphConst.INFINITY, 9, 13, 8, 5, 12, 21, 17, 3, 21, 19, 2, 5, 13, 11);
        assertEquals(expected, actual);
    }

    @Test
    public void test1ForGraph3() {
        BellmanFord bellman = new BellmanFord(GRAPH_3);
        bellman.run(1);
        var actual = bellman.getMinDistances();
        var expected = List.of(2, 0, 7, 2, 3, 9, 5, 10);
        assertEquals(expected, actual);
    }

    @Test
    public void test1ForGraph4() {
        BellmanFord bellman = new BellmanFord(GRAPH_4);
        bellman.run(0);
        var actual = bellman.getMinDistances();
        var expected = List.of(0, 1, 3, 8, 7, 17, 25, 10);
        assertEquals(expected, actual);
    }
}
