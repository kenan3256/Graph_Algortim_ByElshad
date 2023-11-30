package org.example;

import org.example.algo.Dijkstra;
import org.example.model.UndirectedGraph;

import java.time.LocalDateTime;
import java.util.*;


public class ChinesePostmanRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] nodes = NodeReader.readNodesFromExcel("C:\\Users\\kenan\\OneDrive\\Desktop\\kenan.xlsx");
        System.out.print("Başlanğıc nöqtəni daxil edin: ");

        int input = sc.nextInt();
        boolean flag = true;
        while (flag) {

            if (input <= 0) {
                System.out.println("Daxil etdiyiniz nömrə qrafin ölçülərindən kiçikdir");
                System.out.print("Başlanğıc nöqtəni yenidən daxil edin:");
                input = sc.nextInt();


            } else if (input > nodes.length) {
                System.out.println("Daxil etdiyiniz nömrə qrafin ölçülərindən böyükdür");
                System.out.print("Başlanğıc nöqtəni yenidən daxil edin:");
                input = sc.nextInt();

            } else {
                flag = false;
            }
        }

        System.out.println("Start "+LocalDateTime.now());
        UndirectedGraph graph = UndirectedGraph.getGraphFromAdjMatrix(nodes);
        ChinesePostman<UndirectedGraph> chinesePostman = new ChinesePostman<>();

        chinesePostman.run(new Dijkstra(graph), graph, input-1);
        System.out.println("End "+LocalDateTime.now());
    }
}



