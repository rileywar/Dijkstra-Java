package a6;

import java.util.HashMap;
import java.util.Map;


import java.util.*;

public class GraphImpl implements Graph {


    Map<String, Node> nodes; //Do not delete.  Use this field to store your nodes.
    // key: name of node. value: a5.Node object associated with name
    //
    private List<Edge> edges;
    private Stack<String> s;

    public GraphImpl() {
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public boolean addNode(String name) {
        if (name == null) return false;
        if (nodes.containsKey(name)) return false;
        nodes.put(name, new NodeImpl(name));
        return true;
    }

    @Override
    public boolean addEdge(String src, String dest, double weight) {
        Node source = nodes.get(src);
        Node destination = nodes.get(dest);

        if (weight < 0.0)
            return false;
        if (source == null || destination == null) {
            return false;
        }

        for (Edge edge : source.getEdges()) {
            if (edge.getDestination().getName().equals(dest)) {
                return false;
            }
        }

        source.addEdge(destination, weight);
        return true;
    }

    @Override
    public boolean deleteNode(String name) {
        boolean nodeDeleted = false;
        Node nodeToDelete = nodes.get(name);

        if (name == null || name.isEmpty()) {
            return false;
        }
        if (nodeToDelete == null) {
            return false;
        }

        if (nodeToDelete != null) {
            // Remove edges pointing to the node that we want to delete
            for (Node node : nodes.values()) {
                node.deleteEdgeTo(nodeToDelete);
            }

            // Remove the node from the map of nodes
            nodes.remove(name);
            nodeDeleted = true;
        }

        return nodeDeleted;
    }

    @Override
    public boolean deleteEdge(String src, String dest) {
        Node sourceNode = nodes.get(src);
        Node destNode = nodes.get(dest);
        if (src == null || src.isEmpty() || dest == null || dest.isEmpty()) {
            return false;
        }
        if (sourceNode == null || destNode == null) {
            return false;
        }

        // Find the edge to delete
        Edge edgeToDelete = null;
        for (Edge edge : sourceNode.getEdges()) {
            if (edge.getDestination() == destNode) {
                edgeToDelete = edge;
                break;
            }
        }

        if (edgeToDelete == null) {
            return false;
        } else {
            // Remove the edge from the source node's list of edges
            Node desttNode = edgeToDelete.getDestination();
            sourceNode.deleteEdgeTo(desttNode);
            return true;
        }
    }

    @Override
    public int numNodes() {
        return nodes.size();
    }

    @Override
    public int numEdges() {
        int numEdges = 0;
        for (Node node : nodes.values()) {
            numEdges += node.getEdges().size();
        }
        return numEdges;
    }

    @Override
    public Stack<String> topoSort() {
        Stack<String> topoOrder = new Stack<>();

        if (nodes.isEmpty()) {
            return topoOrder;
        }

        Map<Node, Integer> inDegree = new HashMap<>();
        for (Node node : nodes.values()) {
            inDegree.put(node, 0);
        }
        for (Node node : nodes.values()) {
            for (Edge edge : node.getEdges()) {
                inDegree.put(edge.getDestination(), inDegree.get(edge.getDestination()) + 1);
            }
        }

        Queue<Node> queue = new LinkedList<>();
        for (Node node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            topoOrder.push(currNode.getName());

            for (Edge edge : currNode.getEdges()) {
                Node destNode = edge.getDestination();
                inDegree.put(destNode, inDegree.get(destNode) - 1);

                if (inDegree.get(destNode) == 0) {
                    queue.add(destNode);
                }
            }
        }

        if (topoOrder.size() != nodes.size()) {
            return null;
        }

        return topoOrder;
    }


    public void printGraph() {
        for (Node node : nodes.values()) {
            System.out.print(node.getName() + " -> ");
            for (Edge edge : node.getEdges()) {
                System.out.print(edge.getDestination().getName() + " ");
            }
            System.out.println();
        }
    }


    @Override
    public Map<String, Double> dijkstra(String start) {
        Map<String, Double> shortestDistances = new HashMap<>();

        for (Node node : nodes.values()) {
            node.setDistance(Double.POSITIVE_INFINITY);
            node.setVisited(false);
        }

        Node startNode = nodes.get(start);
        if (startNode == null) {
            throw new IllegalArgumentException("Invalid start node.");
        }
        startNode.setDistance(0.0);

        Comparator<Node> comparator = Comparator.comparingDouble(Node::getDistance);
        PriorityQueue<Node> queue = new PriorityQueue<>(comparator);

        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.isVisited()) {
                continue;
            }

            current.setVisited(true);

            for (Edge edge : current.getEdges()) {
                Node neighbor = edge.getDestination();
                if (neighbor == null) {
                    continue;
                }
                double tentativeDistance = current.getDistance() + edge.getWeight();
                if (tentativeDistance < neighbor.getDistance()) {
                    neighbor.setDistance(tentativeDistance);
                    neighbor.setPredecessor(current);

                    queue.add(neighbor);
                }
            }
        }

        for (Node node : nodes.values()) {
            if (node.getDistance() != Double.POSITIVE_INFINITY) {
                shortestDistances.put(node.getName(), node.getDistance());
            }
        }

        return shortestDistances;
    }
}
