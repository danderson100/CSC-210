/*
 * AUTHOR: David Anderson
 * FILE: Library.java
 * ASSIGNMENT: Programming Assignment 4 - Cappriotify
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores a user's song Library and provides functions
 * for retrieving, storing, and removing information related to their songs.
 * It supplements the other classes in the program and provides encapsulation and improved
 * readability and code reusability.
 *
 * USAGE:
 * java PA4Main infile
 *
 * where infile is the name of an input file in the following format:
 *
 * ----------- EXAMPLE INPUT -------------
 * Input file:
 * -------------------------------------------
 * Ed Sheeran/Shape of You
 * John Mayer/New Light
 * Drake/God's Plan
 * Marshmello/Happier
 * Post Malone/Congratulations
 * Kendrick Lamar/HUMBLE.
 * Nirvana/In Bloom
 * XXXTENTACION/SAD!
 * Portugal. The Man/Feel It Still
 * The Beatles/Come Together
 * -------------------------------------------
 *
 */


import java.util.*;

public class Library {

    //I used a TreeMap because it stores info alphabetically and has low complexity
    private final Map<String, Song> songTreeMap = new TreeMap<>();

    //default constructor that takes no parameters
    public Library() {

    }

    /*
     * Purpose: A method that returns the song the user requested based on
     * the song's title.
     *
     * @param title, is the String containing the song's name.
     *
     * @return s, which is the song returned as long as it is
     * in the user's library TreeMap
     */
    public Song getSong(String title) {
        Song s = null;
        if (songTreeMap.containsKey(title)) {
            s = songTreeMap.get(title);
        }
        return s;
    }

    /*
     * Purpose: A method that returns all songs in a user's
     * song library in the form of a List of Strings.
     *
     * @return allSongs, which is a list of all the songs with
     * song name, artist name, and times played.
     */
    public List<String> getAllSongs() {
        List<String> titles = new ArrayList<>(songTreeMap.keySet());
        List<String> allSongs = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < songTreeMap.size(); i++) {
            s.append(prefix);
            prefix = "\n";

            Song song = songTreeMap.get(titles.get(i));
            String artist = song.getArtist();
            String title = titles.get(i);
            int timesPlayed = song.getTimesPlayed();
            s.append(title).append(" by ").append(artist).append(", ").append(timesPlayed).append(" play(s)");
            allSongs.add(s.toString());
        }

        return allSongs;
    }

    /*
     * Purpose: A method that adds the song the user requested and stores
     * it in the TreeMap with the key being the song name.
     *
     * @param song, is a Song object that the user would like to add
     * to their library.
     *
     */
    public void addSong(Song song) {
        songTreeMap.put(song.getTitle(), song);

    }

    /*
     * Purpose: A method that removes the song the user requested.
     *
     * @param song, is the Song object that the user would like removed
     * from his/her library.
     *
     */
    public void removeSong(Song song) {
        songTreeMap.remove(song.getTitle());
        System.out.println("Removed song.");
    }
    /*
     * Purpose: This toString method uses the TreeMap keySet
     * and StringBuilder to return a list of all songs in the
     * library, in the following format:
     *
     * [Song Name] by [Artist name], [numTimesPlayed] play(s)
     *
     */

    @Override
    public String toString() {

        List<String> titles = new ArrayList<>(songTreeMap.keySet());

        StringBuilder s = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < songTreeMap.size(); i++) {
            s.append(prefix);
            prefix = "\n";

            Song song = songTreeMap.get(titles.get(i));
            String artist = song.getArtist();
            String title = titles.get(i);
            int timesPlayed = song.getTimesPlayed();
            s.append(title).append(" by ").append(artist).append(", ").append(timesPlayed).append(" play(s)");
        }


        return s.toString();

    }
}
