/*
 *
 * AUTHOR: David Anderson
 * FILE: WordSearch.java
 * ASSIGNMENT: Programming Assignment 1 - Word Search
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This program reads in two text files--a dictionary file, and a file
 * that contains integers indicating the number of rows and columns and a grid
 * of space-separated characters. The program searches the grid horizontally
 * and vertically, backwards and forwards, for words that match dictionary values.
 * Finally, it outputs any found words in the order they were found.
 *
 * USAGE:
 * java WordSearch dictionarypath gridpath
 *
 * where dictionarypath and gridpath are the locations of the input files
 * within the project directory.
 *
 * ----------- EXAMPLE INPUT -------------
 * Dictionary file (reduced):
 * Aarhus
 * Aaron
 * Ababa
 * aback
 * abaft
 * abandon
 * abandoned
 * abandoning
 * abandonment
 * abandons
 * abase
 * abased
 *
 * Grid file:
 * 6
 * 6
 * y c o d e j
 * h s e y p k
 * l p h b w a
 * l o b w x z
 * w o b a a i
 * p l y y c g
 * -------------------------------------------
 * This program assumes that the correct directories and file names
 * are entered as command-line arguments.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordSearch {

    private static final int MIN_WORD_LENGTH = 3;
    private static final List<String> DICTIONARY_LIST = new ArrayList<>();
    private static final List<List<String>> POSSIBLE_WORD_LISTS = new ArrayList<>();
    /*
     * The purpose of the main method is to call each sub-method--passing
     * the data found from the grid files and reversing the rows--
     * and then search for each word in the ArrayList DICTIONARY_LIST
     * using Collections.binarySearch for efficiency. It iterates through
     * POSSIBLE_WORD_LISTS and if an index is found, the word exists
     * and it prints the word.
     */
    static public void main(String[] args) {
        String dictionaryPath = args[0];
        String gridPath = args[1];

        createDictionary(dictionaryPath);

        possibleRowWords(getRows(gridPath));
        possibleRowWords(reverseLists(getRows(gridPath)));
        possibleColumnWords(getColumns(gridPath));
        possibleColumnWords(reverseLists(getColumns(gridPath)));

        Collections.sort(DICTIONARY_LIST);
        //Here I use a nested for loop to search within the lists
        for (List<String> possibleWordList : POSSIBLE_WORD_LISTS) {
            for (String word : possibleWordList) {
                int index = Collections.binarySearch(DICTIONARY_LIST, word);
                if (index >= 0) {
                    System.out.println(word);
                }
            }
        }
    }
    /*
     * Purpose: This method uses the information gathered from
     * getColumns() and splices the Strings into smaller substrings
     * that fit the minimum length requirement.
     * It then feeds these into a List of ArrayLists POSSIBLE_WORD_LISTS
     * that holds all possible words from the grid.
     * NOTE: I made two methods because I was unsure if the rows and columns
     * could each be a different size, and that was making my program crash.
     */
    private static void possibleColumnWords(List<String> columns) {
        List<String> columnWords = new ArrayList<>();
        int columnNum = 0;
        while (columnNum != columns.size()) {
            try {
                for (int i = 0; i < columns.get(columnNum).length() - 2; i++) {
                    for (int j = 0; j <= columns.get(columnNum).length(); j++) {
                        if (i < j) {
                            String possibleColumnWord = columns.get(columnNum).substring(i, j);
                            if (possibleColumnWord.length() >= MIN_WORD_LENGTH) {
                                columnWords.add(possibleColumnWord);
                            }
                        }
                    }
                }
                columnNum++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e);
            }
        }
        POSSIBLE_WORD_LISTS.add(columnWords);
    }
    /*
     * Purpose: This method takes as a parameter the rows and columns found
     * in the getRows() and getColumns() methods, and reverses each
     * string so that it can iterate right-to-left & bottom-to-top.
     */
    private static List<String> reverseLists(List<String> list) {
        List<String> reversedList = new ArrayList<>();
        for (String row : list) {
            StringBuilder reversedItem = new StringBuilder();
            reversedItem.append(row);
            reversedList.add(reversedItem.reverse().toString());
        }
        return reversedList;
    }

    /*
     * Purpose: This method uses the information gathered from
     * getRows() and splice the Strings into
     * smaller substrings that fit the minimum length requirement.
     * It then feeds these into a List of ArrayLists POSSIBLE_WORD_LISTS
     * that holds all possible words from the grid.
     */
    private static void possibleRowWords(List<String> rows) {
        List<String> rowWords = new ArrayList<>();
        int rowNum = 0;
        while (rowNum != rows.size()) {
            try {
                for (int i = 0; i < rows.get(rowNum).length() - 2; i++) {
                    for (int j = 0; j <= rows.get(rowNum).length(); j++) {
                        if (i < j) {
                            String possibleRowWord = rows.get(rowNum).substring(i, j);
                            if (possibleRowWord.length() >= MIN_WORD_LENGTH) {
                                rowWords.add(possibleRowWord);
                            }
                        }
                    }
                }
                rowNum++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e);
            }
        }
        POSSIBLE_WORD_LISTS.add(rowWords);
    }
    /*
     * Purpose: this method reads the input dictionary file
     * and creates an ArrayList DICTIONARY_LIST that will be used
     * to search for word matches in the Main method
     */
    private static void createDictionary(String inputPath) {
        try {
            Scanner fileReader = new Scanner(new File(inputPath));

            while (fileReader.hasNext()) {
                String word = fileReader.nextLine();
                DICTIONARY_LIST.add(word.toLowerCase());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }
    /*
     * Purpose: this method takes the grid file path as a parameter
     * and reads the values. It removes spaces from each line to
     * make the String easier to analyze, and then it adds it to
     * an ArrayList rows.
     */
    private static List<String> getRows(String inputPath) {
        List<String> rows = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(inputPath));
            fileReader.nextLine();fileReader.nextLine(); //I put this in to skip the first two lines because they don't have words

            while (fileReader.hasNext()) {
                String row = fileReader.nextLine().toLowerCase();
                String noSpacesRow = row.replaceAll("\\s+", "");
                rows.add(noSpacesRow);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return rows;
    }
    /*
     * Purpose: this method takes the grid file path as a parameter
     * and reads the first character of each line to obtain the first column,
     * then iterates through the lines to get each column.
     * It removes spaces from each line to make the String easier to analyze,
     * and then it adds it to an ArrayList COLUMNS.
     */
    private static List<String> getColumns(String inputPath) {
        List<String> columns = new ArrayList<>();
        int counter = 0;
        try {
            Scanner fileReader = new Scanner(new File(inputPath));
            int numRows = Integer.parseInt(fileReader.nextLine());
            int numColumns = Integer.parseInt(fileReader.nextLine());

            while (counter < numColumns) {
                for (int i = 0; i < numColumns; i++) {
                    Scanner newFileReader = new Scanner(new File(inputPath));
                    newFileReader.nextLine();newFileReader.nextLine();
                    //Used StringBuilder to append each char and create column strings
                    StringBuilder strColumn = new StringBuilder();
                    for (int j = 0; j < numRows; j++) {
                        String row = newFileReader.nextLine().toLowerCase();
                        String noSpaces = row.replaceAll("\\s+", "");
                        strColumn.append(noSpaces.charAt(i));
                    }
                    columns.add(strColumn.toString());
                    counter++;
                    newFileReader.close();
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return columns;
    }
}