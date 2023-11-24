package org.example;

import org.example.algo.Dijkstra;
import org.example.model.UndirectedGraph;

import java.util.*;


public class ChinesePostmanRunner {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int [][] nodes = NodeReader.readNodesFromExcel("C:\\Users\\kenan\\OneDrive\\Desktop\\kenan.xlsx");
        System.out.print("Başlanğıc nöqteni daxil edin: ");

        int input = sc.nextInt();
        UndirectedGraph graph = UndirectedGraph.getGraphFromAdjMatrix(nodes);
    ChinesePostman<UndirectedGraph> chinesePostman = new ChinesePostman<>();

        chinesePostman.run(new Dijkstra(graph), graph, input-1);


    }
}
//test


