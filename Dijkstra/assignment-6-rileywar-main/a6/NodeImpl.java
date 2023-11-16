package a6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NodeImpl implements Node {
    private String name;
    private List<Edge> edges;
    private Set<Node> neighbors;
    private double distance;
    private boolean visited;
    private Node predecessor;

    public NodeImpl(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.neighbors = new HashSet<>();
        this.distance = Double.MAX_VALUE;
        this.visited = false;
        this.predecessor = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean hasEdgeTo(Node node) {
        for (Edge edge : edges) {
            if (edge.getDestination().equals(node)) {
                return true;
            }
        }
        return false;
    }

    public Set<Node> getNeighbors() {
        return new HashSet<>(this.neighbors);
    }

    @Override
    public boolean deleteEdgeTo(Node node) {
        for (Edge edge : edges) {
            if (edge.getDestination().equals(node)) {
                edges.remove(edge);
                return true;
            }
        }
        return false;
    }


    public void addEdge(Node destination, double weight) {
        edges.add(new EdgeImpl(this, destination, weight));
    }

    @Override
    public double distance() {
        return 0;
    }

    @Override
    public boolean visited() {
        return false;
    }

    @Override
    public Node predecessor() {
        return null;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
}


