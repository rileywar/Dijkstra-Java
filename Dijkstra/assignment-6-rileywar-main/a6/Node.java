package a6;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public interface Node {

     /**
      * Returns the name of the node.
      *
      * @return the name of the node
      */
     String getName();

     /**
      * Returns the list of edges leaving the node.
      *
      * @return the list of edges leaving the node
      */
     List<Edge> getEdges();

     Set<Node> getNeighbors();

     /**
      * Returns true if there is an edge between the current node and the given node,
      * false otherwise.
      *
      * @param node the node to check for an edge
      * @return true if there is an edge between the current node and the given node,
      *         false otherwise
      */
     boolean hasEdgeTo(Node node);


     /**
      * Deletes the edge from the current node to the given node, if it exists.
      *
      * @param node the node to delete the edge to
      * @return true if the edge was deleted, false otherwise
      */
     boolean deleteEdgeTo(Node node);

     void addEdge(Node destination, double weight);


      double distance();
      boolean visited();
      Node predecessor();

      double getDistance();
      void setDistance(double distance);

      boolean isVisited();

      void setVisited(boolean visited);

      Node getPredecessor();

      void setPredecessor(Node predecessor);



}

