
RESULTS: Timing experiments for input file big11.mtx:
-----------------------------------------------------
heuristic: cost = 3.4, 1 milliseconds
mine: cost = 1.4, 72 milliseconds
backtrack: cost = 1.4, 9452 milliseconds
-----------------------------------------------------

EXPLANATION:
The reason for the time differences between the three traversal approaches has to do with the decisions made in each algorithm. The heuristic, or greedy approach, simply chooses the next shortest edge at each node (assuming that neighbor hasn't been visited) in the hopes that it reaches an optimal path. This leads to a highly efficient run-time, but by no means guarantees the shortest path. Recursive backtracking, alternatively, searches every possible permutation of the vertices and keeps track of the minimum distance. It therefore guarantees the optimal path, but with a Big-O complexity of O(N!) it becomes impractical as the number of nodes gets large. My approach uses recursive backtracking but adds in some stipulations for "pruning" the decision tree so the search space becomes manageable. I added this conditional statement to my code:

if (trip.tripCost(dGraph) < currentMin) {
                    myAlgorithmHelper(dGraph, visitOrder, trip, numCities, shortestTrip, count + 1);
                }

By storing the current shortest path length and comparing all subsequent trips with it, my algorithm immediately aborts a path as soon as the trip total exceeds the current minimum distance. It does so because we know it can't possibly be the shortest path at that point. It still must search a large space, but clearly a huge number of decisions have been removed since my run-time is substantially faster than the traditional recursive approach.