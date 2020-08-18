# wordsearch

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
