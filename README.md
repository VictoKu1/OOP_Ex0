# OOP Course Ex0 Solution . 

@author Victor Kushnir.

Assignment Ex0 is representing an undirected graph data structure.
The project contains three classes, where each one of them is representing a different level in an undirected graph data structure.
The main data structure in this project is a HashMap which was mainly used to achieve O(1) complexity for searching for nodes. 
By the terms of the assigment - method .values() of HashMap class should be considered as O(1) complexity . 

The Graph_Algo class is mainly based on the BFS algorithm, mainly used to find the shortest distance between two nodes and a HashMap to work as an isomorphism function from one group of nodes to another and from one group of edges to another - this type of HashMap implementation is mainly used in .copy() method, for more information please read the comments for the function listed in this document in the relevant section or in the code itself. 


## NodeData:

Class NodeData is implementing node_data_p interface which is extending the original noda_data interface with one method that should be used in the implementation of the class. 
This class represents a single node in a graph with its characteristics. 




    NodeData class contained parameters :
        
          * counter - static parameter of type int, counting every creation of the object of type NodeData, mainly used to give every node its special unique key at its creation.
          * key -  integer, which represents each node unique personal key.
          * info - string, which represents remark (metadata) associated with this node.
          * tag - integer, which represents temporal data (aka color: e,g, white, gray, black).
          * connected - HashMap which contains all the connections of this node, used as the primal data structure for holding these node connections.

    NodeData class methods :
   
        public NodeData() ;     - (Constructor for a object of a NodeData type ) .

        public int getKey();     - (This method returns a collection with all the Neighbor nodes of this node_data). 

        public Collection<node_data> getNi() ;   - ( This method returns a Collection interface implementing data structures with all the Neighbor nodes of this node_data , method is using HashMap .values() method which was told to consider as O(1) complexity method ) .

        public boolean hasNi(int key);     - ( Return true iff this<==>key are adjacent, as an edge between them. The method uses a helper function contains which provides boolean information about node_data implementing object presence. The method uses a boolean method containsKey(int key) in the HashMap data structure ). 

       public void addNi(node_data t) ;   - ( This method adds the node_data (t) to this node_data.  And adds this node_data to t , using implemented from node_data_p inteface method getHashMap() ) . 

       public void removeNode(node_data node);    - ( Removes the edge this-key from both ends of the bond . This method is using implemented from node_data_p interface method getHashMap() to avoid StackOverflow at the redirection phase  ).

       public String getInfo() ;   - ( Returns the remark (meta data) associated with this node.)

       public void setInfo(String s);   - ( Allows changing the remark (metadata) associated with this node). 

       public int getTag();     - ( Temporal data (aka color: e,g, white, gray, black) which can be used be algorithms) .

       public void setTag(int t);  -  ( Allow setting the "tag" value for temporal marking a node - a common practice for marking by algorithms. t - the new value of the tag ).

        public HashMap<Integer, node_data> getHashMap() ;     - ( Returns HashMap which contains links to connected nodes . This method is implementing node_data_p inteface ) .


## Graph_DS:

Class Graph_DS is implementing graph interface 
This class represents an undirected graph that contains nodes of a NodeData type.  

    Graph_DS class contained parameters :

         *  nodeHash - HashMap data structure, mainly used to contain every node_data in the graph and find it in O(1) complexity.
         *  numOfChanges - Parameter of type int, mainly represents a number of activations of methods (like adding a node/deleting a node/deleting an edge with a node) on this graph.
         *  numOfNodes - Parameter of type int, mainly represent a number of nodes this specific graph contains.
         *  numOfEdges - Parameter of type int, mainly represents a number of edges between the nodes in this graph.

    Graph_DS class methods :
    
        public Graph_DS() ;   -( Constructor of an object of Graph_DS type ) .

        public node_data getNode(int key) ;  - ( Return the node_data by the node_id, searches nodeHash by the entered key number. Time complexity: O(1) . Returns the node_data by the node_id, null if none ) .

        public boolean hasEdge(int node1, int node2) ;    - ( Returns true iff (if and only if) there is an edge between node1 and node2. Time complexity: O(1) ) .

        public void addNode(node_data n) ;     - ( Adds a new node to the graph with the given node_data and updates the numOfChanges and numOfNodes parameters. Time complexity: O(1). This method uses nodeHash data structure to achieve requested O(1) time complexity. In case the graph already contains the given node_data, the method stops ).

        public void connect(int node1, int node2) ;   - ( Connect an edge between node1 and node2and updates the numOfChanges and numOfEdges parameters. Time complexity: O(1). If the edge node1-node2 already exists - the method simply does nothing ) .

        public Collection<node_data> getV() ;    - ( This method return a pointer (shallow copy) for the collection representing all the nodes in the graph. Time complexity: O(1) - as a result of using HashMaps .values() method which was told to be considered as O(1) time complexity ). 

       public Collection<node_data> getV(int node_id) ;  - ( This method return a collection which represents all the nodes connected to node_id.  Time complexity: O(1) ) . 

       public node_data removeNode(int key) ;    - ( Deletes the node (with the given ID) from the graph -  and removes all edges using removeEdge(int node1, int node2) method which starts or ends at this node and updates the numOfChanges and numOfNodes parameters and returns the removed node .Time complexity: O(n)  ) . 

       public void removeEdge(int node1, int node2) ;   -(Deletes an edge from the graph and updates the numOfChanges and numOfEdges parameters. In case there is no edge between the given nodes or in case one of them (or both) is(/are) not contained in this graph the method simply does nothing. Time complexity: O(1) ) .

       public int nodeSize() ;  - ( Returns the number of vertices (nodes) in the graph. Time complexity: O(1) ) .

       public int edgeSize() ;  - ( Returns the number of edges (undirectional graph). Time complexity: O(1) ) . 

       public int getMC();   -( Returns the Mode Count - for testing changes in the graph. Any change in the inner state of the graph should cause an increment in the ModeCount Time complexity: O(1) ).



