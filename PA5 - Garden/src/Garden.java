/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores all of the information related to the
 * garden object and the plants that get stored here. It is used to
 * create, print, and grow the plants.
 *
 */

import java.util.*;

public class Garden {

    String[][] garden;
    int rows;
    int columns;
    List<Plant> plants = new ArrayList<>();
    List<String> allPlots = new ArrayList<>();
    TreeMap<String, List<String>> orderedByPlotNumber = new TreeMap<>();
    List<Plant> grownPlants = new ArrayList<>();


    public Garden() {

    }

    /*
     * Purpose: A method that takes commands and uses those to
     * grow the appropriate plant(s).
     *
     * @param growAmount, is the int indicating the amount to grow.
     *
     * @param coordinatesOrType, is the String indicating either the
     * coordinates at which to grow or the type of plant to grow.
     */
    public void grow(int growAmount, String coordinatesOrType) {
        if (coordinatesOrType != null) {
            if (coordinatesOrType.contains("(")) {
                String rowColumn = coordinatesOrType.replaceAll("\\D", "");
                int row = Integer.parseInt(rowColumn.substring(0, 1));
                int column = Integer.parseInt(rowColumn.substring(1));
                String plotNumber = "[" + row + ", " + column + "]";
                growSpecificPlant(growAmount, plotNumber);
            } else {
                growSpecificPlant(growAmount, coordinatesOrType);

            }
        } else {
            growAll(growAmount);
        }
    }

    /*
     * Purpose: A helper method that grows all plants in the garden
     * if specifics aren't provided.
     *
     * @param growAmount, is the int indicating the amount to grow.
     */
    private void growAll(int growAmount) {
        for (Plant plant : plants) {
            if (plant instanceof Tree) {

                Tree grownTree = new Tree();
                grownTree.setId(plant.getId());
                int[] plot = plant.getPlotNumber();
                grownTree.setPlotNumber(plot[0], plot[1]);
                grownTree.treeString(growAmount);

                grownPlants.add(grownTree);

            } else if (plant instanceof Flower) {
                Flower grownFlower = new Flower();
                grownFlower.setId(plant.getId());
                grownFlower.growFlower(growAmount);
                int[] plot = plant.getPlotNumber();
                grownFlower.setPlotNumber(plot[0], plot[1]);
                grownPlants.add(grownFlower);

            } else {

                Vegetable grownVegetable = new Vegetable();
                grownVegetable.setId(plant.getId());
                grownVegetable.vegetableString(growAmount);
                int[] plot = plant.getPlotNumber();
                grownVegetable.setPlotNumber(plot[0], plot[1]);

                grownPlants.add(grownVegetable);
            }

        }
        plants.clear();
        plants.addAll(grownPlants);

    }

    /*
     * Purpose: A helper method that takes commands and uses those to
     * grow the appropriate specific plant(s).
     *
     * @param growAmount, is the int indicating the amount to grow.
     *
     * @param plotNumberOrType, is the String containing either the
     * plot number or the type of plant to grow.
     */
    public void growSpecificPlant(int growAmount, String plotNumberOrType) {
        int index = 0;
        if (plotNumberOrType.contains("[")) {
            for (Plant plant : plants) {
                int[] plotNumber = plant.getPlotNumber();
                String plotNo = Arrays.toString(plotNumber);
                if (plotNo.equals(plotNumberOrType)) {
                    index = plants.indexOf(plant);

                    if (plant instanceof Tree) {
                        Tree grownTree = new Tree();
                        grownTree.setId(plant.getId());
                        int[] plot = plant.getPlotNumber();
                        grownTree.setPlotNumber(plot[0], plot[1]);
                        grownTree.treeString(growAmount);
                        grownPlants.add(grownTree);
                        break;

                    } else if (plant instanceof Flower) {
                        Flower grownFlower = new Flower();
                        grownFlower.setId(plant.getId());
                        grownFlower.growFlower(growAmount);
                        int[] plot = plant.getPlotNumber();
                        grownFlower.setPlotNumber(plot[0], plot[1]);
                        grownPlants.add(grownFlower);
                        break;

                    } else {
                        Vegetable grownVegetable = new Vegetable();
                        grownVegetable.setId(plant.getId());
                        grownVegetable.vegetableString(growAmount);
                        int[] plot = plant.getPlotNumber();
                        grownVegetable.setPlotNumber(plot[0], plot[1]);
                        grownPlants.add(grownVegetable);
                        break;
                    }
                }
            }
        } else {
            growByType(plotNumberOrType, growAmount);
            //else we need to grow every plant of that type
        }

        if (grownPlants.size() > 0) {
            plants.remove(index);
            plants.add(index, grownPlants.get(0));
        } else {
            System.out.println("Can't grow there.");
            System.out.println();
        }
    }

