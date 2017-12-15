Tom Nguyen
2/21/17
CSE 373
TA: Raquel Van Hofwegen
HW #4

1) 
   edgeCost = O(|V| + |E|)

   The asymptotic runtime of my implementation of adjacentVertices is O(|V| + |E|). This was determined by examining the "runtime-meaningful" code following way: the containsKey() on the HashMap would be O(|V|) because it has to check every single key in the case that the hashing of the keys went to the same bucket in the hash table, then the for each loop iterates over one of the lists of Edges in the Map values but it is possible that all the edges lie in this list so this operation will be O(|E|). For graphs, you add these runtimes together to get the overall runtime and in this case you would get O(|V| + |E|).



   The runtime of my implementation of edgeCost is O(|V| + |E|). This was determined by examining the "runtime-meaningful" code following way: both of the containsKey() calls will be O(|V|) for the same reason for the situation in adjacentVertices (the coefficient of 2 can be neglected like in typical asymptotic runtime analysis), then again, just like in the adjacentVertices, the for each loop iterates over a key’s value of a list of edges but all the edges could be in that list so the runtime would be O(|E|). Add these runtimes together to get an overall runtime of O(|V| + |E|).

2) On top of encapsulating my fields, in order to preserve the abstraction of my Graph implementation utilizes deep copying and returning aliases. If you deep copy all the inputs for your object and return new copies with new references of your object's data, then there will be no way for the client to inadvertently (or advertently) mess with the abstraction and data stored in the object without a method that you implemented. This is because they will never have references to the data that is stored in the object. Deep copying is essentially protecting your object's references. Therefore, if the client edits what the object returned to them, then they will be editing their copy rather than the copy that is stored in the object. Also, making the contents/data of the object immutable will in a sense be redundant in preserving the abstraction. However, if you did make the data immutable, then in a sense, it does add another layer of protection because maybe you forget that there is a case in some of your code that allows the change of the data fields and if those fields were not immutable then the client would be able to tinker with the data outside of your object. I did not edit the code for the Edge and Vertex classes but it does seem that the fields were made immutable. 

3) Above all else, I tested that the functionality of my Graph implementation was doing what a cost-directed graph does (adding vertices/edges, tested the costs of an edge, etc.) Also, I tested the edge cases of my code by making sure that exception errors were being thrown when they needed to be thrown (when invalid arguments were made). Also, I tested to make sure that the abstraction of the graph was being preserved. To do this, I tried editing references to data in my Graph and editing the data that was returned to me (the client) and seeing if the edits were reflective in the Graph’s data.

4) N/A