/*
 * AUTHOR: David Anderson
 * FILE: PA11Main.java
 * ASSIGNMENT: Programming Assignment 11 - Travelling Salesman
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This program performs three different graph traversal methods to find path in which
 * a salesman could visit every city (node on the graph) only once. The user can choose between
 * heuristic, recursive backtracking, or my own customized recursive algorithm. The heuristic option
 * aims for efficiency only, finding some path quickly but not necessarily finding the optimal path.
 * The recursive backtracking approach guarantees an optimal path, but will take quite a long time
 * to do so if the number of cities is large (complexity of O(N!)). My algorithm uses recursive
 * backtracking with "pruning", eliminating a branch as soon as it exceeds the current minimum
 * trip total.
 *
 * When the user uses the TIME command, the program runs all three strategies back-to-back, and outputs
 * the cost of the path and the time, in milliseconds, it took to complete that method.
 *
 * AVAILABLE COMMANDS:
 * HEURISTIC, BACKTRACK, MINE, TIME
 *
 * USAGE:
 * java PA11Main inputfile COMMAND
 *
 * EXAMPLE INPUT: example.mtx
 * ----------------------------------------------------------
 * %%MatrixMarket matrix coordinate real general
 * 3 3 6
 * 1 2 1.0
 * 2 1 2.0
 * 1 3 3.0
 * 3 1 4.0
 * 2 3 5.0
 * 3 2 6.0
 * ----------------------------------------------------------
 *
 * The program assumes that the input will be properly formatted and that the
 * input files are located in the working directory.
 *
 */

RESULTS: Timing experiments for input file big11.mtx:
-----------------------------------------------------
heuristic: cost = 3.4, 1 milliseconds <br>
mine: cost = 1.4, 72 milliseconds <br>
backtrack: cost = 1.4, 9452 milliseconds <br>
-----------------------------------------------------

EXPLANATION:
The reason for the time differences between the three traversal approaches has to do with the decisions made in each algorithm. The heuristic, or greedy approach, simply chooses the next shortest edge at each node (assuming that neighbor hasn't been visited) in the hopes that it reaches an optimal path. This leads to a highly efficient run-time, but by no means guarantees the shortest path. Recursive backtracking, alternatively, searches every possible permutation of the vertices and keeps track of the minimum distance. It therefore guarantees the optimal path, but with a Big-O complexity of O(N!) it becomes impractical as the number of nodes gets large. My approach uses recursive backtracking but adds in some stipulations for "pruning" the decision tree so the search space becomes manageable. I added this conditional statement to my code:

if (trip.tripCost(dGraph) < currentMin) {
                    myAlgorithmHelper(dGraph, visitOrder, trip, numCities, shortestTrip, count + 1);
                }

By storing the current shortest path length and comparing all subsequent trips with it, my algorithm immediately aborts a path as soon as the trip total exceeds the current minimum distance. It does so because we know it can't possibly be the shortest path at that point. It still must search a large space, but clearly a huge number of decisions have been removed since my run-time is substantially faster than the traditional recursive approach.
