/*
 * AUTHOR: David Anderson
 * FILE: UserCollection.java
 * ASSIGNMENT: Programming Assignment 4 - Cappriotify
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class stores all of the users that have been created during the lifecycle
 * of the program's current operation and validates credentials; it also provides functions
 * for retrieving, storing, and removing information that are accessed by the main method.
 * It supplements the other classes in the program and provides encapsulation and improved
 * readability and code reusability.
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCollection {

    //I used two data structures because HashMap is a low complexity way to supplement the User ArrayList
    private final List<User> allUsers = new ArrayList<>();
    private final Map<String, String> credentials = new HashMap<>();

    public UserCollection() {
    }

    /*
     * Purpose: A method that checks to see if a user exists
     * in the credentials HashMap
     *
     * @param username, is the String that contains the username
     * being checked.
     *
     * @return, true/false depending on whether a match was found.
     *
     */
    public boolean userExists(String username) {
        boolean flag = false;
        if (credentials.containsKey(username)) {
            flag = true;
        }
        return flag;
    }

    /*
     * Purpose: A method that validates a user's information
     * against the credentials HashMap and returns the desired
     * User object if it exists
     *
     * @param username, is the username entered by the user.
     *
     * @param password, is the password entered by the user.
     *
     * @return user, is the User object that was stored when
     * the user created their account.
     *
     */
    public User login(String username, String password) {
        User user = null;
        if (credentials.containsKey(username)) {
            if (password.equals(credentials.get(username))) {
                user = new User(username, password);
            }
        }

        return user;
    }

    /*
     * Purpose: A method that gets called when a user creates
     * an account. Stores the user information in two places so
     * the username/password can be checked efficiently.
     *
     * @param add, is the User object being added to the storage
     * data structures.
     *
     */
    public void addUser(User add) {
        allUsers.add(add);
        credentials.put(add.getName(), add.getPassword());
    }

    /*
     * Purpose: This toString method returns appropriate information
     * about the user (i.e. username and playlist names, but not password)
     * in the following format:
     *
     * { [Username],  [Numplaylists] playlists }
     *
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < credentials.size(); i++) {
            s.append(prefix);
            prefix = ", ";

            User user = allUsers.get(i);
            String userName = user.getName();
            int numPlaylists = user.getNumPlaylists();

            s.append(userName).append(",  ").append(numPlaylists).append(" playlists ");
        }


        return "{ " + s.toString() + " }";
    }
}

