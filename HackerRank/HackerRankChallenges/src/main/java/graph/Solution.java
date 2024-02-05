package graph;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<String> listOfRows = new ArrayList<>();

        IntStream.range(0, n).forEach(nItr -> {
            try {
                String row = bufferedReader.readLine();

                // Write your code here
                listOfRows.add(row);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<List<Integer>>> bidirectionalTunnels = new ArrayList<>();
        IntStream.range(0, k).forEach(kItr -> {
            try {
                String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int i1 = Integer.parseInt(secondMultipleInput[0]);

                int j1 = Integer.parseInt(secondMultipleInput[1]);

                int i2 = Integer.parseInt(secondMultipleInput[2]);

                int j2 = Integer.parseInt(secondMultipleInput[3]);

                // Write your code here
                List<List<Integer>> container = new ArrayList<>();
                List<Integer> point1 = new ArrayList<>();
                point1.add(i1);
                point1.add(j1);
                List<Integer> point2 = new ArrayList<>();
                point2.add(i2);
                point2.add(j2);
                container.add(point1);
                container.add(point2);
                bidirectionalTunnels.add(container);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Write your code here
        System.out.println(listOfRows);
        System.out.println(bidirectionalTunnels);

        Graph board = new Graph();
        String startPoint = addingVertecies(listOfRows, board, m);
        addingEdges(listOfRows, board, m);
        for (List<List<Integer>> tunnelRelations : bidirectionalTunnels) {
            String firstPointValue = String.valueOf(tunnelRelations.get(0).get(0) - 1) + String.valueOf(tunnelRelations.get(0).get(1) - 1);
            String secondPointValue = String.valueOf(tunnelRelations.get(1).get(0) - 1) + String.valueOf(tunnelRelations.get(1).get(1) - 1);
            Vertex v1 = board.getVertex(firstPointValue);
            Vertex v2 = board.getVertex(secondPointValue);
            board.addEdge(v1, v2);
        }
        List<Integer> probabilities = new ArrayList<>();
        probabilities.add(0);
        probabilities.add(0);
        board.printGraph();
        traverseDFSInGraphAndAddProbabilities(board, probabilities, startPoint);
        System.out.println(probabilities);
        double finalProbabilityResult = calculateTheProbabilityRatio(probabilities);
        System.out.println(finalProbabilityResult);

        bufferedReader.close();
    }

    private static void traverseDFSInGraphAndAddProbabilities(Graph board, List<Integer> probabilties, String startPoint) {
        Set<Vertex> seen = new HashSet<>();
        Vertex traversVertex = board.getVertex(startPoint);
        seen.add(traversVertex);
        traverseDFSInGraphAndAddPrababilities(board, probabilties, startPoint, seen, traversVertex);
    }

    private static void traverseDFSInGraphAndAddPrababilities(Graph board, List<Integer> probabilties, String startPoint, Set<Vertex> seen, Vertex traversVertex) {
        if (traversVertex.isExit) {
            probabilties.set(1, probabilties.get(1) + 1);
            return;
        }
        if (traversVertex.isBombed || board.adjMap.get(traversVertex).isEmpty()) {
            probabilties.set(0, probabilties.get(0) + 1);
            return;
        }
        if (containsAll(seen, board.adjMap.get(traversVertex))) {
            if (board.adjMap.get(traversVertex).size() == 1) {
                probabilties.set(0, probabilties.get(0) + 1);
            }
            return;
        }


        for (Vertex connection : board.adjMap.get(traversVertex)) {
            if (!seen.contains(connection)) {
                seen.add(connection);
                traverseDFSInGraphAndAddPrababilities(board, probabilties, startPoint, seen, connection);
            }
        }
    }

    private static boolean containsAll(Set<Vertex> seen, List<Vertex> traversVertexList) {
        for (Vertex v : traversVertexList) {
            if (!seen.contains(v)) {
                return false;
            }
        }
        return true;
    }

    private static double calculateTheProbabilityRatio(List<Integer> probabilties) {
        int total = probabilties.get(0) + probabilties.get(1);
        if (total == 0) return 0d;
        double ratio = (double) probabilties.get(1) / (double) total;
        return ratio;
    }

    private static String addingVertecies(List<String> listOfRows, Graph board, int m) {
        String startPoint = "";
        for (int j = 0; j < listOfRows.size(); j++) {
            for (int i = 0; i < m; i++) {
                Vertex vertex;
                if (listOfRows.get(j).charAt(i) == '#') {
                    continue;
                } else if (listOfRows.get(j).charAt(i) == '*') {
                    vertex = new Vertex(String.valueOf(j) + String.valueOf(i), true, false, false);
                } else if (listOfRows.get(j).charAt(i) == 'O') {
                    vertex = new Vertex(String.valueOf(j) + String.valueOf(i), false, false, false);
                } else if (listOfRows.get(j).charAt(i) == '%') {
                    vertex = new Vertex(String.valueOf(j) + String.valueOf(i), false, true, false);
                } else {
                    vertex = new Vertex(String.valueOf(j) + String.valueOf(i), false, false, true);
                    startPoint = String.valueOf(j) + String.valueOf(i);
                }
                board.addVertices(vertex);
            }
        }
        return startPoint;
    }

    private static void addingEdges(List<String> listOfRows, Graph board, int m) {
        for (int j = 0; j < listOfRows.size(); j++) {
            for (int i = 0; i < m; i++) {
                if (listOfRows.get(j).charAt(i) == '#') {
                    continue;
                }
                int left = i - 1;
                int top = j - 1;
                int right = i + 1;
                int bottom = j + 1;
                Vertex main = board.getVertex(String.valueOf(j) + String.valueOf(i));
                if (left > 0 && listOfRows.get(j).charAt(left) != '#') {
                    Vertex conection = board.getVertex(String.valueOf(j) + String.valueOf(left));
                    board.addEdge(main, conection);
                }
                if (top > 0 && listOfRows.get(top).charAt(i) != '#') {
                    Vertex conection = board.getVertex(String.valueOf(top) + String.valueOf(i));
                    board.addEdge(main, conection);
                }
                if (right < m && listOfRows.get(j).charAt(right) != '#') {
                    Vertex conection = board.getVertex(String.valueOf(j) + String.valueOf(right));
                    board.addEdge(main, conection);
                }
                if (bottom < listOfRows.size() && listOfRows.get(bottom).charAt(i) != '#') {
                    Vertex conection = board.getVertex(String.valueOf(bottom) + String.valueOf(i));
                    board.addEdge(main, conection);
                }
            }
        }
    }

    static class Vertex {
        String value;
        Boolean isBombed;
        Boolean isExit;
        Boolean isStart;
        Vertex (String value, Boolean isBombed, Boolean isExit, Boolean isStart) {
            this.value = value;
            this.isBombed = isBombed;
            this.isExit = isExit;
            this.isStart = isStart;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Vertex)) {
                return false;
            }
            Vertex other = (Vertex) o;
            return (other == null && this == null) || (other.value.equals(this.value) && other.isBombed == this.isBombed && other.isExit == this.isExit && other.isStart == this.isStart);
        }

        @Override
        public final int hashCode() {
            int result = 17;
            if (value != null) {
                result = 31 * result + value.hashCode();
                result = 31 * result + isBombed.hashCode();
                result = 31 * result + isExit.hashCode();
                result = 31 * result + isStart.hashCode();
            }
            return result;
        }
    }

    static class Graph {
        Map<Vertex, List<Vertex>> adjMap;
        Graph() {
            adjMap = new HashMap<>();
        }

        public void addVertices(Vertex vertex) {
            adjMap.putIfAbsent(vertex, new ArrayList<>());
        }

        public void removeVertices(Vertex vertex) {
            adjMap.values().stream().forEach(element -> element.remove(vertex));
            adjMap.remove(vertex);
        }

        public void addEdge(Vertex v1, Vertex v2) {
            if ((!adjMap.containsKey(v1) || !adjMap.containsKey(v2)) || (adjMap.get(v1).contains(v2) && adjMap.get(v2).contains(v1)) || v1.equals(v2)) {
                return;
            }
            adjMap.get(v1).add(v2);
            adjMap.get(v2).add(v1);
        }

        public void removeEdge(Vertex v1, Vertex v2) {
            if (!adjMap.containsKey(v1) || !adjMap.containsKey(v2) || !adjMap.get(v1).contains(v2) || !adjMap.get(v2).contains(v1)) {
                return;
            }
            adjMap.get(v1).remove(v2);
            adjMap.get(v2).remove(v1);
        }

        public Vertex getVertex(String inputKey) {
            for (Vertex key : adjMap.keySet()) {
                if (key.value.equals(inputKey)) {
                    return key;
                }
            }
            return null;
        }

        public void printGraph() {
            for (Vertex key : adjMap.keySet()) {
                System.out.print("{" + key.value + "=");
                adjMap.get(key).forEach(e -> System.out.print(e.value + " "));
                System.out.print("}");
                System.out.println();
            }
        }
    }
}

