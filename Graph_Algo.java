package ex0;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/*
 * Class implementing graph_algorithms interface and contains 1 object which implements graph interface .
 * This class represents the "regular" Graph Theory algorithms .
 *
 * @author Victor Kushnir .
 */
public class Graph_Algo implements graph_algorithms {
    graph g;

    /*
    Constructor of an object of Graph_Algo type .
    */
    public Graph_Algo() {
        this.g = new Graph_DS();
    }

    /*
     * Init the graph on which this set of algorithms operates on.
     * Making the graph that is being contained as a parameter in this object to hold a pointer to the inputted graph g.
     */
    @Override
    public void init(graph g) {
        this.g = g;
    }

    /*
     * Returns a deep copy of this graph , including new nodes which are
     * isomorphically equivalent to the contained graph nodes but with new unique keys .
     * The isomorphism is being implemented using HashMap data structure , s.t. the key
     * is the node in the original graph and the value is the pointer to the node in the new deep copied graph .
     * The edges are being copied as same as the nodes - using isomorphism implemented my a HashMap data structure .
     */
    @Override
    public graph copy() {
        graph g1 = new Graph_DS();
        node_data[] ndar1 = this.g.getV().toArray(new NodeData[g1.getV().size()]);
        HashMap<node_data, node_data> isomorphism = new HashMap<node_data, node_data>();
        for (int i = 0; i < ndar1.length; i++) {
            node_data n = new NodeData();
            g1.addNode(n);
            isomorphism.put(ndar1[i], n);
        }
        for (int j = 0; j < ndar1.length; j++) {
            Iterator<node_data> itr = ndar1[j].getNi().iterator();
            while (itr.hasNext()) {
                if (g1.edgeSize() == this.g.edgeSize()) {
                    return g1;
                }
                g1.connect((isomorphism.get(ndar1[j])).getKey(), (isomorphism.get(itr.next())).getKey());
            }
        }
        return g1;
    }

    /*
     * Returns true if and only if (iff) there is a valid path from EVREY node to each other node.
     * The method uses BFSFromNode(int src) method to try to pass all over the nodes
     * and mark its Info parameter to "V" , and afterwards check if the nodes
     * in the graph have changed their Info parameter to "V" using allTheInfosAreV() helper checking method .
     * The method is using Iterator interface .
     */
    @Override
    public boolean isConnected() {
        if (this.g.nodeSize() == 0) {
            return true;
        }
        boolean answer = false;
        if (this.g.getV().iterator().hasNext()) {
            BFSFromNode(this.g.getV().iterator().next().getKey());
            answer = allTheInfosAreV();
            setTagAndInfoOfEveryNodeInGraphToDefault();
        }
        return answer;
    }

    /*
     * Privately accessed helper method , used to check if all nodes
     * in the graph changed their Info parameter to "V" as a mark of being
     * checked in the BFSFromNode(int src) algorithm to know
     * if there is a path from every node to every other node .
     */
    private boolean allTheInfosAreV() {
        Iterator<node_data> it1 = this.g.getV().iterator();
        while (it1.hasNext()) {
            if (it1.next().getInfo().equals("")) {
                return false;
            }
        }
        return true;
    }

    /*
     * Returns the length of the shortest path between src to des, if no such path --> returns -1 .
     * Method is using BFSFromNode(int src) to pass over the graph
     * staring in the node which key is src, after finishing the BFS scan
     * the method retrieves and returns the Tag parameter from the node
     * which key is dest and changing all the nodes Tags and Infos
     * parameters to default ("") and (-1) .
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        if (src == dest) {
            return 0;
        }
        if (this.g.getNode(src) != null && this.g.getNode(dest) != null) {
            BFSFromNode(src);
        } else {
            return -1;
        }
        int returningValue = this.g.getNode(dest).getTag();
        setTagAndInfoOfEveryNodeInGraphToDefault();
        return returningValue;
    }

    /*
     * Helper method that is implementing BFS algorithm
     * from a node which key is src , using LinkedList data structure
     *  as a Queue data structure .
     */
    private void BFSFromNode(int src) {
        int dist = 0;
        LinkedList<node_data> nodeQueue = new LinkedList<node_data>();
        nodeQueue.add(this.g.getNode(src));
        int containsOnThisLevel = 1;
        int added = 0;
        while (!nodeQueue.isEmpty()) {
            if (containsOnThisLevel == 0) {
                dist++;
                containsOnThisLevel = added;
                added = 0;
            }
            if (nodeQueue.peek().getInfo().equals("")) {
                nodeQueue.peek().setInfo("V");
                nodeQueue.peek().setTag(dist);
                added = added + nodeQueue.peek().getNi().size();
                adjacentsEnque(nodeQueue, nodeQueue.peek().getNi());
            }
            nodeQueue.pop();
            containsOnThisLevel--;
        }
    }