## Graph_Algo:

Class Graph_Algo is implementing the graph_algorithms interface.
This class represents the "regular" Graph Theory algorithms, which can be implemented on a Graph_DS object type which contains NodeData type of objects. 
    
    Graph_Algo class contained parameters :
      
         * g - Graph_DS object which is implementing graph interface. 

    Graph_Algo class methods :
    
        public Graph_Algo();    - ( Constructor of an object of Graph_Algo type ). 

       public void init(graph g);  - ( Init the graph on which this set of algorithms operates.  Making the graph that is being contained as a parameter in this object to hold a pointer to the inputted graph g ). 

       public graph copy();  - ( Returns a deep copy of this graph, including new nodes which are isomorphically equivalent to the contained graph nodes but with new unique keys. The isomorphism is being implemented using the HashMap data structure, s.t. the key is the node in the original graph and the value is the pointer to the node in the new deep copied graph. The edges are being copied as same as the nodes - using isomorphism implemented my a HashMap data structure ).

       public boolean isConnected();  - ( Returns true if and only if (iff) there is a valid path from EVREY node to each other node. The method uses BFSFromNode(int src) method to try to pass all over the nodes and mark its Info parameter to "V",  and afterward check if the nodes in the graph have changed their Info parameter to "V" using allTheInfosAreV() helper checking method. The method is using the Iterator interface ).

       private boolean allTheInfosAreV();  - ( Privately accessed helper method, used to check if all nodes in the graph changed their Info parameter to "V" as a mark of being checked in the BFSFromNode(int src) algorithm to know if there is a path from every node to every other node ).

       public int shortestPathDist(int src, int dest);   - ( Returns the length of the shortest path between src to des if no such path --> returns -1. A method is using BFSFromNode(int src) to pass over the graph staring in the node which key is src, after finishing the BFS scan the method retrieves and returns the Tag parameter from the node which key is dest and changing all the nodes Tags and Infos parameters to default ("") and (-1) ).
       
       private void BFSFromNode(int src);    - ( Helper method that is implementing BFS algorithm from a node which key is src, using LinkedList data structure as a Queue data structure  ). 

       private void adjacentsEnque(LinkedList<node_data> nodeQueue, Collection<node_data> nodeArray) ;    - ( Helper method that is used in BFSFromNode(int src) method  to add the nodeArray contained parameters to nodeQueue, the adding is produced using Iterator interface ) .

       private void setTagAndInfoOfEveryNodeInGraphToDefault() ;    - ( Helper method that is used after the BFS algorithm to set all Node's Infos and Tags parameters to default ("") and (-1) ) .

       private void setTagAndInfo(node_data n) ;   - ( Helper method to a helper method that sets all Node's Infos and Tags parameters to default ("") and (-1)  ) .

      public List<node_data> shortestPath(int src, int dest) ;   - (  Returns the the shortest path between src to dest - as an ordered List of nodes: src--> n1-->n2-->...dest, see: https://en.wikipedia.org/wiki/Shortest_path_problem, ( Note if no such path --> returns null; ) Uses two helping methods , first the method is using the BFS marking algorithm but as a source node it uses the destination node to determine the distances of each node in the graph from the destination node , after that the method is recursively building a path to the destination node each time searching for the node which distance is lower in 1 that his own distance (each node parameter tag is used to represent a distance) after the method finishing building a path ,it's turning all node's tags and infos parameters to it's default values [ int tag=(-1) ] and [String info="" ] .

       public List<node_data> shortestPathHLPR(int src, int dest, List<node_data> nodeList) ;    - (Helping method that is recursively building the shortest path to the destination node , using a method which is giving us the adjacent node with a distance to the destination node  lower in one than current node distance .).
    
      public node_data closestAdjacent(Collection<node_data> adjacents, int prevDist) ;      - ( Helping method which finds adjacent node which is leading using the shortest way to the destination node . Knowing the distanse of the node that gave this adjacents collection we can know for sure that the tag parameter of the node we are searching for is for sure lower in 1 that its "parent node" .).
        
