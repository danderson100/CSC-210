/*
 * AUTHOR: David Anderson
 * FILE: Playlist.java
 * ASSIGNMENT: Programming Assignment 4 - Cappriotify
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores a user's playlists and provides functions
 * for retrieving, storing, and removing information related to their playlists. Users
 * can also retrieve information about the songs in their playlist.
 * It supplements the other classes in the program and provides encapsulation and improved
 * readability and code reusability.
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final String name;

    /*
     * I used an arraylist of Songs to store the contents of each playlist
     * because it is easy to organize and we don't know how many songs there
     * will be.
     */
    private List<Song> contents = new ArrayList<>();

    public Playlist(String name, List<Song> contents) {
        this.name = name;
        this.contents = contents;
    }

    public Playlist(String name) {
        this.name = name;
    }

    /*
     * Purpose: A method that returns the name of the user's
     * playlist.
     */
    public String getName() {
        return name;
    }
    /*
     * Purpose: A method that allows a user to add a song to his/her
     * playlist, which provides text confirmation.
     *
     * @param song, is the Song object that will be added
     * to the playlist.
     *
     */
    public void addSong(Song song) {
        contents.add(song);
        System.out.println(song + " added.");
    }
    /*
     * Purpose: A method that loops through the user's playlist in the
     * contents ArrayList and "plays" each song.
     */
    public void play() {
        for (Song song : contents) {
            System.out.println(song.getTitle() + " by " + song.getArtist() + ", " + song.getTimesPlayed() + " play(s)");
        }

    }
    /*
     * Purpose: A method that removes a song from the user's playlist
     * and provides text confirmation.
     *
     * @param song, is the song being removed.
     */
    public void removeSong(Song song) {
        contents.remove(song);
        System.out.println("Song removed");
    }

}
