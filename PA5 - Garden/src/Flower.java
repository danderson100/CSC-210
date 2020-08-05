/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class creates Flower objects to be planted in the garden. It
 * stores their information and growth amount, and uses multiple methods
 * from its parent class Plant.
 *
 */

import java.util.Arrays;

public class Flower extends Plant {


    private Type flowerType;
    private int growCount = 0;
    private final String[] flowerString = new String[5];

    public Flower() {
        createFlowerString();
    }


    public enum Type {
        IRIS, LILY, ROSE, TULIP, SUNFLOWER
    }

    public Type getType() {
        return this.flowerType;
    }

    /*
     * Purpose: A method that returns String containing
     * the type of Flower. This string is used in Garden to
     * check if it matches when growing.
     *
     * @return s, is the string containing the type
     */
    public String getTypeString() {
        switch (flowerType) {
            case IRIS:
                return "iris";
            case LILY:
                return "lily";
            case ROSE:
                return "rose";
            case TULIP:
                return "tulip";
            case SUNFLOWER:
                return "sunflower";
            default:
                return "";
        }
    }

    /*
     * Purpose: A method that returns String array containing
     * the 5x5 plot.
     */
    public String[] getFlowerString() {
        return flowerString;
    }

    /*
     * Purpose: A method that sets the type of flower
     * so it can be accessed later in the Garden class.
     *
     * @param type, is the String containing the type
     * from the input commands.
     */
    public void setType(String type) {
        switch (type) {
            case "iris":
                this.flowerType = Type.IRIS;
                setId('i');
                break;
            case "lily":
                this.flowerType = Type.LILY;
                setId('l');
                break;
            case "rose":
                this.flowerType = Type.ROSE;
                setId('r');
                break;
            case "tulip":
                this.flowerType = Type.TULIP;
                setId('t');
                break;
            case "sunflower":
                this.flowerType = Type.SUNFLOWER;
                setId('s');
                break;


        }


    }

    /*
     * Purpose: A method that generates the
     * initial flowerString.
     */
    public void createFlowerString() {
        Arrays.fill(flowerString, ".....");
        flowerString[(flowerString.length - growCount) / 2] = ".." + getId() + "..";

    }

    /*
     * Purpose: A method that generates the flowerString
     * after it has been created and grown.
     *
     * @param growAmount, is the amount to grow the flower.
     */
    public void flowerString(int growAmount) {

        for (int i = 0; i <= growAmount; i++) {

            flowerString[(flowerString.length - growCount) / 2] = ".." + getId() + "..";
            growCount++;
        }

    }

    /*
     * Purpose: A method that grows the flower by the
     * appropriate amount. I used this because I couldn't
     * figure out how to recursively "bloom" the flower
     *
     * @param growAmount, is the amount to grow the flower.
     */
    public void growFlower(int growAmount) {
        switch (growAmount) {
            case 1:
                flowerString[1] = ".." + getId() + "..";
                flowerString[2] = "." + getId() + getId() + getId() + ".";
                flowerString[3] = flowerString[1];
                break;
            case 2:
                flowerString[0] = ".." + getId() + "..";
                flowerString[1] = "." + getId() + getId() + getId() + ".";
                flowerString[2] = "" + getId() + getId() + getId() + getId() + getId();
                flowerString[3] = "." + getId() + getId() + getId() + ".";
                flowerString[4] = ".." + getId() + "..";
                break;
        }

    }

}
