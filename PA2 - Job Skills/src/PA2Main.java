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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PA2Main {

    public static void main(String[] args) {
        //I found it easiest to put the commands into a List and check it for validity
        List<String> validCommands = new ArrayList<>();
        validCommands.add("CATCOUNT");
        validCommands.add("LOCATIONS");

        String fileName = args[0];
        String command = args[1];
        String optionalInput = null;
        //I created this statement to check for the optional argument
        if (args.length == 3) {
            optionalInput = args[2];
        }
        if (!validCommands.contains(command)) {
            System.out.println("Invalid Command");
        } else {

            List<String> fileInfo = createData(fileName);
            //this will store all of my Job objects in a HashMap
            Map<String, Job> jobsMap = createJobs(fileInfo);
            List<String> sortedJobs = new ArrayList<>(jobsMap.keySet());
            Collections.sort(sortedJobs);

            List<String> allCategories = sort("categories", jobsMap, sortedJobs);
            List<String> allLocations = sort("locations", jobsMap, sortedJobs);
            //this will send the command-line arguments and print the desired results
            printResults(command, optionalInput, allCategories, allLocations, jobsMap);
        }
    }

    /*
     * Purpose: A method that returns a List of Strings containing all
     * categories found in the input file sorted alphabetically.
     *
     * @param jobsMap, is the HashMap containing every found job in the file.
     *
     * @param sortedJobs, is the List of job names, which are used to identify
     * and get the values for that particular job.
     *
     * @return sortedCategories, which is the List<String> of sorted categories
     * that will be returned to the Main method and printed out.
     */
    private static List<String> sort(String choice, Map<String, Job> jobsMap, List<String> sortedJobs) {

        if (choice.equals("categories")) {
            List<String> sortedCategories = new ArrayList<>();
            //This iterates over the jobs, and uses the job title (Job1, Job2, etc.) to find its category in the HashMap
            for (String job : sortedJobs) {
                String category = jobsMap.get(job).getCategory();
                if (!sortedCategories.contains(category)) {
                    sortedCategories.add(category);
                }
            }
            sortedCategories.remove(0);
            Collections.sort(sortedCategories);
            return sortedCategories;

        } else {
            List<String> allLocations = new ArrayList<>();
            for (String job : sortedJobs) {
                String location = jobsMap.get(job).getLocation();
                if (!allLocations.contains(location)) {
                    allLocations.add(location);
                }

            }
            allLocations.remove(0);
            Collections.sort(allLocations);
            return allLocations;
        }
    }

    /*
     * Purpose: This method takes all of the parameters and uses them to
     * print out the results from the file.
     *
     * @param command, is the string command that tells the method which operation
     * to perform.
     *
     * @param optionalInput, is the optional command that narrows the search results.
     *
     * @param allCategories, is the List<String> containing all job categories in the file.
     *
     * @param allLocations, is the List<String> containing all job locations in the file.
     *
     * @param jobsMap, is the HashMap containing all of the Job objects.
     */
    public static void printResults(String command, String optionalInput, List<String> allCategories,
                                    List<String> allLocations, Map<String, Job> jobsMap) {
        //I created this ArrayList of job names so that I could search for each job easily and find the categories/locations
        List<String> jobs = new ArrayList<>(jobsMap.keySet());
        if (command.equals("CATCOUNT")) {
            System.out.println("Number of positions for each category");
            System.out.println("-------------------------------------");
            for (String category : allCategories) {
                System.out.println(category + ", " +
                        countCategories(jobsMap).get(category));
            }
        }

        if (command.equals("LOCATIONS") & optionalInput != null) {
            System.out.println("LOCATIONS for category: " + optionalInput);
            System.out.println("-------------------------------------");
            for (String location : allLocations) {
                //I included this test so that it wouldn't print the location name will a "null" value
                if (countLocations(optionalInput, jobs, jobsMap).get(location) != null) {
                    System.out.println(location + ", " + countLocations(optionalInput, jobs, jobsMap).get(location));
                }
            }
        }
    }

    /*
     * Purpose: A method used to iterate over the List<String> of jobs and
     * count the number of jobs at each location.
     *
     * @param optionalInput, is the optional input that narrows the scope of the results.
     *
     * @param sortedJobs, is the List<String> of all the job titles, so the method
     * can find them in the HashMap.
     *
     * @param jobsMap, is the HashMap containing all Job objects.
     *
     * @return locationsCount, which is a HashMap containing locations as keys and the
     * number of times they appear as values.
     */
    public static Map<String, Integer> countLocations(String optionalInput, List<String> sortedJobs,
                                                      Map<String, Job> jobsMap) {
        //this Map will store each location and the count
        Map<String, Integer> locationsCount = new HashMap<>();
        for (String job : sortedJobs) {
            String category = jobsMap.get(job).getCategory();
            if (category.equals(optionalInput)) {
                String location = jobsMap.get(job).getLocation();
                if (locationsCount.containsKey(location)) {
                    int currentCount = locationsCount.get(location);
                    currentCount++;
                    locationsCount.put(location, currentCount);
                } else {
                    locationsCount.put(location, 1);
                }
            }
        }
        return locationsCount;
    }

    /*
     * Purpose: A short method that takes the file name as a parameter and
     * uses a scanner to extract all of the data. It puts this data into
     * a List<String> called fileStrings
     *
     * @param fileName, is the String with the fileName that was passed as
     * command-line argument
     *
     * @return fileStrings, which is the List<String> containing all the lines
     * from the file.
     */
    public static List<String> createData(String fileName) {
        List<String> fileStrings = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileStrings.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return fileStrings;
    }

    /*
     * Purpose: A method that uses the private class Job to create a new Job
     * object for each job in the file, and then insert that object as a value
     * in the jobsMap HashMap
     *
     * @param fileStrings, is the List<String> containing all of the job information
     * where each element is a String with all job information
     *
     * @return jobsMap, which is a new HashMap containing keys job1, job2, ...job(n)
     * for every job in the file, and assigning each Job object as a value.
     */
    public static Map<String, Job> createJobs(List<String> fileStrings) {
        Map<String, Job> jobsMap = new HashMap<>();

        for (int i = 0; i < fileStrings.size(); i++) {
            List<String> job = Arrays.asList(fileStrings.get(i).split(","));
            if (job.size() > 4) {
                jobsMap.put("job" + (i + 1), new Job(job.get(0), job.get(1), job.get(2), job.get(3),
                        job.get(4), job.get(5), job.get(6)));
                //since some jobs had blanks for responsibilities and other areas, I added this condition
            } else {
                jobsMap.put("job" + (i + 1), new Job(job.get(0), job.get(1), job.get(2), job.get(3),
                        null, null, null));
            }
        }
        return jobsMap;
    }

    /*
     * Purpose: A method that takes the HashMap jobsMap and searches through
     * each job to find their associated categories. It then returns another map
     * with the category as a key and its count as the value.
     *
     * @param jobsMap, is the HashMap containing all Job objects and their associated
     * keys.
     *
     * @return categoriesCount, which is the new HashMap containing each category as a key
     * and the number of times it appeared in the file as the value.
     */
    public static Map<String, Integer> countCategories(Map<String, Job> jobsMap) {
        List<String> sortedJobs = new ArrayList<>(jobsMap.keySet());
        Collections.sort(sortedJobs);
        //this stores all categories and their current count
        Map<String, Integer> categoriesCount = new HashMap<>();
        for (String job : sortedJobs) {
            String category = jobsMap.get(job).getCategory();
            if (categoriesCount.containsKey(category)) {
                int currentCount = categoriesCount.get(category);
                currentCount++;
                categoriesCount.put(category, currentCount);
            } else {
                categoriesCount.put(category, 1);
            }
        }
        return categoriesCount;
    }

    /*
     * Purpose: Since the files contained a list of Jobs that all had similar
     * characteristics and categories, I thought it made sense to create a Job
     * class and instantiate a new Job object for each job listed in the document. I only
     * kept the parameters category and location since those were the only two commands, but
     * this would allow me to easily add other commands like TITLE, RESPONSIBILITIES,
     * etc.
     */
    private static class Job {

        private final String category;
        private final String location;
        //I created the constructor because I would know all of the input after reading the file
        public Job(String company, String title, String category,
                   String location, String responsibilities, String minQual, String prefQual) {
            this.category = category;
            this.location = location;
        }

        public String getCategory() {
            return category;
        }

        public String getLocation() {
            return location;
        }
    }
}