package io.lsn;


import java.util.*;

public class GraphsApp {
    public static void main(String[] args) {
        System.out.println("Enter number of pairs to be input:");
        int n = getSingleInt(); //number of connections
        Set<int[]> connections = getPairsOfIntegers(n);
//        Set<int[]> connections = new HashSet<>(Arrays.asList(new int[]{4, 3}, new int[]{1 ,3}, new int[]{5, 6}));
//        printOutput(connections);
        Map<Integer, Set<Integer>> graph = getGraphFromConnections(connections);
        System.out.println(getNumberOfGraphs(graph)); //<< main result
    }

    static int getNumberOfGraphs(Map<Integer, Set<Integer>> graph) {
        int numberOfGraphs = 0;
        //Necessary set to make flags on visited nodes
        Set<Integer> nodesVisited = new HashSet<>();
        for (Integer key : graph.keySet()) {
            if (!nodesVisited.contains(key)) {
                //DigInGraph proliferate in one island - separated graph
                DigInGraph(graph, key, nodesVisited);
                numberOfGraphs++;
            }
        }
        return numberOfGraphs;
    }

    private static void DigInGraph(Map<Integer, Set<Integer>> graph, int node, Set<Integer> nodesVisited) {
        nodesVisited.add(node);
        for (int childNode : graph.get(node)) {
            if (!nodesVisited.contains(childNode)) {
                DigInGraph(graph, childNode, nodesVisited);
            }
        }
    }

    static Map<Integer, Set<Integer>> getGraphFromConnections(Set<int[]> connections) {
        //Method is creating map of sets from connections entered by user
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (int[] connection : connections) {
            //creating new set for one node or adding neighbor is set exists
            result.computeIfAbsent(connection[0], key -> new HashSet<>()).add(connection[1]);
            result.computeIfAbsent(connection[1], key -> new HashSet<>()).add(connection[0]);
        }
        return result;
    }

    private static void printOutput(Set<int[]> outputSet) {
        // this function is displaying connections as it was in task
        for (int[] pair : outputSet) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    private static Set<int[]> getPairsOfIntegers(int n) {
        Set<int[]> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            //loop is conducting user thought demanded number of connections
            System.out.println("Enter first vert number of " + (i + 1) + " connection:");
            int a = getSingleInt();
            System.out.println("Enter second vert number of " + (i + 1) + " connection:");
            int b = getSingleInt();

            // Ensure that both vertices are different
            if (a != b) {
                int[] pair = {Math.min(a, b), Math.max(a, b)};

                // checking if this pair already exists
                boolean pairExists = false;
                for (int[] existingPair : result) {
                    if (Arrays.equals(existingPair, pair)) {
                        pairExists = true;
                        i--; //step back to renew this input
                        System.out.println("Duplicate pair detected. Ignoring: [" + a + "]-[" + b + "]");
                    }
                }
                if (!pairExists) {
                    result.add(pair);
                }
            } else {
                i--; //step back to renew this input
                System.out.println("Vertices must be different. Ignoring: [" + a + "]-[" + b + "]");
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