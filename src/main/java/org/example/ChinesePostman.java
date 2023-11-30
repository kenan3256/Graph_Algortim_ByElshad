package org.example;

import org.example.algo.SingleSourceShortestPathAlgo;
import org.example.model.Edge;
import org.example.model.Graph;
import org.example.model.UndirectedGraph;
import org.example.model.Vertex;
import org.example.util.GeneratorUtils;
import org.example.util.GraphUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public final class ChinesePostman<G extends UndirectedGraph> {

    public ChinesePostman() {
    }

    public static void printThreeDimensionalArray(List<List<List<Integer>>> list) {
        System.out.print("[");
        for (List<List<Integer>> outerList : list) {
            System.out.print("[");
            for (List<Integer> innerList : outerList) {
                System.out.print("["+(innerList.get(0)+1)+"-"+(innerList.get(1)+1)+"],");
            }
        }
        System.out.print("]");
    }

    public List<Integer> run(SingleSourceShortestPathAlgo pathAlgo, G graph, int startVIdx) {
        var allCombinationsOfPairingOddDegreeVertices = getAllCombinationsOfPairingOddDegreeVertices(graph);
        System.out.println("Grafın tək dərəcəli nöqtələri arasındakı cütləşmələri:");
        printThreeDimensionalArray(allCombinationsOfPairingOddDegreeVertices);
        System.out.println();

        int lengthOfRoute = graph.getSumOfAllEdges();
        if (!allCombinationsOfPairingOddDegreeVertices.isEmpty()) {
            var edgesWithMinWeight = getEdgesWithMinWeight(graph, pathAlgo, allCombinationsOfPairingOddDegreeVertices); //
            lengthOfRoute += edgesWithMinWeight.stream().mapToInt(Edge::getWeight).sum(); //
            increaseFrequencyOfGraphEdges(graph, edgesWithMinWeight); //allah kerimdi
            System.out.println("Grafın ən yaxın tək dərəcəli nöqtələri və arasındakı məsafə:  ");
            for (Edge edge : edgesWithMinWeight) {
                System.out.print("["+(edge.getSrcVIdx()+1)+"--"+edge.getWeight()+"--"+(edge.getDestVIdx()+1)+"],");

            }
            System.out.println();
        }

        System.out.println("Gediləcək yolun marşurutu: ");
        List<Integer> eulerCircuit = GraphUtils.getEulerCircuit((UndirectedGraph) graph.clone(), startVIdx);
        eulerCircuit.forEach(vIdx -> System.out.print(vIdx + 1 + " "));
        System.out.printf("\nGediləcək yolun uzunluğu = %d.\n\n", lengthOfRoute);
        return eulerCircuit;
    }

    private void increaseFrequencyOfGraphEdges(G graph, List<Edge> edges) {
        for (Edge edge : edges) {
            for (Edge graphEdge : graph.getEdges()) {
                if (edge.equals(graphEdge)) {
                    graphEdge.increaseFrequency();
                }
            }
        }
    }

    private List<Edge> getEdgesWithMinWeight(G graph, SingleSourceShortestPathAlgo pathAlgo, List<List<List<Integer>>> allCombinations) {
        List<List<Edge>> edgesFromCombination = new ArrayList<>();

        for (List<List<Integer>> pairsFromCombination : allCombinations) {
            List<Edge> edgeHolderList = new ArrayList<>();
            for (List<Integer> pair : pairsFromCombination) {
                pathAlgo.run(pair.get(0));
                List<Edge> edgesBetweenPair = pathAlgo.getPathFromSrcToDestVertex(pair.get(1));
                edgeHolderList.addAll(edgesBetweenPair);
            }
            edgesFromCombination.add(edgeHolderList);
        }
        return edgesFromCombination.stream()
                .min(Comparator.comparing(edges -> edges.stream().mapToInt(Edge::getWeight).sum()))
                .orElseThrow();
    }

    private List<List<List<Integer>>> getAllCombinationsOfPairingOddDegreeVertices(Graph graph) {
        var oddVIndexes = graph.getOddDegreeVertices().stream()
                .map(Vertex::getIdx)
                .toList();
        return GeneratorUtils.getCombinations(oddVIndexes);
    }
}


