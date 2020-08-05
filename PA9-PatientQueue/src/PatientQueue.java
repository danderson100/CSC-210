/*
 * AUTHOR: David Anderson
 * FILE: PatientQueue.java
 * ASSIGNMENT: Programming Assignment 9 - Patient Queue
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a Priority Queue that stores instances
 * of the secondary Patient class in order of urgency (1 being most urgent, highest number
 * being least urgent). The Patient class stores the patient's name as a String and their
 * priority as an integer. After storing each new patient in the queue (an array), it checks
 * the priority and uses "bubble up" or "bubble down" sorting to achieve priority min-heap ordering.
 * This program implements the following methods, most of which are used in Java's traditional
 * PriorityQueue data structure:
 *
 * enqueue(String name, int priority), enqueue(Patient patient), dequeue(), peek(), peekPriority(),
 * changePriority(String name, int newPriority), isEmpty(), clear(), size(), and toString().
 *
 * It also contains a several private helper methods to more efficiently complete tasks and/or reuse code.
 *
 * Special circumstances:
 * Priority tie -- in the case of a priority tie, the program will use the first letter of
 * the first name to determine who goes first (higher in the alphabet gets priority (e.g A before D)).
 * Duplicate patient names/priorities -- if this happens, both patients are stored in the queue. If
 * changePriority() is called, it will only affect the first found patient with that name.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 * EXAMPLE OUTPUT: toString()
 * ----------------------------------------------------------
 * {Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}
 * ----------------------------------------------------------
 */

import java.util.NoSuchElementException;

public class PatientQueue {

    private int capacity = 10;
    private int size = 0;
    //created an array of Patient objects for the priority queue
    private Patient[] patients = new Patient[capacity];

    public PatientQueue() {

    }

    /*
     * Purpose: This method takes the passed information,
     * creates a new Patient object, and calls the next enqueue method.
     *
     * @param name, is the String containing the patient's name.
     *
     * @param, priority, is the integer indicating their priority.
     */
    void enqueue(String name, int priority) {
        Patient patient = new Patient(name, priority);
        enqueue(patient);

    }

    /*
     * Purpose: This method takes in a new Patient object and
     * adds it to the priority queue. If there isn't enough room,
     * it doubles the size of the queue and copies the elements.
     *
     * @param patient, is the Patient being added to the priority queue.
     */
    void enqueue(Patient patient) {
        //this way we start at index 1 instead of 0
        int index = size + 1;
        if (size < capacity - 1) {
            if (patients[index] == null) {
                //add patient to queue at first empty spot in array
                patients[index] = patient;
                size++;
                bubbleUp(index);
            }
            //if it doesn't have room we double the size and copy the elements into new array
        } else {
            capacity *= 2;
            Patient[] resizedPatients = new Patient[capacity];

            //copy all elements from patients[] into resizedPatients[]
            if (size >= 0) System.arraycopy(patients, 0, resizedPatients, 0, size + 1);
            patients = resizedPatients;
            enqueue(patient);

        }
    }

    /*
     * Purpose: This is a private helper method that performs the "bubble up"
     * sorting mechanism. It determines which index holds the "parent" in the queue,
     * checks each priority, and swaps the child with the parent if the child is more
     * urgent
     *
     * @param childIndex, is the index of the patient that was just added to the
     * priority queue.
     */
    private void bubbleUp(int childIndex) {
        if (childIndex >= 2) {
            int parentIndex = childIndex / 2;
            int parentPriority = patients[parentIndex].priority;
            int childPriority = patients[childIndex].priority;
            //rearrange into correct binary min-heap order
            do {
                if (childPriority < parentPriority) {
                    //call swap method to switch them
                    swap(parentIndex, childIndex);
                    parentPriority = patients[parentIndex].priority;
                    childPriority = patients[childIndex].priority;
                } else if (childPriority == parentPriority) {
                    boolean parentFirstNameEarlier = breakTie(childIndex, parentIndex);
                    if (parentFirstNameEarlier) {
                        //don't swap
                        break;
                    } else {
                        //swap
                        swap(parentIndex, childIndex);
                    }
                }
                //this checks to see if it has a new parent and needs to attempt bubble up again
                if ((parentIndex / 2) > 0) {
                    bubbleUp(parentIndex);
                }
                //continue this loop until parent priority > than child's
            } while (childPriority < parentPriority);
        }
    }