    /*
     * Helper method that is used in BFSFromNode(int src) method
     * to add the nodeArray contained parameters to nodeQueue,
     * the adding is produced using Iterator interface .
     */
    private void adjacentsEnque(LinkedList<node_data> nodeQueue, Collection<node_data> nodeArray) {
        Iterator<node_data> itr = nodeArray.iterator();
        while (itr.hasNext()) {
            nodeQueue.add(itr.next());
        }
    }

    /*
     * Helper method that is used after the BFS algorithm to set all Node's Infos and Tags parameters to default ("") and (-1) .
     */
    private void setTagAndInfoOfEveryNodeInGraphToDefault() {
        Iterator<node_data> itr = this.g.getV().iterator();
        while (itr.hasNext()) {
            setTagAndInfo(itr.next());
        }
    }

    /*
     * Helper method to a helper method that sets all Node's Infos and Tags parameters to default ("") and (-1) .
     */
    private void setTagAndInfo(node_data n) {
        n.setTag(-1);
        n.setInfo("");
    }

    /*
     * Returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * Uses two helping methods , first the method is using the BFS marking algorithm but as a source node it uses the destination node
     * to determine the distances of each node in the graph from the destination node , after that the method
     * is recursively building a path to the destination node each time searching for the node which distance
     * is lower in 1 that his own distance (each node parameter tag is used to represent a distance)
     * after the method finishing building a path ,it's turning all node's tags and infos parameters to it's default values [ int tag=(-1) ] and [ String info="" ] .
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        int dist = shortestPathDist(src, dest);
        if (dist == -1) {
            return null;
        }
        List<node_data> nodeList = new ArrayList<node_data>();
        nodeList.add(this.g.getNode(src));
        if (dist == 0) {
            return nodeList;
        }
        BFSFromNode(dest);
        List<node_data> nodeList1 = shortestPathHLPR(closestAdjacent(this.g.getV(src), this.g.getNode(src).getTag()).getKey(), dest, nodeList);
        setTagAndInfoOfEveryNodeInGraphToDefault();
        return nodeList1;
    }

    /*
     * Helping method that is recursively building the shortest path to the destination node ,
     * using a method which is giving us the adjacent node with a distance to the destination node
     * lower in one than current node distance .
     */
    public List<node_data> shortestPathHLPR(int src, int dest, List<node_data> nodeList) {
        if (src == dest) {
            nodeList.add(this.g.getNode(dest));
            return nodeList;
        }
        nodeList.add(this.g.getNode(src));
        node_data n = closestAdjacent(this.g.getV(src), this.g.getNode(src).getTag());
        return shortestPathHLPR(n.getKey(), dest, nodeList);

    }

    /*
     * Helping method which finds adjacent node which is leading using the shortest way to the destination node .
     * Knowing the distanse of the node that gave this adjacents collection we can know for sure
     *  that the tag parameter of the node we are searching for is for sure lower in 1 that its "parent node" .
     */
    public node_data closestAdjacent(Collection<node_data> adjacents, int prevDist) {
        Iterator<node_data> itr = adjacents.iterator();
        while (itr.hasNext()) {
            node_data n = itr.next();
            if (n.getTag() == (prevDist - 1)) {
                return n;
            }
        }
        return null;
    }

}
