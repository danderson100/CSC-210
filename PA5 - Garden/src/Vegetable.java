/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class creates Vegetable objects to be planted in the garden. It
 * stores their information and growth amount, and uses multiple methods
 * from its parent class Plant.
 *
 */

import java.util.Arrays;

public class Vegetable extends Plant {

    private Type vegetableType;
    private int growCount = 0;
    private final String[] veggieString = new String[5];

    public Vegetable() {
        //this creates the string when a Vegetable object is created.
        createVegetableString();
    }

    public enum Type {
        GARLIC, ZUCCHINI, TOMATO, YAM, LETTUCE
    }

    public Type getType() {
        return this.vegetableType;
    }

    /*
     * Purpose: A method that sets the type of vegetable
     * so it can be accessed later in the Garden class.
     *
     * @param type, is the String containing the type
     * from the input commands.
     */
    public void setType(String type) {
        switch (type) {
            case "garlic":
                this.vegetableType = Type.GARLIC;
                setId('g');
                break;
            case "zucchini":
                this.vegetableType = Type.ZUCCHINI;
                setId('z');
                break;
            case "tomato":
                this.vegetableType = Type.TOMATO;
                setId('t');
                break;
            case "yam":
                this.vegetableType = Type.YAM;
                setId('y');
                break;
            case "lettuce":
                this.vegetableType = Type.LETTUCE;
                setId('l');
                break;

        }
    }

    /*
     * Purpose: A method that returns
     * the String[] array containing the 5x5 plot
     */
    public String[] getVeggieString() {
        return veggieString;
    }

    /*
     * Purpose: A method that generates the
     * initial vegetable String array.
     */
    public void createVegetableString() {
        Arrays.fill(veggieString, ".....");

    }

    /*
     * Purpose: A method that generates the vegetableString
     * after it has been created and grown.
     *
     * @param growAmount, is the amount to grow the tree.
     */
    public void vegetableString(int growAmount) {

        for (int i = 0; i <= growAmount; i++) {
            if (growCount < MAX_GROW_COUNT) {
                veggieString[growCount] = ".." + getId() + "..";
                growCount++;
            }

        }
    }
}
