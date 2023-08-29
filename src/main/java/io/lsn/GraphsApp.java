package io.lsn;


// The first line of input contains a positive number n, next n lines contains pairs of positive integers,
// where each pair identifies a connection between two vertices in a graph.
// Please provide a working code that will give us the answer for the following questions:
// how many separated graphs are in the input.

import java.util.*;

public class GraphsApp {
    public static void main(String[] args) {
//        System.out.println("Enter number of pairs to be input:");
//        int n = getSingleInt();
//        Set<int[]> connections = getPairsOfIntegers(n);
        Set<int[]> connections = new HashSet<>(Arrays.asList(new int[]{1, 4}, new int[]{4,3}, new int[]{5,6}, new int[]{2,3}));
        printOutput(connections);
        Map<Integer,Set<Integer>> graph = getGraphFromConnections(connections);
        System.out.println(graph);
        System.out.println("How many graphs: "+getNumberOfGraphs(graph));
    }

    private static int getNumberOfGraphs(Map<Integer, Set<Integer>> graph) {
        int numberOfGraphs = 0;
        Set<Integer> nodesVisited = new HashSet<>();
        DigInGraph(graph, 1, nodesVisited);



        return numberOfGraphs;
    }

    private static void DigInGraph(Map<Integer, Set<Integer>> graph, int node, Set<Integer> nodesVisited) {
        nodesVisited.add(node);
        System.out.println("I'm visiting node: "+node);
        for (int childNode:graph.get(node)) {
            if (!nodesVisited.contains(childNode)){
                DigInGraph(graph,childNode,nodesVisited);
            }
        }
    }

    private static Map<Integer, Set<Integer>> getGraphFromConnections(Set<int[]> connections) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int[] connection : connections) {
            result.computeIfAbsent(connection[0],key -> new HashSet<>()).add(connection[1]);
            result.computeIfAbsent(connection[1],key -> new HashSet<>()).add(connection[0]);
        }
        return result;
    }

    private static void printOutput(Set<int[]> outputSet) {
        for (int[] pair : outputSet) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    private static Set<int[]> getPairsOfIntegers(int n) {
        Set<int[]> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter first vert number of " + (i + 1) + " connection:");
            int a = getSingleInt();
            System.out.println("Enter second vert number of " + (i + 1) + " connection:");
            int b = getSingleInt();

            // Ensure that both vertices are different
            if (a != b) {
                int[] pair = {Math.min(a, b), Math.max(a, b)};

                // Check if the pair already exists in the set
                boolean pairExists = false;
                for (int[] existingPair : result) {
                    if (Arrays.equals(existingPair, pair)) {
                        pairExists = true;
                        i--;
                        System.out.println("Duplicate pair detected. Ignoring: [" + a + "]-[" + b+"]");
                    }
                }
                if (!pairExists) {
                    result.add(pair);
                }
            } else {
                i--;
                System.out.println("Vertices must be different. Ignoring: [" + a + "]-[" + b+"]");
            }
        }
        return result;
    }

    private static int getSingleInt() {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        String userInput;
        do {
            userInput = scanner.nextLine();
            try {
                n = Integer.parseInt(userInput);
                if (n <= 0) {
                    System.out.println("Number must be positive. Ignoring: " + n);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid format. Ignoring: " + userInput);
            }
        } while (n <= 0);
        return n;
    }

}