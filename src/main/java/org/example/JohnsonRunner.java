package org.example;

import org.example.algo.Johnson;
import org.example.model.DirectedGraph;
import org.example.util.GraphConst;

public class JohnsonRunner {

    public static void main(String[] args) {
        DirectedGraph graph = DirectedGraph.getGraphFromAdjMatrix(new int[][]{
                {0, 0, 0, 689, 0, 1350, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 262, 0, 0, 395, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 262, 0, 0, 0, 0, 552, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {689, 0, 0, 0, 0, 0, 0, 918, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 929, 0, 612, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1350, 395, 0, 0, 0, 0, 299, 445, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 552, 0, 0, 299, 0, 0, 500, 0, 0, 647, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 918, 929, 445, 0, 0, 282, 0, 451, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 500, 282, 0, 0, 0, 668, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 612, 0, 0, 0, 0, 0, 0, 0, 912, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 451, 0, 0, 0, 0, 272, 0, 0, 0, 0, 1025, 0},
                {0, 0, 0, 0, 0, 0, 647, 0, 668, 0, 0, 0, 0, 0, 257, 213, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 912, 272, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 478, 0, 0, 0, 817},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 257, 0, 478, 0, 0, 189, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 213, 0, 0, 0, 0, 200, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 189, 200, 0, 201, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1025, 0, 0, 0, 0, 0, 201, 0, 554},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 817, 0, 0, 0, 554, 0}

        });
        int[][] D = Johnson.run(graph);
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D.length; j++) {
                System.out.printf((D[i][j] == GraphConst.INFINITY) ? "  x " : "%3d ", D[i][j]);
            }
            System.out.println();
        }
    }
}