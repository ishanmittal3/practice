Ishan Mittal
ishan.mittal@orbitz.com

Solution summary:
The given data is represented as a directed weighted graph.

- The first task uses Dijkstra's algorithm to find the shortest path between two nodes. 
Time complexity:  O(n^2)
Space complexity: O(n^2) (worst case)

- The second task uses (depth-limited) breadth first search
Time complexity: O(V+E), where V = #vertices (airports), E = #edges (flights)
O(E) can vary between O(1) and O(V) depending on how sparse the input graph is.
Space complexity: O(V^2) (worst case)

- The third task is just a variation of the first task, with origin being the same as destination.
Time complexity:  O(n^2)
Space complexity: O(n^2) (worst case)


This project can be loaded into eclipse (I've included the .classpath and .project files in this directory)
src/Solution.java contains the main method, which reads resources/flights.txt and resources/tasks.txt
Output is generated in output/output.txt
