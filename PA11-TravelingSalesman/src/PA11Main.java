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

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PA11Main {

    public static void main(String[] args) {
        //create dgraph object in main to send to all methods
        DGraph dGraph = null;
        try {
            dGraph = read(args[0]);

        } catch (IOException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }

        String command = args[1]; // HEURISTIC, BACKTRACK, MINE, or TIME

        int numCities = dGraph.getNumNodes();
        Trip trip = new Trip(numCities);
        List<Integer> visitOrder = new ArrayList<>();
        performCommands(command, dGraph, visitOrder, trip, numCities);

    }

    /*
     * Purpose: This method takes the passed information,
     * and calls the method associated with the given command.
     *
     * @param command, is the String containing the command to perform
     *
     * @param dGraph, is the dGraph object that was created from the file
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     */
    private static void performCommands(String command, DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities) {
        switch (command) {
            case "HEURISTIC":
                //perform heuristic travel
                heuristicTravel(dGraph, visitOrder, trip, numCities, command);
                break;
            case "BACKTRACK":
                //perform recursive backtracking
                backtrackMethod(dGraph, visitOrder, trip, numCities, command);
                break;
            case "MINE":
                //use my own style
                myAlgorithm(dGraph, visitOrder, trip, numCities, command);
                break;
            case "TIME":
                //get the processing time for all 3 types
                getTime(dGraph, visitOrder, trip, numCities, command, 0);
                break;
            default:
                System.out.println("Could not find that command");
        }
    }

    /*
     * Purpose: This method takes the passed information,
     * starts a nanosecond timer, and then performs each of the methods. It
     * outputs the time it took for each method.
     *
     * @param dGraph, is the dGraph object that was created from the file
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @param command, is the String containing the command to perform
     *
     * @param count, keeps track of which method to call next
     *
     */
    private static void getTime(DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities, String command, int count) {
        long startTime = System.nanoTime();
        switch (count) {
            case 0:
                // ... the code being measured starts ...
                Trip heuristicTrip = heuristicTravel(dGraph, visitOrder, trip, numCities, command);
                // ... the code being measured ends ...
                endTimePrintResults(heuristicTrip, startTime, count, dGraph);
                break;
            case 1:
                // ... the code being measured starts ...
                Trip myTrip = myAlgorithm(dGraph, visitOrder, trip, numCities, command);
                // ... the code being measured ends ...
                endTimePrintResults(myTrip, startTime, count, dGraph);
                break;
            case 2:
                // ... the code being measured starts ...
                Trip backtrackTrip = backtrackMethod(dGraph, visitOrder, trip, numCities, command);
                // ... the code being measured ends ...
                endTimePrintResults(backtrackTrip, startTime, count, dGraph);
                break;
            default:
                System.exit(1);
        }
        sleepPause();
        visitOrder.clear();
        trip = new Trip(numCities);
        //recursively call this method so it prints all 3 cases and then defaults to sysexit.
        getTime(dGraph, visitOrder, trip, numCities, command, count + 1);
    }

    /*
     * Purpose: This method gets called from the getTime method; it
     * ends the timer, calculates the results in milliseconds, and prints
     * the results.
     *
     * @param trip, is the trip being analyzed for cost and time.
     *
     * @param, startTime, is the long indicating when the time started
     *
     * @param count, keeps track of which method was called to output the
     * correct information.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     */
    private static void endTimePrintResults(Trip trip, long startTime, int count, DGraph dGraph) {
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        switch (count) {
            case 0:
                String heuristicCost = String.format("%.1f", trip.tripCost(dGraph));
                System.out.println("heuristic: cost = " + heuristicCost + ", " + timeElapsed / 1000000 + " milliseconds");
                break;
            case 1:
                String myMethodCost = String.format("%.1f", trip.tripCost(dGraph));
                System.out.println("mine: cost = " + myMethodCost + ", " + timeElapsed / 1000000 + " milliseconds");
                break;
            case 2:
                String backtrackCost = String.format("%.1f", trip.tripCost(dGraph));
                System.out.println("backtrack: cost = " + backtrackCost + ", " + timeElapsed / 1000000 + " milliseconds");
                break;
        }
    }

    /*
     * Purpose: This is a private helper method that performs
     * a 2 second sleep between time calls so the processes don't
     * overlap or cause an error.
     */
    private static void sleepPause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * Purpose: This method takes the passed information
     * and then performs my algorithmic approach to solving the TSP. It
     * calls a helper method.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @param command, is the String containing the command to perform
     *
     * @return bestTrip, is the shortest possible path found by the algorithm
     *
     */
    private static Trip myAlgorithm(DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities, String command) {

        trip.chooseNextCity(1);
        visitOrder.add(1);
        Trip bestTrip = myAlgorithmHelper(dGraph, visitOrder, trip, numCities, new Trip(numCities), 0);

        if (!command.equals("TIME")) {
            printResults(bestTrip, dGraph);
        }

        return bestTrip;
    }

    /*
     * Purpose: This helper method takes the passed information
     * and then performs my algorithmic approach to solving the TSP. It
     * gets called from myAlgorithm.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @param shortestTrip, is the Trip Object that will store the shortest
     * possible trip and compare it with the other trips.
     *
     * @param count, allows dGraph to call getNeighbors and get the current
     * node's neighbors.
     *
     * @return shortestTrip, is the shortest possible trip found
     *
     */
    private static Trip myAlgorithmHelper(DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities, Trip shortestTrip, int count) {

        List<Integer> neighbors = dGraph.getNeighbors(visitOrder.get(count));
        double currentMin;
        //base case - if there are no cities left
        if (trip.citiesLeft().size() == 0) {
            if (visitOrder.size() == numCities) {
                if (trip.tripCost(dGraph) < shortestTrip.tripCost(dGraph)) {
                    shortestTrip.copyOtherIntoSelf(trip);
                }
            }
        }
        currentMin = shortestTrip.tripCost(dGraph);
        //recursive case
        for (Integer neighbor : neighbors) {
            if (trip.isCityAvailable(neighbor)) {
                //choose
                trip.chooseNextCity(neighbor);
                visitOrder.add(neighbor);
                //explore (optimization: see if it's below the current minimum trip cost. If not, we should just skip it)
                if (trip.tripCost(dGraph) < currentMin) {
                    myAlgorithmHelper(dGraph, visitOrder, trip, numCities, shortestTrip, count + 1);
                }
                //un-choose
                trip.unchooseLastCity();
                visitOrder.remove(neighbor);
            }
        }
        return shortestTrip;
    }

    /*
     * Purpose: This method takes the passed information
     * and then performs a traditional recursive backtracking approach
     * to solving the TSP. It calls a helper method.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @param command, is the String containing the command to perform
     *
     * @return shortestTrip, is the shortest possible path found
     *
     */
    private static Trip backtrackMethod(DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities, String command) {
        //choose
        trip.chooseNextCity(1);
        visitOrder.add(1);

        //call recursive function // explore
        Trip shortestTrip = backtrackMethodHelper(dGraph, trip, visitOrder, new Trip(numCities), 0, numCities);

        if (!command.equals("TIME")) {
            printResults(shortestTrip, dGraph);
        }
        return shortestTrip;
    }

    /*
     * Purpose: This method takes the passed information
     * and then performs a traditional recursive backtracking approach
     * to solving the TSP. It gets called from backtrackMethod.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     *
     * @param trip, is the trip being taken
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param shortestTrip, stores the shortest possible path found during
     * the search
     *
     * @param count, used as an index to get the neighbors of the current node
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @return shortestTrip, returns the shortest trip found
     *
     */
    private static Trip backtrackMethodHelper(DGraph dGraph, Trip trip, List<Integer> visitOrder, Trip shortestTrip, int count, int numCities) {

        List<Integer> neighbors = dGraph.getNeighbors(visitOrder.get(count));
        //base case - if there are no cities left
        if (trip.citiesLeft().size() == 0) {
            //if we have visited all cities
            if (visitOrder.size() == numCities) {
                if (trip.tripCost(dGraph) < shortestTrip.tripCost(dGraph)) {
                    shortestTrip.copyOtherIntoSelf(trip);
                }
            }
        } else {
            //recursive case
            for (Integer neighbor : neighbors) {
                if (trip.isCityAvailable(neighbor)) {
                    //choose
                    trip.chooseNextCity(neighbor);
                    visitOrder.add(neighbor);
                    //explore
                    backtrackMethodHelper(dGraph, trip, visitOrder, shortestTrip, count + 1, numCities);
                    //un-choose
                    trip.unchooseLastCity();
                    visitOrder.remove(neighbor);
                }
            }
        }
        return shortestTrip;
    }

    /*
     * Purpose: This method takes the passed information
     * and then performs an heuristic approach to solving the TSP. It
     * calls a helper method.
     *
     * @param dGraph, is the dGraph where all of the graph info is stored.
     *
     * @param visitOrder, is an ArrayList of integers that keeps track
     * of the order of cities visited
     *
     * @param trip, is the trip being taken
     *
     * @param numCities, is the int indicating how many cities are in the graph
     *
     * @param command, is the String containing the command to perform
     *
     * @return trip, returns a trip found quickly, but perhaps not the optimal path
     *
     */
    private static Trip heuristicTravel(DGraph dGraph, List<Integer> visitOrder, Trip trip, int numCities, String command) {
        //created a map of path costs to find the shortest path
        Map<Integer, Double> pathCosts = new HashMap<>();
        //we need to compare the weights. Whichever has lower weight is the path we choose
        trip.chooseNextCity(1);
        visitOrder.add(1);
        for (int k = 1; k < numCities; k++) {

            List<Integer> neighbors = dGraph.getNeighbors(visitOrder.get(k - 1));
            int currentCity = visitOrder.get(k - 1);
            //call getMinWeight method to find the shortest path
            Double min = getMinWeight(dGraph, currentCity, neighbors, pathCosts);
            //call findNextCity to make sure that city is available
            int nextCity = findNextCity(pathCosts, min);

            while (!trip.isCityAvailable(nextCity)) {
                pathCosts.remove(nextCity);
                Double nextMin = Collections.min(pathCosts.values());
                nextCity = findNextCity(pathCosts, nextMin);
            }
            trip.chooseNextCity(nextCity);
            visitOrder.add(nextCity);
            pathCosts.clear();

        }
        //I used this so it wouldn't print the usual output when running the TIME command
        if (!command.equals("TIME")) {
            printResults(trip, dGraph);
        }
        return trip;
    }

    /*
     * Purpose: This private method gets called from heuristic travel
     * to find the next shortest path to take.
     *
     * @param dGraph, is the digraph Object containing all stored info
     *
     * @param currentCity, is the integer indicating the current node.
     *
     * @param neighbors, is the List of all neighbors
     *
     * @param pathCosts, is the HashMap that contains all paths as keys
     * and their costs as values
     *
     * @return Collections.min(pathCosts.values()), returns the minimum path.
     */
    private static Double getMinWeight(DGraph dGraph, int currentCity, List<Integer> neighbors, Map<Integer, Double> pathCosts) {
        for (Integer neighbor : neighbors) {
            double weight = dGraph.getWeight(currentCity, neighbor);
            if (weight >= 0) {
                pathCosts.put(neighbor, weight);
            }
        }
        return Collections.min(pathCosts.values());
    }

    /*
     * Purpose: This private method just takes the parameters
     * and calls toString to print the results of a trip.
     *
     * @param trip, is the trip being printed
     *
     * @param dGraph, is the dgraph that stores all graph information
     */
    private static void printResults(Trip trip, DGraph dGraph) {
        System.out.println(trip.toString(dGraph));
    }

    /*
     * Purpose: This private method takes searches all the cities
     * using the passed minimum path, and then chooses that city next
     *
     * @param pathCosts, is the HashMap containing cities as keys and
     * the cost to get there as a value
     *
     * @param min, is the double indicating which edge to look for
     *
     * @return nextCity, is the next city to travel to
     */
    private static int findNextCity(Map<Integer, Double> pathCosts, Double min) {
        int nextCity = -1;

        for (Integer key : pathCosts.keySet()) {
            if (min.equals(pathCosts.get(key))) {
                nextCity = key;
                break;
            }
        }
        return nextCity;
    }

    /*
     * Purpose: This method takes the file name from String[] args,
     * creates a new DGraph instance and inserts all necessary information
     *
     * @param filename, is the String consisting of the file name
     *
     * @return dGraph, is the DGraph object holding all of the parsed
     * information
     */
    public static DGraph read(String filename) throws IOException {
        //this allows us to get the number of cities
        Set<Integer> cities = new HashSet<>();
        Scanner scanner = new Scanner(new File(filename));
        String line;
        // read comment lines if any
        boolean comment = true;
        while (comment) {
            line = scanner.nextLine();
            comment = line.startsWith("%");
        }
        DGraph dGraph = null;
        //data section
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (line == null) break;
            String[] str = line.split("( )+");
            int i = Integer.parseInt(str[0].trim());
            int j = Integer.parseInt(str[1].trim());
            //since hashsets don't take duplicates this keeps the right amount
            cities.add(i);
            cities.add(j);
            double x = Double.parseDouble(str[2].trim());
            dGraph = new DGraph(cities.size());
            dGraph.addEdge(i, j, x);
        }
        scanner.close();
        return dGraph;
    }

}