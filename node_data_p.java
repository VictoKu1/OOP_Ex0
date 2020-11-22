package ex0;

import java.util.HashMap;
/*
* An interface created for adding helping methods to NodeData object .
*
* @author Victor Kushnir .
 */
public interface node_data_p extends node_data {

    /**
     * Returns HashMap which contains links to connected nodes   .
     **/
    public HashMap<Integer, node_data> getHashMap();

}
