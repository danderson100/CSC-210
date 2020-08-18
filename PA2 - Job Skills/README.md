# jobskills

/*
 * AUTHOR: David Anderson
 * FILE: PA2Main.java
 * ASSIGNMENT: Programming Assignment 2 - Job Skills
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This program reads in a text file which contains information about
 * various software engineering positions an their characteristics. It also accepts
 * a command as the second argument, and an optional element to narrow the scope
 * of the search. It has a private inner class Job to store each job and its features.
 * The program returns search results based on the input, in alphabetical order. If the
 * user types CATCOUNT, the program returns a count of each job category in that file; if
 * the user types LOCATIONS, the program returns the number of jobs at that location; if
 * the user uses an optional command like "Finance" it narrows the results to only those jobs
 * in the finance category.
 *
 * USAGE:
 * java PA2Main infile COMMAND optional
 *
 * where infile is the name of an input file in the following format:
 *
 * ----------- EXAMPLE INPUT -------------
 * Input file:
 * -------------------------------------------
 * Company,Title,Category,Location,Responsibilities,Minimum Qualifications,Preferred Qualifications
 * Google,TitleA,CategoryX,Tel Aviv,Everything and the rest, BS, MS
 * Google,TitleB,CategoryX,Tel Aviv,Everything and the rest, BS, MS
 * Google,TitleB,CategoryY,Houston,Everything and the rest, BS, MS
 * Google,TitleC,CategoryX,Jonesboro,Everything and the rest, BS, MS
 * -------------------------------------------
 * Available commands:
 * CATCOUNT, LOCATIONS
 *
 * The commands shown above are all of the commands that are supported
 * by this program. It is assumed that (except for some specific errors),
 * the input is well-formed, and matches the format shown above.
 */
