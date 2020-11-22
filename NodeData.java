package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class implements noda_data interface and contains 6 variables :
 * Parameters contained in NodeData object :
 * counter - static paremeter of type int ,counting every creation of object of type NodeData ,mainly used to give every node its own special uniqe key at it's creation .
 * key -  integer, which represents each node unique personal key .
 * info - string, which represents remark (meta data) associated with this node .
 * tag - integer, which represents temporal data (aka color: e,g, white, gray, black) .
 * connected - HashMap which contains all the connections of this node, used as primal data structure for holding this node connections .
 *
 * @author Victor Kushnir .
 **/
public class NodeData implements node_data_p {
    private static int counter = 0;
    private int key;
    private String info;
    private int tag;
    private HashMap<Integer, node_data> connected;

    /*
     * Constructor for a object of a NodeData type .
     */
    public NodeData() {
        this.key = counter + 0;
        this.counter++;
        this.info = "";
        this.tag = -1;
        this.connected = new HashMap<Integer, node_data>();
    }

    /*
     * This method returns a collection with all the Neighbor nodes of this node_data
     */
    @Override
    public int getKey() {
        return key;
    }

    /*
     * This method returns a Collection interface implementing data structures with all the Neighbor nodes of this node_data .
     * Not using HashMap .values() method to avoid O(n) complexity - which is not allowed by the requirements of this assignment .
     */
    @Override
    public Collection<node_data> getNi() {
        return connected.values();
    }

    /*
     * Return true iff this<==>key are adjacent, as an edge between them.
     * The method uses a helper function contains which provides boolean information about node_data implementing object presence .
     * The method uses a boolean method containsKey(int key) in HashMap data structure .
     */
    @Override
    public boolean hasNi(int key) {
        return connected.containsKey((Integer) key);
    }

    /*
     * This method adds the node_data (t) to this node_data.
     * and adds this node_data to t , using implemented from node_data_p inteface method getHashMap() .
     */

    @Override
    public void addNi(node_data t) {
        if (t.getKey() == this.getKey()) {
            return;
        }
        if (this.hasNi(t.getKey())) {
            return;
        }
        ((NodeData) this).getHashMap().put(((Integer) t.getKey()), t);
        ((NodeData) t).getHashMap().put(((Integer) ((NodeData) this).getKey()), this);
    }

    /*
     * Removes the edge this-key from both ends of the bond .
     * This method is using implemented from node_data_p inteface method getHashMap() to avoid stackOverFlow at the redirection phase .
     */
    @Override
    public void removeNode(node_data node) {
        connected.remove(node.getKey());
        ((NodeData) node).getHashMap().remove(this.getKey());
    }

    /*
     * Returns the remark (meta data) associated with this node.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     */
    @Override
    public void setInfo(String s) {
        this.info = s + "";
    }

    /*
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms .
     */
    @Override
    public int getTag() {
        return tag;
    }

    /*
     * Allow setting the "tag" value for temporal marking an node - common
     * practice for marking by algorithms.
     * t - the new value of the tag .
     */
    @Override
    public void setTag(int t) {
        this.tag = t + 0;
    }

    /*
     * Returns HashMap which contains links to connected nodes .
     * This method is implementing node_data_p inteface .
     */
    @Override
    public HashMap<Integer, node_data> getHashMap() {
        return this.connected;
    }


}
