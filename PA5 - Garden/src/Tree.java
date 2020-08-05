/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class creates Tree objects to be planted in the garden. It
 * stores their information and growth amount, and uses multiple methods
 * from its parent class Plant.
 *
 */

import java.util.Arrays;

public class Tree extends Plant {

    private Type treeType;
    private int growCount = 0;
    private final String[] treeString = new String[5];

    public Tree() {
        createTreeString();
    }

    //I used this enum to cycle through the various types of trees.
    public enum Type {
        OAK, WILLOW, BANANA, COCONUT, PINE
    }

    //returns the Type
    public Type getType() {
        return this.treeType;
    }

    /*
     * Purpose: A method that returns String array containing
     * the 5x5 plot.
     */
    public String[] getTreeString() {
        return treeString;
    }

    /*
     * Purpose: A method that sets the type of tree
     * so it can be accessed later in the Garden class.
     *
     * @param type, is the String containing the type
     * from the input commands.
     */
    public void setType(String type) {
        switch (type) {
            case "oak":
                this.treeType = Type.OAK;
                setId('o');
                break;
            case "willow":
                this.treeType = Type.WILLOW;
                setId('w');
                break;
            case "banana":
                this.treeType = Type.BANANA;
                setId('b');
                break;
            case "coconut":
                this.treeType = Type.COCONUT;
                setId('c');
                break;
            case "pine":
                this.treeType = Type.PINE;
                setId('p');
                break;
        }

    }

    //This method clears the 5x5 plot and makes it empty
    public void cutTree() {
        Arrays.fill(treeString, ".....");
    }

    /*
     * Purpose: A method that generates the
     * initial treeString.
     */
    public void createTreeString() {
        Arrays.fill(treeString, ".....");

    }

    /*
     * Purpose: A method that generates the treeString
     * after it has been created and grown.
     *
     * @param growAmount, is the amount to grow the tree.
     */
    public void treeString(int growAmount) {

        for (int i = 0; i <= growAmount; i++) {
            if (growCount < MAX_GROW_COUNT) {
                treeString[treeString.length - (growCount + 1)] = ".." + getId() + "..";
                growCount++;
            }

        }
    }
}