    /*
     * Purpose: This is a private helper method that swaps
     * a parent with its child in the priority queue.
     *
     * @param parentIndex, is the array index of the parent.
     *
     * @param childIndex, is the array index of the child.
     */
    private void swap(int parentIndex, int childIndex) {
        Patient temp = patients[parentIndex];
        patients[parentIndex] = patients[childIndex];
        patients[childIndex] = temp;

    }

    /*
     * Purpose: This is a private helper method that performs the "bubble down"
     * sorting mechanism. It determines which indexes hold the two children of
     * the parent in the queue. Then it checks the priorities and swaps the
     * more urgent child with the parent. It repeats this process until
     * min-heap ordering is achieved.
     *
     * @param parentIndex, is the index of the parent Patient object after a dequeue
     * or priority change has taken place.
     */
    private void bubbleDown(int parentIndex) {
        int child1Index = parentIndex * 2;
        int child2Index = (parentIndex * 2) + 1;
        //this flag will see if we need to continue bubbling down or if parent is more urgent than children
        boolean flag = false;
        if (child1Index > size || patients[child1Index] == null) {
            //then the parent has no children so we should break out of this method
            flag = true;
        } else {
            //here we can call compareChildPriorities to determine which child to swap with
            int swapChildIndex = compareChildPriorities(child1Index, child2Index);
            int childPriority = patients[swapChildIndex].priority;

            int parentPriority = patients[parentIndex].priority;
            if (parentPriority > childPriority) {
                //use swap method to swap parent with child
                swap(parentIndex, swapChildIndex);
                parentIndex = swapChildIndex;
            } else {
                flag = true;
            }
        }

        if (parentIndex * 2 <= size && !flag) {
            //uses recursion to continue until order is achieved
            bubbleDown(parentIndex);
        }
    }

    /*
     * Purpose: This is a private helper method that compares the priorities of the
     * two children and returns the index of the more urgent child.
     *
     * @param child1Index, is the index of the first child.
     *
     * @param child2Index, is the index of the second child.
     *
     * @return childIndex, is the index of the more urgent child.
     */
    private int compareChildPriorities(int child1Index, int child2Index) {
        int child1Priority;
        int child2Priority;
        if (child1Index <= size) {
            child1Priority = patients[child1Index].priority;
        } else {
            child1Priority = 100000;
        }
        if (child2Index <= size) {
            child2Priority = patients[child2Index].priority;
        } else {
            child2Priority = 100001;
        }

        if (child1Priority > child2Priority) {
            return child2Index;
        } else if (child2Priority > child1Priority) {
            return child1Index;
        } else {
            boolean checkNames = breakTie(child1Index, child2Index);
            if (checkNames) {
                return child2Index;
            } else {
                return child1Index;
            }
        }
    }

    /*
     * Purpose: This private boolean method is called in the case of a priority
     * tie; it uses the ASCII value of the first letter of each name to determine
     * which comes earlier in the alphabet (higher ASCII value means higher
     * in the alphabet).
     *
     * @param firstPatientIndex, is the index of the first patient being compared.
     *
     * @param secondPatientIndex, is the index of the other patient.
     *
     * @return diff > 0, is the boolean that returns true or false depending
     * on the patients' names.
     */
    private boolean breakTie(int firstPatientIndex, int secondPatientIndex) {

        String firstPatientName = patients[firstPatientIndex].name.toLowerCase();
        String otherName = patients[secondPatientIndex].name.toLowerCase();
        //compare first letters
        char firstLetter = firstPatientName.charAt(0); //ex. name: Danny
        char otherFirstLetter = otherName.charAt(0); //ex. name: August
        int diff = firstLetter - otherFirstLetter; //uses ASCII values to determine which letter comes earlier
        //true if the second patient's name is earlier in the alphabet
        //false if the first patient's name is higher
        return diff > 0;

    }

