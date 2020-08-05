/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This abstract class acts as the parent class to the Tree,
 * Flower, and Vegetable subclasses. Since I didn't need to instantiate Plant
 * objects themselves I just used this to supply methods and fields to subclasses.
 *
 */
public abstract class Plant extends Garden {

    public char id;
    private final int[] plotNumber = new int[2];
    public final int MAX_GROW_COUNT = 4;
    private String plantString;
    private Type plantType;

    public Plant() {
    }

    //I used enums to determine the plant types
    public enum Type {
        TREE, FLOWER, VEGETABLE
    }

    /*
     * Purpose: A method that returns Type plantType.
     */
    public Type getPlantType() {
        return plantType;
    }

    /*
     * Purpose: A method that sets the plant type
     * for future reference.
     *
     * @param plantType, is the Type for this plant instance.
     */
    public void setPlantType(Type plantType) {
        this.plantType = plantType;
    }

    /*
     * Purpose: A method that returns the id for this plant
     */
    public char getId() {
        return id;
    }

    /*
     * Purpose: A method that sets the id for this
     * plant object.
     */
    public void setId(char id) {
        this.id = id;
    }

    //unfinished method to return the plantString if needed
    public String getPlantString() {
        return plantString;
    }

    //unfinished method to set the plant string if needed.
    public void setPlantString(String plantString) {
        this.plantString = plantString;
    }

    /*
     * Purpose: A method that returns the plot number
     */
    public int[] getPlotNumber() {
        return plotNumber;
    }

    /*
     * Purpose: A method that sets the plot number.
     */
    public void setPlotNumber(int row, int column) {
        plotNumber[0] = row;
        plotNumber[1] = column;
    }

    public void grow(int growAmount) {
        System.out.println("Growing...");
    }


    @Override
    public void remove(int row, int column) {

    }

}