    /*
     * Purpose: A helper method that takes commands and uses those to
     * grow the appropriate plant(s).
     *
     * @param growAmount, is the int indicating the amount to grow.
     *
     * @param type, is the String indicating
     *  the type of plant to grow.
     */
    private void growByType(String type, int growAmount) {
        List<Plant> storePlantsToRemove = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant instanceof Tree) {
                Tree grownTree = new Tree();
                String treeType = grownTree.getType().toString();
                if (type.equals(treeType)) {
                    grownTree.setId(plant.getId());
                    int[] plot = plant.getPlotNumber();
                    grownTree.setPlotNumber(plot[0], plot[1]);
                    grownTree.treeString(growAmount);
                    storePlantsToRemove.add(plant);
                    grownPlants.add(grownTree);
                }
            } else if (plant instanceof Flower) {
                String flowerType;
                Flower flower = (Flower) plant;
                if (flower.getType() != null) {
                    flowerType = flower.getTypeString();
                } else {
                    flowerType = "not";
                }

                if (type.equals(flowerType)) {
                    Flower grownFlower = new Flower();
                    grownFlower.setId(plant.getId());
                    int[] plot = plant.getPlotNumber();
                    grownFlower.setPlotNumber(plot[0], plot[1]);
                    grownFlower.growFlower(growAmount);
                    storePlantsToRemove.add(plant);
                    grownPlants.add(grownFlower);
                }
            } else {
                Vegetable grownVegetable = new Vegetable();
                String vegetableType = grownVegetable.getType().toString();
                if (type.equals(vegetableType)) {
                    grownVegetable.setId(plant.getId());
                    int[] plot = plant.getPlotNumber();
                    grownVegetable.setPlotNumber(plot[0], plot[1]);
                    grownVegetable.vegetableString(growAmount);
                    storePlantsToRemove.add(plant);
                    grownPlants.add(grownVegetable);
                }
            }
        }
        for (Plant plant : storePlantsToRemove) {
            plants.remove(plant);
        }
        plants.addAll(grownPlants);

    }

    /*
     * Purpose: A method that adds plants to the garden by
     * adding them to the List<Plant> plants.
     *
     * @param plant, is the Plant object being added.
     */
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    /*
     * Purpose: A method that generates the garden with
     * appropriate rows and columns
     *
     * @param row, is the int indicating rows.
     *
     * @param column, is the int indicating the columns.
     */
    public void createGarden(int row, int column) {
        rows = row;
        columns = column;
        int gardenLength = row * 5;

        garden = new String[gardenLength][column];
        for (int i = 0; i < gardenLength; i++) {
            for (int j = 0; j < column; j++) {
                garden[i][j] = ".....";
            }
        }
    }

    public void cutTree() {
        //cut tree at specific coordinate
    }

    public void pickFlower() {
        //pick flower at coordinates
    }

    public void harvestVegetables() {
        //harvest veggies at coordinates
    }

    public void remove(int row, int column) {
        garden[row][column] = null;
    }

    /*
     * Purpose: A method that creates all possible plots in the
     * grid, so that the program knows when to insert empty plots
     * and when to use the plant strings.
     */
    public void createAllPlots() {

        int[] samplePlot = new int[2];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                samplePlot[0] = r;
                samplePlot[1] = c;
                allPlots.add(Arrays.toString(samplePlot));
            }
        }
    }

    /*
     * Purpose: A method that generates EmptyPlot objects
     * and stores them in a TreeMap in order to fill the
     * garden.
     *
     * @param plotNumbers, is the List<String> of plotNumbers
     * being taken by the current plants.
     */
    private void generateEmptyPlots(List<String> plotNumbers) {

        for (String plotNumber : allPlots) {
            //if no plant currently has that plot number, it fills it with an empty 5x5 plot
            if (!plotNumbers.contains(plotNumber)) {

                EmptyPlot emptyPlot = new EmptyPlot();
                emptyPlot.setPlotNumber(plotNumber);
                String[] emptyPlotString = emptyPlot.getEmptyPlotString();
                List<String> emptyPlotList = new ArrayList<>(Arrays.asList(emptyPlotString));
                orderedByPlotNumber.put(plotNumber, emptyPlotList);

            }
        }
    }

    /*
     * Purpose: An (unfortunately gigantic) method that prints
     * out the garden.
     */
    public void printGarden() {


        List<List<String>> rowLists = new ArrayList<>();
        List<String> plotNumbers = new ArrayList<>();

        Tree tree;
        Flower flower;
        Vegetable vegetable;

        for (Plant plant : plants) {

            if (plant instanceof Tree) {
                tree = (Tree) plant;
                String[] treeRows = tree.getTreeString();
                List<String> treeRowList = new ArrayList<>(Arrays.asList(treeRows));
                plotNumbers.add(Arrays.toString(tree.getPlotNumber()));
                orderedByPlotNumber.put(Arrays.toString(tree.getPlotNumber()), treeRowList);

            } else if (plant instanceof Flower) {
                flower = (Flower) plant;
                String[] flowerRows = flower.getFlowerString();
                List<String> flowerRowList = new ArrayList<>(Arrays.asList(flowerRows));
                plotNumbers.add(Arrays.toString(flower.getPlotNumber()));
                orderedByPlotNumber.put(Arrays.toString(flower.getPlotNumber()), flowerRowList);

            } else {
                vegetable = (Vegetable) plant;
                String[] veggieRows = vegetable.getVeggieString();
                List<String> vegetableRowList = new ArrayList<>(Arrays.asList(veggieRows));
                plotNumbers.add(Arrays.toString(vegetable.getPlotNumber()));
                orderedByPlotNumber.put(Arrays.toString(vegetable.getPlotNumber()), vegetableRowList);

            }
        }
        generateEmptyPlots(plotNumbers);

        Collections.sort(allPlots);
        //add all of the plots, the plants and the empty plots, in order based on plot number
        for (int i = 0; i < orderedByPlotNumber.size(); i++) {
            rowLists.add(orderedByPlotNumber.get(allPlots.get(i)));
        }

        StringBuilder stringBuilder = new StringBuilder();

        int gardenLength = rows * 5;
        int count = 0;
        int j = 0;
        int i = 0;

        while (count < gardenLength) {
            boolean flag = false;
            while (!flag) {
                for (int c = 0; c < columns; c++) {
                    String firstRows = rowLists.get(j).get(i);
                    stringBuilder.append(firstRows);
                    //get first row of next item in firstRows
                    if (columns > 1) {
                        j++;
                    }

                }
                System.out.println(stringBuilder.toString());

                count++;
                //I was going to clean this up but ran out of time. Sorry!
                if (columns > 1 && count < 5) {
                    j = 0;
                } else if (columns > 1 && count < 10) {
                    j = columns;
                } else if (columns > 1 && count < 15) {
                    j = columns * 2;
                } else if (columns > 1 && count < 20) {
                    j = columns * 3;
                } else if (columns > 1 && count < 25) {
                    j = columns * 4;
                } else if (columns > 1 && count < 30) {
                    j = columns * 5;
                } else if (columns > 1 && count < 35) {
                    j = columns * 6;
                }

                i++;
                stringBuilder.delete(0, stringBuilder.length());
                if (count % 5 == 0) {
                    flag = true;
                }
            }
            i = 0;
            if (columns == 1 && rows > 1) {
                j++;
            }
        }
        orderedByPlotNumber.clear();
        rowLists.clear();
        plotNumbers.clear();
    }
}


