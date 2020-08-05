/*
 * AUTHOR: David Anderson
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming Assignment 5 - Garden
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class holds empty plots that are used to fill the garden in between
 * the 5x5 plant strings.
 *
 */

import java.util.Arrays;

public class EmptyPlot extends Plant {

    private final int[] plotNumber = new int[2];
    private final String[] emptyPlotString = new String[5];

    public EmptyPlot() {
        createEmptyPlotString();
    }

    /*
     * Purpose: A method that fills the emptyPlotString[]
     * with 5x5 empty plots.
     */
    public void createEmptyPlotString() {
        Arrays.fill(emptyPlotString, ".....");

    }

    /*
     * Purpose: A method that sets the plot number and
     * coordinates for the empty plot. I used this with TreeMap
     * to automatically sort every plot in the garden.
     *
     * @param plotNumber1, is the String indicating the plot
     * number to be associated with this plot.
     */
    public void setPlotNumber(String plotNumber1) {

        String rowColumn = plotNumber1.replaceAll("\\D", "");
        int row = Integer.parseInt(rowColumn.substring(0, 1));
        int column = Integer.parseInt(rowColumn.substring(1));
        plotNumber[0] = row;
        plotNumber[1] = column;
    }

    /*
     * Purpose: A method that returns the plot number
     */
    @Override
    public int[] getPlotNumber() {
        return plotNumber;
    }

    /*
     * Purpose: A method that returns the String[]
     * of empty plots.
     */
    public String[] getEmptyPlotString() {
        return emptyPlotString;
    }
}
