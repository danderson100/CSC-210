/*
 * AUTHOR: David Anderson
 * FILE: ListQueue.java
 * ASSIGNMENT: Programming Assignment 6 - Stacks and Queues
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a Queue using a List of ListNodes
 *  as a backing data structure. It implements the Queue Interface
 * and implements the various methods used in Java's traditional
 * Queue abstract data type.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 */

import java.util.Objects;

public class ListQueue implements QueueInterface {

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

    private int size;
    private ListNode first;

    /*
     * Purpose: A default constructor that allows a ListStack
     * to be created with no passed parameters.
     */
    public ListQueue() {

    }

    /*
     * Purpose: This is a copy constructor that copies
     * all information from the passed ListStack and
     * creates a new instance with that imported data.
     *
     * @param listStack, is the listStack object
     * to be copied.
     */
    public ListQueue(ListQueue listQueue) {
        this.size = listQueue.size;
        this.first = listQueue.first;

    }
    /*
     * Purpose: A method that imitates the traditional Queue enqueue
     * method; it checks to see if the list is empty, and if it isn't
     * it inserts the value at the current position (back of the queue).
     * It then increments the size of the list.
     *
     * @param value, is the int value being placed into the queue.
     */
    @Override
    public void enqueue(int value) {
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
     * Purpose: A method that imitates the traditional Queue dequeue
     * method; it first stores the element being dequeued, and then it
     * simply makes the first ListNode the next one in the queue.
     *
     * @return dequeueValue, is the int that was dequeued off the
     * front of the queue.
     */
    @Override
    public int dequeue() {
        int deQueueValue = first.getData();
        first = first.next;
        size--;
        return deQueueValue;
    }
    /*
     * Purpose: A method that imitates the traditional Queue
     * peek method; it simply returns the element being stored
     * at the front of the queue
     *
     * @return first.getData(), which is the first element
     * in the queue (the "front" of the queue).
     */
    @Override
    public int peek() {
        return first.getData();
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
