/*
 * AUTHOR: David Anderson
 * FILE: ListStack.java
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
import java.util.Objects;

public class ListStack implements StackInterface {

    /*
     * Purpose: This is a private nested class that
     * creates individual nodes for the linked list that
     * will back this Stack implementation. It allows
     * the class to store and get nodes, and to point to
     * the next node in the list.
     */
    private static class ListNode {
        int data;
        ListNode next;

        // Creates a terminal ListNode containing null as data.
        public ListNode() {
            this(0, null);
        }

        // Creates a terminal ListNode with the specified data.
        public ListNode(int data) {
            this(data, null);
        }

        // Creates a ListNode with the specified data and next node.
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

    }

    private int size = 0;
    private ListNode first;

    /*
     * Purpose: A default constructor that allows a ListStack
     * to be created with no passed parameters.
     */
    public ListStack() {

    }

    /*
     * Purpose: This is a copy constructor that copies
     * all information from the passed ListStack and
     * creates a new instance with that imported data.
     *
     * @param listStack, is the listStack object
     * to be copied.
     */
    public ListStack(ListStack listStack) {
        this.size = listStack.size;
        this.first = listStack.first;

    }

    /*
     * Purpose: A method that imitates the traditional Stack push
     * method; it checks to see if the list is empty, and if it isn't
     * it inserts the value at the current position (top of the stack).
     * It then increments the size of the list.
     *
     * @param value, is the int value being pushed onto the stack.
     */
    @Override
    public void push(int value) {
        if (first == null) {
            first = new ListNode(value);
        } else {
            ListNode current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
        size++;
    }

    /*
     * Purpose: A method that imitates the traditional Stack pop
     * method; it checks to see if we are removing the first element, and
     * if not then it loops over the nodes until it finds the last one
     * and deletes it from the stack after storing the value. It
     * then decrements the size by one.
     *
     * @return value, is the int that was popped off the top
     * of the stack.
     */
    @Override
    public int pop() {
        int value = 0;
        int index = size - 1;

        if (index == 0) {
            first = first.next;
        } else {
            ListNode current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
                value = current.next.getData();
            }
            current.next = current.next.next;

        }
        size--;
        return value;
    }

    /*
     * Purpose: A method that imitates the traditional Stack
     * peek method; it simply returns the element being stored
     * at the end of the array.
     *
     * @return current.next.getData(), which is the last element
     * in the list of Nodes (the "top" of the stack)
     */
    @Override
    public int peek() {
        ListNode current = first;
        for (int i = 0; i < size - 2; i++) {
            current = current.next;
        }
        return current.next.getData();
    }

    /*
     * Purpose: A method that checks to see if the
     * first ListNode is null; if so, it returns true
     * that the list is empty.
     *
     * @return first == null, which is the boolean referring
     * to whether the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }
    /*
     * Purpose: A method that returns the size of the
     * list (aka the number of created nodes).
     *
     * @return size, which is the current number
     * of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }
    /*
     * Purpose: A method that deletes the current first ListNode
     * and creates a new ListNode in its place, thus deleting all elements.
     */
    @Override
    public void clear() {
        first = new ListNode();
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
        ListNode current = first;
        for (int i = 0; i < size; i++) {
            s.append(current.getData()).append(", ");
            current = current.next;

        }
        return "{ " + s +
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
        return Objects.hash(size, first);
    }
}
