/*
 * AUTHOR: David Anderson
 * FILE: ArrayStack.java
 * ASSIGNMENT: Programming Assignment 6 - Stacks and Queues
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a Stack using an array as
 * a backing data structure. It implements the Stack Interface
 * and implements the various methods used in Java's traditional
 * Stack abstract data type.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 */
import java.util.Arrays;
import java.util.Objects;

public class ArrayStack implements StackInterface {

    private static final int INITIAL_CAPACITY = 1;
    private int capacity;
    private int[] array;
    private int index = 0;

    /*
     * Purpose: A default constructor that initiates the initial
     * capacity and creates a new int array.
     */
    public ArrayStack() {
        capacity = INITIAL_CAPACITY;
        array = new int[capacity];
    }

    /*
     * Purpose: This is a copy constructor that copies
     * all information from the passed ArrayStack and
     * creates a new instance with that imported data.
     *
     * @param arrayStack, is the ArrayStack object
     * to be copied.
     */
    public ArrayStack(ArrayStack arrayStack) {
        this.index = arrayStack.index;
        this.capacity = arrayStack.capacity;
        array = new int[capacity];
        if (capacity >= 0) System.arraycopy(arrayStack.array, 0, array, 0, capacity);
    }

    /*
     * Purpose: A method that can be called anywhere in
     * this class that creates a new array,
     * increases the size of the array by 1, and copies all
     * of the data from the previous array.
     */
    private void growArray() {
        // Grow the array by 1
        capacity++;
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /*
     * Purpose: A method that imitates the traditional Stack push
     * method; it checks to see if it first needs to grow the array,
     * and then it inserts the value at the current index (top of the stack).
     *
     * @param value, is the int value being pushed onto the stack.
     */
    @Override
    public void push(int value) {
        if (array.length <= index) {
            growArray();
        }

        array[index] = value;
        index++;
    }

    /*
     * Purpose: A method that imitates the traditional Stack pop
     * method; it creates a new array with one fewer positions,
     * and copies all elements into this new stack except for
     * the element that was popped.
     *
     * @return poppedInt, is the int that was popped off the top
     * of the stack.
     */
    @Override
    public int pop() {
            int poppedInt = array[array.length - 1];

            // Create another array of one size smaller
            int[] anotherArray = new int[array.length - 1];

            // Copy the elements from original array to the other array
            for (int i = 0, k = 0; i < array.length; i++) {

                // if the index is the removal element index
                // which in this case is the top of the stack
                if (i == index - 1) {
                    continue;
                }

                anotherArray[k++] = array[i];
            }
            array = anotherArray;
            capacity--;
            index--;
            // return the int that was popped off the top of the stack
            return poppedInt;

    }
    /*
     * Purpose: A method that imitates the traditional Stack
     * peek method; it simply returns the element being stored
     * at the end of the array.
     *
     * @return array[capacity - 1], which is the last element
     * in the array (the "top" of the stack)
     */
    @Override
    public int peek() {
        return array[capacity - 1];
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
        array = new int[INITIAL_CAPACITY];

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
