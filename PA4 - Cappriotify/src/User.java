/*
 * AUTHOR: David Anderson
 * FILE: User.java
 * ASSIGNMENT: Programming Assignment 4 - Cappriotify
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores a user's information like username, password,
 * number of playlists, and the playlists themselves; it also provides functions
 * for retrieving, storing, and removing information related to their account.
 * It supplements the other classes in the program and provides encapsulation and improved
 * readability and code reusability.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class User {

    private final String name;
    private final String password;
    private int numPlaylists = 0;
    private final TreeMap<String, Playlist> playlists = new TreeMap<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    /*
     * Purpose: A method that returns the username.
     */
    public String getName() {
        return name;
    }
    /*
     * Purpose: A protected method that returns the
     * user's password.
     */
    protected String getPassword() {
        return password;
    }
    /*
     * Purpose: A method that returns the number of
     * playlists the user has created.
     */
    public int getNumPlaylists() {
        return numPlaylists;
    }

    /*
     * Purpose: A method that checks to see if the inputted
     * password matches the password on file for this user.
     *
     * @param password, is the Song object that will be added
     * to the playlist.
     *
     * @return true/false, depending on whether the password
     * was valid and matched.
     */
    public boolean attemptLogin(String password) {
        if (password.equals(this.password)) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * Purpose: A method that takes a Playlist and allows a user
     * to add a playlist of songs and store it. It also increments
     * the numPlaylists value.
     *
     */
    public void addPlaylist(Playlist newPlaylist) {
        playlists.put(newPlaylist.getName(), newPlaylist);
        numPlaylists += 1;
    }
    /*
     * Purpose: A method that returns a List of Playlists
     * that the user has created.
     */
    public List<Playlist> getPlaylists() {
        List<String> playlistNames = new ArrayList<>(playlists.keySet());
        List<Playlist> currentPlaylists = new ArrayList<>();
        for (String playlistName : playlistNames) {
            currentPlaylists.add(playlists.get(playlistName));
        }

        return currentPlaylists;
    }

    /*
     * Purpose: A method that allows a user to select a playlist
     * that he/she previously created by inputting the playlist name.
     *
     * @param name, is the name they chose for their playlist.
     *
     */
    public void selectPlaylist(String name) {
        Playlist playlist = null;
        if (playlists.containsKey(name)) {
            playlist = playlists.get(name);
        }

        assert playlist != null;
        playlist.play();

    }

    /*
     * Purpose: This toString method returns the information
     * about the stored song in the following format:
     *
     * [Username],  [numPlaylists] [playlists].
     *
     */
    @Override
    public String toString() {
        return name + ", " + numPlaylists + " " + playlists;
    }
}
