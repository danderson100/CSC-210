/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This program is intended to take an input file as an argument
 * and create a "garden" consisting of various flower, trees, and vegetables. It
 * also takes commands, coordinates, grow amount, and other optional inputs. This
 * is the main program class that reads the input file and generates all of the other classes and
 * class Objects. Although I was unable to fully complete the project, I believe I came quite close
 * and I am happy with the amount of work I put in. I didn't get the result I wanted,
 * but I learned a lot about Java and working through issues in programming.
 *
 * USAGE:
 * java PA5Main infile
 *
 * where infile is the name of an input file in the following format:
 *
 * ----------- EXAMPLE INPUT -------------
 * Input file:
 * -------------------------------------------
 * rows: 1
 * cols: 3
 * PLANT (0,0) banana
 * PLANT (0,1) tomato
 * PLANT (0,2) sunflower
 * PRINT
 * GROW 1
 * print
 * -------------------------------------------
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PA5Main {

    public static void main(String[] args) {
        String inputFile = args[0];
        List<String> input = readInput(inputFile);

        Garden garden = new Garden();

        createGarden(input, garden);
        //Instantiated a plant object so I could access it outside the loop
        Plant plant;

        for (int i = 2; i < input.size(); i++) {
            String coordinatesOrType = null;
            String[] split = input.get(i).split("\\s+");
            String command = split[0];
            switch (command) {
                case "plant":
                    String coordinates = split[1];
                    String type = split[2];
                    plant = checkType(type);
                    plant(coordinates, plant, garden);
                    break;
                case "grow":
                    int growAmount = Integer.parseInt(split[1]);
                    if (split.length > 2) {
                        coordinatesOrType = split[2];
                    }
                    growPlants(growAmount, garden, coordinatesOrType);
                    break;
                case "cut":
                    if (split.length > 1) {
                        coordinatesOrType = split[1];
                    }
                    System.out.println("> CUT " + coordinatesOrType);
                    System.out.println();
                    System.out.println("Can't cut there.");
                    System.out.println();
                    //cut trees
                    break;
                case "pick":
                    if (split.length > 1) {
                        coordinatesOrType = split[1];
                    }
                    System.out.println("> PICK " + coordinatesOrType);
                    System.out.println();
                    System.out.println("Can't pick there.");
                    System.out.println();
                    //pick flowers
                    break;
                case "harvest":
                    if (split.length > 1) {
                        coordinatesOrType = split[1];
                    }
                    System.out.println("> HARVEST " + coordinatesOrType);
                    System.out.println();
                    System.out.println("Can't harvest there.");
                    System.out.println();
                    //harvest veggies
                    break;
                case "print":
                    printGarden(garden);
            }

        }
    }

    /*
     * Purpose: A method that prints out the created Garden object.
     *
     * @param garden, is the Garden object containing all of the plants and grid.
     */
    private static void printGarden(Garden garden) {
        System.out.println("> PRINT");
        garden.printGarden();
        System.out.println();
    }

    /*
     * Purpose: A method that prints out the required lines and
     * calls garden.grow method to grow each individual plant.
     *
     * @param growAmount, is the int dictating the amount to grow the plant(s).
     *
     * @param garden, is the Garden object containing all plants and grid.
     *
     * @param coordinatesOrType, is the command string containing either the'
     * coordinates for the plant to grow or the type of plants to grow.
     */
    private static void growPlants(int growAmount, Garden garden, String coordinatesOrType) {
        if (coordinatesOrType != null) {
            System.out.println("> GROW " + growAmount + " " + coordinatesOrType + "\n");
        } else {
            System.out.println("> GROW " + growAmount);
            System.out.println();
        }
        garden.grow(growAmount, coordinatesOrType);
    }

    /*
     * Purpose: A method that checks the type of Plant
     * being planted against HashSets containing all plant Types,
     * and returns a tree, flower, or vegetable to plant.
     *
     * @param type, is the String containing the type of plant
     * to be planted.
     *
     * @return Plant, which is the specific type of plant returned
     * and planted in the garden
     */
    private static Plant checkType(String type) {
        HashSet<String> treeTypes = new HashSet<>();
        HashSet<String> flowerTypes = new HashSet<>();

        for (Tree.Type t : Tree.Type.values()) {
            treeTypes.add(t.toString().toLowerCase());
        }
        for (Flower.Type t : Flower.Type.values()) {
            flowerTypes.add(t.toString().toLowerCase());
        }

        if (treeTypes.contains(type)) {
            Tree tree = new Tree();
            tree.setType(type);
            return tree;

        } else if (flowerTypes.contains(type)) {
            Flower flower = new Flower();
            flower.setType(type);
            return flower;
        } else {
            Vegetable vegetable = new Vegetable();
            vegetable.setType(type);
            return vegetable;
        }
    }

    /*
     * Purpose: A method that uses the inputted coordinates, plant,
     * and garden to plant each new plant.
     *
     * @param coordinates, is the String containing the plot where
     * the plant will be planted
     *
     * @param plant, is the Plant object being planted
     *
     * @param garden, is the Garden object containing all plants.
     */
    private static void plant(String coordinates, Plant plant, Garden garden) {

        String rowColumn = coordinates.replaceAll("\\D", "");
        int row = Integer.parseInt(rowColumn.substring(0, 1));
        int column = Integer.parseInt(rowColumn.substring(1));

        if (plant instanceof Tree) {
            Tree tree = (Tree) plant;
            tree.setPlotNumber(row, column);
            plant.setPlantType(Plant.Type.TREE);
            tree.treeString(0);
            garden.addPlant(tree);

        } else if (plant instanceof Flower) {
            Flower flower = (Flower) plant;
            flower.setPlotNumber(row, column);
            flower.flowerString(0);
            garden.addPlant(flower);

        } else if (plant instanceof Vegetable) {
            Vegetable vegetable = (Vegetable) plant;
            vegetable.setPlotNumber(row, column);
            vegetable.vegetableString(0);
            garden.addPlant(vegetable);

        } else {
            System.out.println("Can't plant that.");
        }

    }

    /*
     * Purpose: A method that creates an initial garden with a grid
     * and the correct amount of rows and columns.
     *
     * @param input, is the List<String> that contains all of the
     * input from the file
     *
     * @param garden, is the Garden object containing all plants.
     */
    private static void createGarden(List<String> input, Garden garden) {

        int row = Integer.parseInt(input.get(0).replaceAll("\\D", ""));
        int column = Integer.parseInt(input.get(1).replaceAll("\\D", ""));
        if (column <= 16) {
            garden.createGarden(row, column);
            garden.createAllPlots();
        } else {
            System.out.println("Too many plot columns.");
            System.exit(0);
        }

    }

    /*
     * Purpose: A method that reads the input file, iterates
     * over the file and extracts all of the strings
     *
     * @param inputFile, is the String with the file name.
     *
     * @return input, which is the List<String> containing all
     * information from the file.
     */
    private static List<String> readInput(String inputFile) {
        List<String> input = new ArrayList<>();
        //File file = new File("./PublicTestCases/" + inputFile);
        File file = new File(inputFile);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    input.add(line.toLowerCase());
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        return input;
    }
}
