/*
 * AUTHOR: David Anderson
 * FILE: ArrayQueue.java
 * ASSIGNMENT: Programming Assignment 6 - Stacks and Queues
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a Queue using an array as
 * a backing data structure. It implements the Queue Interface
 * and implements the various methods used in Java's traditional
 * Queue abstract data type.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 */
import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue implements QueueInterface {


    private static final int INITIAL_CAPACITY = 1;
    private int capacity;
    private int[] array;
    private int index = 0;

    /*
     * Purpose: This is a copy constructor that copies
     * all information from the passed ArrayQueue and
     * creates a new instance with that imported data.
     *
     * @param arrayQueue, is the ArrayQueue object
     * to be copied.
     */
    public ArrayQueue(ArrayQueue arrayQueue) {
        this.capacity = arrayQueue.capacity;
        array = new int[capacity];
        if (capacity >= 0) System.arraycopy(arrayQueue.array, 0, array, 0, capacity);
    }

    /*
     * Purpose: A default constructor that initiates the initial
     * capacity and creates a new int array.
     */
    public ArrayQueue() {
        capacity = INITIAL_CAPACITY;
        array = new int[capacity];

    }
    /*
     * Purpose: A method that can be called anywhere in
     * this class that creates a new array,
     * increases the size of the array by 1, and copies all
     * of the data from the previous array.
     */
    private void growArray() {
        // Grow array size by 1
        int[] newArray = new int[capacity + 1];
        capacity++;
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    /*
     * Purpose: A method that imitates the traditional Queue enqueue
     * method; it checks to see if it first needs to grow the array,
     * and then it inserts the value at the current index (back of the queue).
     *
     * @param value, is the int value being passed into the queue.
     */
    @Override
    public void enqueue(int value) {
        if (array.length <= index) {
            growArray();
        }

        array[index] = value;
        index++;

    }
    /*
     * Purpose: A method that imitates the traditional Queue dequeue
     * method; it creates a new array with one fewer positions,
     * and copies all elements into this new queue except for
     * the element that was popped.
     *
     * @return dequeuedInt, is the int that was dequeued off the
     * front of the queue.
     */
    @Override
    public int dequeue() {
        int removalIndex = 0;
        int insertionIndex = 0;
        int dequeuedInt = array[0];

        // Create another array of size one less
        int[] anotherArray = new int[array.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0; i < array.length; i++) {

            // the removal index is 0
            if (i == removalIndex) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[insertionIndex] = array[i];
            insertionIndex++;

        }

        array = anotherArray;
        capacity--;
        // return the int that was first put into the queue
        return dequeuedInt;
    }
    /*
     * Purpose: A method that imitates the traditional Stack
     * peek method; it simply returns the element being stored
     * at the end of the array.
     *
     * @return array[0], which is the first element
     * in the array (the "front" of the queue).
     */
    @Override
    public int peek() {
        return array[0];
    }

    /*
     * Purpose: A method that checks the length of the
     * array. If the array length is less than 1, it returns
     * true; otherwise false.
     *
     * @return array.length <= 0, which is the boolean referring
     * to the array size.
     */
    @Override
    public boolean isEmpty() {
        return array.length <= 0;

    }

    /*
     * Purpose: A method that returns the size of the
     * array (aka the size of the stack).
     *
     * @return array.length, which is the size of the array
     * representing the Stack.
     */
    @Override
    public int size() {
        return array.length;
    }

    /*
     * Purpose: A method that deletes the current array
     * and all of its values, and creates a new array with
     * the default capacity.
     */
    @Override
    public void clear() {
        array = new int[capacity];
    }

    /*
     * Purpose: A method that displays all the elements
     * of the Stack as a String of the form "{ 1, 2, 3, }"
     *
     * @return string, which is the Stack information
     * displayed as a String.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int element : array) {
            s.append(element).append(", ");
        }
        return "{ "+ s +
                '}';
    }

    /*
     * Purpose: A method that checks the equality
     * of two objects. It first ensures that they are the same
     * class type, then checks the equality of their Strings.
     *
     * @param o, is the Object being passed to test equality
     * with the current Object.
     *
     * @return this.toString().equals(o.toString()),
     * which is the true/false value of the Object comparison.
     */
    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        return this.toString().equals(o.toString());

    }

    /*
     * Purpose: A method that creates a numeric hashcode
     * for the current Stack object.
     *
     * @return result, which is the numeric hashcode
     * for the current Object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, index);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
