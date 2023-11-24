package org.example;

import org.example.algo.Johnson;
import org.example.model.DirectedGraph;
import org.example.util.GraphConst;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class JohnsonTest {

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
            {0, 4, 2, 8, 9, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 3, 0},
            {0, 0, 0, 0, 3, 0, 1, 0},
            {0, 0, 0, 0, 0, 6, 0, 0},
            {0, 0, 6, 0, 0, 0, 0, 1},
            {0, 0, 0, 8, 0, 0, 0, 0},
            {0, 0, 5, 0, 8, 0, 0, 0},
            {0, 0, 0, 0, 0, 8, 1, 0}
    });
    private static final DirectedGraph GRAPH_3 = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
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
    private static final DirectedGraph GRAPH_WITH_NEG_CYCLE = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
            {0, 1, 0, 0},
            {0, 0, -1, 0},
            {0, 0, 0, -1},
            {-1, 0, 0, 0}
    });

    @Test
    public void testForGraph1() {
        int[][] minDistances = Johnson.run(GRAPH_1);
        int[] expected8Line = new int[]{GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 0, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY};
        int[] expected9Line = new int[]{GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 16, 8, GraphConst.INFINITY, 9, 0, GraphConst.INFINITY, GraphConst.INFINITY, 5, 8, GraphConst.INFINITY, 12, 10, GraphConst.INFINITY};
        assertArrayEquals("The elements are not equal in row 8", expected8Line, minDistances[8]);
        assertArrayEquals("The elements are not equal in row 9", expected9Line, minDistances[9]);
    }

    @Test
    public void testForGraph2() {
        int[][] expected = new int[][]{
                {0, 4, 2, 8, 5, 14, 3, 6},
                {1, 0, 3, 9, 6, 15, 3, 7},
                {GraphConst.INFINITY, GraphConst.INFINITY, 0, 20, 3, 12, 1, 4},
                {GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 0, GraphConst.INFINITY, 6, GraphConst.INFINITY, GraphConst.INFINITY},
                {GraphConst.INFINITY, GraphConst.INFINITY, 6, 17, 0, 9, 2, 1},
                {GraphConst.INFINITY, GraphConst.INFINITY, GraphConst.INFINITY, 8, GraphConst.INFINITY, 0, GraphConst.INFINITY, GraphConst.INFINITY},
                {GraphConst.INFINITY, GraphConst.INFINITY, 5, 25, 8, 17, 0, 9},
                {GraphConst.INFINITY, GraphConst.INFINITY, 6, 16, 9, 8, 1, 0}
        };
        int[][] actualMinDist = Johnson.run(GRAPH_2);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(String.format("The elements are not equal in row %d", i), expected[i], actualMinDist[i]);
        }
    }

    @Test
    public void testForGraph3() {
        int[][] minDistances = Johnson.run(GRAPH_3);
        int[] expected1Line = new int[]{0, 5, 4, GraphConst.INFINITY, 9, 13, 8, 5, 12, 21, 17, 3, 21, 19, 2, 5, 13, 11};
        assertArrayEquals(expected1Line, minDistances[0]);
    }

    @Test
    public void testForGraphWithNegCycleShouldReturnEmpty2DMatrix() {
        int[][] minDist = Johnson.run(GRAPH_WITH_NEG_CYCLE);
        assertEquals(0, minDist.length);
    }
}
