/*
 * AUTHOR: David Anderson
 * FILE: ListStack.java
 * ASSIGNMENT: Programming Assignment 7 - Generic Stacks and Queues
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a generic Queue using an linked list as
 * a backing data structure. It implements a custom generic Queue Interface
 * and implements the following methods, which are also used in Java's traditional
 * Queue abstract data type:
 *
 * enqueue(), dequeue(), peek(), isEmpty(), clear(), size(),
 * equals(), toString(), and hashCode().
 *
 * The program uses a private nested class, ListNode<E> that takes a
 * type parameter as an element (E) and stores it in a linked list with the
 * initial data and a ListNode<E> variable acting as a "pointer", similar
 * to Java's LinkedList class.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 * EXAMPLE OUTPUT:
 * This program generates no output, but simply functions as a customized Stack
 * and Queue.
 *
 */

import java.util.Objects;

public class ListQueue<E> implements QueueInterface<E> {

    /*
    * This private nested class provides individual nodes so the
    * data can be stored in a similar fashion to a traditional
    * LinkedList.
     */
    private static class ListNode<E> {
        E data;
        //this acts as the "pointer" to the next node
        ListNode<E> next;

        // Creates a terminal ListNode containing null as data.
        public ListNode() {
            this(null, null);
        }

        // Creates a terminal ListNode with the specified data.
        public ListNode(E data) {
            this(data, null);
        }

        // Creates a ListNode with the specified data and next node.
        public ListNode(E data, ListNode<E> next) {
            this.data = data;
            this.next = next;
        }

        // Purpose: returns the data from the given node
        public E getData() {
            return data;
        }

    }

    private int size;
    //the first element, i.e. the "front" of the queue
    private ListNode<E> first;

    public ListQueue() {

    }

    /*
     * Purpose: This is a copy constructor that copies
     * all information from the passed generic listQueue and
     * creates a new instance with that imported data.
     *
     * @param listQueue, is the listQueue object
     * to be copied.
     */
    public ListQueue(ListQueue<E> listQueue) {
        this.size = listQueue.size;
        this.first = listQueue.first;

    }

    /*
     * Purpose: A method that imitates the traditional Queue enqueue
     * method; it checks to see if the list is empty, and if it isn't
     * it inserts the value at the current position (back of the queue).
     * It then increments the size of the list.
     *
     * @param value, is the value being placed into the queue.
     */
    @Override
    public void enqueue(E value) {
        if (first == null) {
            first = new ListNode<>(value);
        } else {
            ListNode<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode<>(value);
        }
        size++;

    }
    /*
     * Purpose: A method that imitates the traditional Queue dequeue
     * method; it first stores the element being dequeued, and then it
     * simply makes the first ListNode the next one in the queue.
     *
     * @return dequeueValue, is the data that was dequeued off the
     * front of the queue.
     */
    @Override
    public E dequeue() {
        E deQueueValue = first.getData();
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
    public E peek() {
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
     * It also sets the size to 0.
     */
    @Override
    public void clear() {
        size = 0;
        first = new ListNode<>();
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
        ListNode<E> current = first;
        for (int i = 0; i < size; i++) {
            if (current.getData() != null) {
                s.append(current.getData()).append(", ");
                current = current.next;
            } else {
                break;
            }


        }
        return "{ " + s +
                "}";
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

