package a6;

import java.util.Map;

public class Main {


    public static void main(String[] args) {

        //You are encouraged (but not required) to include your testing code here.

        //Hint: Try to test basic operations (e.g., adding a few nodes and edges to graphs)
        //before you implement more complex methods
        Graph graph = new GraphImpl();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("C", "E", 6);
        graph.addEdge("D", "E", 2);
        graph.addEdge("D", "F", 5);
        graph.addEdge("E", "F", 1);

        Map<String, Double> shortestDistances = graph.dijkstra("A");
        System.out.println(shortestDistances);

    }}