package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/*
 * Class implementing graph interface and contains 3 variables and 2 data structures .
 * Each object of a type Graph_DS contains :
 *  nodeHash - HashMap data structure, mainly used to contain every node_data in the graph and find it in O(1) complexity .
 *  numOfChanges - Parameter of a type int, mainly represents a number of activations of methods (like: adding a node/deleting a node/deleting an edge with a node) on this graph .
 *  numOfNodes - Parameter of a type int, mainly represent a number of nodes this specific graph contain .
 *  numOfEdges - Parameter of a tye int, mainly represents a number of edges between the nodes in this graph .*
 *
 *  @author Victor Kushnir .
 */

public class Graph_DS implements graph {
    private HashMap<Integer, node_data> nodeHash;
    private int numOfChanges;
    private int numOfEdges;
    private int numOfNodes;

    /*
     *Constructor of an object of Graph_DS type .
     */
    public Graph_DS() {
        nodeHash = new HashMap<Integer, node_data>();
        numOfChanges = 0;
        numOfEdges = 0;
        numOfNodes = 0;
    }

    /*
     * Return the node_data by the node_id,
     * Searches nodeHash by the entered key number.
     * Time complexity: O(1) .
     * returns the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (nodeHash.containsKey(key)) {
            return nodeHash.get(key);
        }
        return null;
    }

    /*
     * Returns true iff (if and only if) there is an edge between node1 and node2
     * Time complexity: O(1) .
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (this.getNode(node1) == null || this.getNode(node2) == null) {
            return false;
        }
        return getNode(node1).hasNi(node2);
    }

    /**
     * Adds a new node to the graph with the given node_data and updates the numOfChanges and numOfNodes parameters.
     * Time complexity: O(1) .
     * This method uses nodeHash data structure to achieve requested O(1) time complexity.
     * In case the graph already contains the given node_data, the method stops .
     */
    @Override
    public void addNode(node_data n) {
        if (nodeHash.containsKey(n.getKey())) {
            return;
        }
        nodeHash.put(n.getKey(), n);
        numOfChanges++;
        numOfNodes++;
    }

    /*
     * Connect an edge between node1 and node2and updates the numOfChanges and numOfEdges parameters.
     * Time complexity: O(1) .
     * If the edge node1-node2 already exists - the method simply does nothing.
     */
    @Override
    public void connect(int node1, int node2) {
        if (!nodeHash.containsKey(node1) || !nodeHash.containsKey(node2) || this.hasEdge(node1, node2) || node1 == node2) {
            return;
        }
        getNode(node1).addNi(getNode(node2));
        numOfChanges++;
        numOfEdges++;
    }

    /*
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * Time complexity: O(1) - as a result of using .values() method of HashMap class
     * which returns a Collection type data structure .
     */
    @Override
    public Collection<node_data> getV() {
        return nodeHash.values();
    }

    /*
     * This method return a collection which represents all the nodes connected to node_id .
     * Time complexity: O(1) .
     */
    @Override
    public Collection<node_data> getV(int node_id) {
        if (nodeHash.containsKey(node_id)) {
            return getNode(node_id).getNi();
        }
        System.out.println("Error : There is no node with id :" + node_id + " in this graph.");
        return null;
    }

    /*
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges using removeEdge(int node1, int node2)
     * method which starts or ends at this node and
     * updates the numOfChanges and numOfNodes parameters and returns the removed node .
     * Time complexity: O(n) .
     */
    @Override
    public node_data removeNode(int key) {
        if (getNode(key) == null) {
            return null;
        }
        List<node_data> list = new ArrayList<node_data>(getNode(key).getNi());
        for (int i = 0; i < list.size(); i++) {
            removeEdge(key, list.get(i).getKey());
        }
        numOfChanges++;
        numOfNodes--;
        return nodeHash.remove(key);
    }

    /**
     * Deletes an edge from the graph and updates the numOfChanges and numOfEdges parameters.
     * In case there is no edge between the given nodes or in case one of them
     * (or both) is(/are) not contained in this graph the method simply does nothing .
     * Time complexity: O(1) .
     */
    @Override
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {
            getNode(node1).removeNode( getNode(node2));
            numOfEdges--;
            numOfChanges++;
        }
    }

    /**
     * Returns the number of vertices (nodes) in the graph.
     * Time complexity: O(1) .
     */
    @Override
    public int nodeSize() {
        return numOfNodes;
    }

    /**
     * Returns the number of edges (undirectional graph).
     * Time complexity: O(1) .
     */
    @Override
    public int edgeSize() {
        return numOfEdges;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     * Time complexity: O(1) .
     */
    @Override
    public int getMC() {
        return numOfChanges;
    }
}