    /*
     * Purpose: This method removes the front-most element (most urgent)
     * from the priority queue and returns the name of that patient. It always
     * calls the bubbleDown() method after a dequeue. It throws
     * an exception if the queue is empty.
     *
     * @return name, is the first name of the patient being removed
     * from the priority queue.
     */
    String dequeue() {
        String name;
        if (patients[1] == null || size == 0) {
            throw new NoSuchElementException("The queue is empty!");
        } else {
            name = patients[1].name;
            patients[1] = patients[size];
            patients[size] = null;
            size--;
            bubbleDown(1);
        }

        return name;
    }

    /*
     * Purpose: This method gets the first name of the patient
     * at the front of the queue, but does NOT remove them from
     * the priority queue. Throws an exception if the queue is empty.
     *
     * @return patients[1].name, is the name of the most urgent patient.
     */
    String peek() {
        if (patients[1] == null || size == 0) {
            throw new NoSuchElementException("the queue is empty!");
        } else {
            return patients[1].name;
        }
    }

    /*
     * Purpose: This method gets the priority number of the patient
     * at the front of the queue, but does NOT remove them from
     * the priority queue. Throws an exception if the queue is empty.
     *
     * @return patients[1].priority, is the integer priority of the most urgent patient.
     */
    int peekPriority() {
        if (patients[1] == null || size == 0) {
            throw new NoSuchElementException("the queue is empty!");
        } else {
            //get the priority of the patient at the front of the array (index 1)
            return patients[1].priority;
        }
    }

    /*
     * Purpose: This method takes a patient's name and a new
     * value for her priority, and updates it in the queue. If the
     * new priority is less urgent it performs bubbleDown, and if it's
     * more urgent it performs bubbleUp.
     *
     * @param name, is the name of the patient being updated.
     *
     * @param newPriority, is the integer value of their new
     * level of urgency.
     */
    void changePriority(String name, int newPriority) {
        //search the array for the patient w/ this name, and
        //set their new priority
        int count = -1;
        for (Patient patient : patients) {
            count++;
            if (patient != null && patient.name.equals(name)) {
                int oldPriority = patient.priority;
                patient.priority = newPriority;
                if (newPriority > oldPriority) {
                    bubbleDown(count);
                } else if (newPriority < oldPriority) {
                    bubbleUp(count);
                } else {
                    continue;
                }
                break;
            }

        }
    }

    /*
     * Purpose: A method that checks to see if the current
     * size of the PatientQueue is 0, and thus empty.
     *
     * @return size == 0, which is the boolean referring
     * to whether the array is empty.
     */
    boolean isEmpty() {
        return size == 0;
    }

    /*
     * Purpose: A method that returns the size of the
     * PatientQueue (aka the number of objects in the array).
     *
     * @return size, which is the current number
     * of items in the PatientQueue.
     */
    int size() {
        return size;
    }

    /*
     * Purpose: A method that clears all of the previously stored
     * elements, sets the existing patients array to a new array with
     * null values and capacity 10, and sets size back to 0.
     */
    void clear() {
        //create a new array with initial capacity and delete the old one
        patients = new Patient[10];
        size = 0;
        capacity = 10;
    }

    /*
     * Purpose: This method allows users to get a String containing the current ordering
     * of patients in the PatientQueue along with their priorities. It returns it in the
     * following format: {Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}
     *
     * @return { + s.toString() + }, is the String of patients and their priorities.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String prefix = "";

        for (Patient patient : patients) {
            if (patient != null) {
                s.append(prefix);
                prefix = ", ";
                s.append(patient.toString());
            }
        }

        return "{" + s.toString() + "}";
    }
}
