/*
 * AUTHOR: David Anderson
 * FILE: Song.java
 * ASSIGNMENT: Programming Assignment 4 - Cappriotify
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores each individual song and provides functions
 * for retrieving, storing, and removing information related to their songs (like
 * song title, artist name, and the number of times played).
 * It supplements the other classes in the program and provides encapsulation and improved
 * readability and code reusability.
 */

public class Song {

    private final String title;
    private final String artist;
    private int timesPlayed = 0;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
    /*
     * Purpose: A method that returns the title of the
     * stored song.
     */
    public String getTitle() {
        return title;
    }
    /*
     * Purpose: A method that returns the name song's
     * artist.
     */
    public String getArtist() {
        return artist;
    }
    /*
     * Purpose: A method that returns the number of times
     * this song has been played by the current user.
     */
    public int getTimesPlayed() {
        return timesPlayed;
    }
    /*
     * Purpose: This method increments the timesPlayed
     * variable by one and "plays" the song by calling
     * the toString function and printing the song information.
     */
    public void play() {
        timesPlayed += 1;
        System.out.println(toString());
    }

    /*
     * Purpose: This toString method returns the information
     * about the stored song in the following format:
     *
     * [Song Name] by [Artist name], [numTimesPlayed] play(s)
     *
     */
    @Override
    public String toString() {
        return title + " by " + artist + ", " + timesPlayed + " play(s)";
    }
}
