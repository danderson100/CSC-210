/*
 * AUTHOR: David Anderson
 * FILE: MyHashMap.java
 * ASSIGNMENT: Programming Assignment 8 - Generic HashMap
 * COURSE: CSc 210; Summer 2020
 * PURPOSE: This class functions as a generic HashMap using an linked list of
 * HashNodes as a backing data structure. It utilizes a custom class, HashNode.java,
 * to store generic data and use pointers. This program implements the following methods,
 * most of which (aside from printTable() and hash()) are used in Java's traditional
 * HashMap abstract data type:
 *
 * put(K key, V value), get(K key), containsKey(K key), containsValue(V value), keySet(), isEmpty(), clear(),
 * size(), remove(K key) printTable(), and hash(K key).
 *
 * It also contains a few private helper methods to more efficiently complete tasks and/or reuse code.
 *
 * The program uses a another class, hashNode<K, V> that takes a key/value pair as
 * type parameters and stores them in a linked list with the
 * initial data and a HashNode<K, V> variable acting as a "pointer", which is similar
 * to Java's LinkedList class.
 *
 * USAGE:
 * This program takes no command-line arguments.
 *
 * EXAMPLE OUTPUT:
 * printTable():
 * ------------------------------------
 * Index 0: (0 conflicts), []
 * Index 1: (0 conflicts), []
 * Index 2: (0 conflicts), []
 * Index 3: (0 conflicts), []
 * Index 4: (0 conflicts), []
 * Index 5: (0 conflicts), [ExampleKeyX, ]
 * Index 6: (0 conflicts), [ExampleKeyY, ]
 * Index 7: (0 conflicts), []
 * Total # of conflicts: 0
 * -------------------------------------
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MyHashMap<K, V> {

    private int size;
    //this array will store the number of items in each bucket
    private final int[] bucketSize = new int[8];
    /*
     * The ArrayList will store a HashNode at each index (bucket),
     * and the HashNodes will be linked with pointers
     */
    private final List<HashNode<K, V>> buckets = new ArrayList<>();

    //This constructor initializes the ArrayList of HashNodes and fills it with null HashNode objects
    public MyHashMap() {
        for (int i = 0; i < 8; i++) {
            buckets.add(new HashNode<>(null, null));
        }
        size = 0;
    }

    /*
     * Purpose: This boolean method searches a specific bucket
     * to determine if the HashMap contains the indicated key.
     *
     * @param key, is the key being searched for.
     *
     * @return flag, is the boolean that returns true if the
     * key is found, and false otherwise.
     */
    public boolean containsKey(K key) {

        boolean flag = false;
        int index = hash(key);
        
        if (bucketSize[index] == 0) {
            return false;
        }
        HashNode<K, V> firstNodeInBucket = buckets.get(index);
        while (firstNodeInBucket.getKey() != null) {
            if (firstNodeInBucket.getKey().equals(key)) {
                flag = true;
                break;
            } else {
                if (firstNodeInBucket.getNext() != null) {
                    firstNodeInBucket = firstNodeInBucket.getNext();
                } else {
                    break;
                }
            }
        }
        return flag;
    }

    /*
     * Purpose: This boolean method searches the entire HashMap
     * to determine if the HashMap contains the indicated value.
     *
     * @param value, is the value being searched for.
     *
     * @return true/false, returns true if the
     * value is found, and false otherwise.
     */
    public boolean containsValue(V value) {
        int numNodes = 0;
        for (HashNode<K, V> node : buckets) {
            /*
            * this is a nested loop that checks each itemn in a bucket,
            * then moves to the next bucket.
             */
            HashNode<K, V> current = node;
            for (int i = 0; i < bucketSize[numNodes]; i++) {
                if (current.getValue() != null) {
                    if (current.getValue().equals(value)) {
                        return true;
                    } else {
                        current = current.getNext();
                    }
                }

            }
            numNodes++;
        }
        return false;

    }

    /*
     * Purpose: A method that first identifies which bucket to
     * search by calling the hash function, and then retrieves
     * the value associated with the indicated key.
     *
     * @param key, is the key being searched for in the HashMap.
     *
     * @return value, is the value associated with that key (if found).
     */
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> firstNode = buckets.get(index);
        V value = null;
        if (firstNode.getKey() != null) {
            if (firstNode.getKey().equals(key)) {
                value = firstNode.getValue();
            } else {
                while (firstNode.getNext() != null) {
                    firstNode = firstNode.getNext();
                    if (firstNode.getKey().equals(key)) {
                        value = firstNode.getValue();
                    }
                }
            }
        }

        return value;
    }

    /*
     * Purpose: A method that adds a new key/value pair into
     * the generic HashMap. It first checks to see if the given
     * key already exists in the keySet(); if so, it just updates
     * the value, and if not it creates a new element for the HashMap.
     *
     * @param key, is the key being added/updated in the HashMap.
     *
     * @param value, is the value being added/updated.
     *
     * @return value, is the value associated with that key.
     */
    public V put(K key, V value) {
        //this checks to see if the key already exists in the keySet
        if (!checkKeySet(key)) {
            int index = hash(key);
            bucketSize[index] += 1;
            HashNode<K, V> firstNodeInBucket = buckets.get(index);

            if (firstNodeInBucket.getKey() == null) {
                firstNodeInBucket.setKey(key);
                firstNodeInBucket.setValue(value);
            } else {
                HashNode<K, V> temp = new HashNode<>(key, value);
                temp.setNext(firstNodeInBucket);
                buckets.remove(index);
                buckets.add(index, temp);

            }
            size++;
            //if the key already exists, we call the updateValue method instead
        } else {
            updateValue(key, value);
        }

        return value;
    }

    /*
     * Purpose: A private helper method that gets called from
     * the put() method. Its function is to update the value associated
     * with a given key if that key already existed in the HashMap.
     *
     * @param key, is the key that is having its value updated.
     *
     * @param value, is the new value
     */
    private void updateValue(K key, V value) {
        int index = hash(key);
        HashNode<K, V> firstNodeInBucket = buckets.get(index);
        if (firstNodeInBucket.getKey().equals(key)) {
            firstNodeInBucket.setValue(value);
        } else {
            HashNode<K, V> current = firstNodeInBucket;
            for (int i = 0; i < bucketSize[index]; i++) {
                current = current.getNext();
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    break;
                }

            }
        }
    }

    /*
     * Purpose: A private helper method that gets called from
     * the put() method. Its function is to check the existing
     * Set of keys to see if that key already exists in the HashMap.
     *
     * @param key, is the key that is being checked.
     *
     * @return true/false, true if keySet contains the key, false otherwise.
     */
    private boolean checkKeySet(K key) {
        Set<K> keySet = keySet();
        return keySet.contains(key);

    }
    /*
     * Purpose: A method that creates a Set<K> of keys that have
     * been inserted into the generic HashMap. It iterates over each item
     * in each bucket and adds the keys to the Set. I used HashSet for
     * efficiency since the order doesn't matter.
     *
     * @return keys, is the Set<K> of found keys.
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        int count;

        for (int i = 0; i < buckets.size(); i++) {
            HashNode<K, V> firstNode = buckets.get(i);
            count = 0;
            while (count < bucketSize[i]) {
                if (firstNode.getKey() != null) {
                    keys.add(firstNode.getKey());
                    firstNode = firstNode.getNext();
                    count++;
                } else {
                    break;
                }

            }
        }
        //fill keys with the key value from each node
        return keys;
    }
    /*
     * Purpose: A method that first uses the hash function to determine
     * which bucket to search, and then removes the key/value pair
     * associated with the given key. It also reduces the total size
     * and the bucketSize array.
     *
     * @param key, is the key being searched for and removed.
     *
     * @return value, is the value associated with that key.
     */
    public V remove(K key) {
        V value = null;
        int index = hash(key);
        HashNode<K, V> previous = buckets.get(index);
        HashNode<K, V> current = previous.getNext();
        if (previous.getKey().equals(key)) {
            value = previous.getValue();
            buckets.remove(index);
            buckets.add(index, Objects.requireNonNullElseGet(current, () -> new HashNode<>(null, null)));
        } else {
            while (current != null) {
                K oldKey = current.getKey();
                if (oldKey.equals(key)) {
                    value = current.getValue();
                    HashNode<K, V> afterRemoved = current.getNext();
                    // i.e. removing last element
                    previous.setNext(afterRemoved);
                    break;
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
        }
        size--;
        bucketSize[index] -= 1;
        return value;
    }

    /*
     * Purpose: A method that can be called to print the current
     * contents of the ArrayList buckets. It uses a nested loop
     * to iterate over each HashNode in each bucket, and then
     * prints in the format indicated at the top of this file.
     */
    public void printTable() {
        StringBuilder s = new StringBuilder();
        int numBuckets = 0;
        for (HashNode<K, V> node : buckets) {

            for (int i = 0; i < bucketSize[numBuckets]; i++) {
                if (node.getKey() != null) {
                    s.append(node.getKey()).append(", ");
                    node = node.getNext();
                } else {
                    break;
                }
            }
            if (bucketSize[numBuckets] > 0) {
                System.out.println("Index " + numBuckets + ": (" + (bucketSize[numBuckets] - 1) + " conflicts), [" + s + "]");
            } else {
                System.out.println("Index " + numBuckets + ": (" + bucketSize[numBuckets] + " conflicts), [" + s + "]");
            }
            //move to the size of the next bucket, and reset the StringBuilder
            numBuckets++;
            s = new StringBuilder();

        }
        int totalNumConflicts = 0;
        for (int i : bucketSize) {
            if (i > 0) {
                totalNumConflicts += i - 1;
            }
        }
        System.out.println("Total # of conflicts: " + totalNumConflicts);
    }

    /*
     * Purpose: A method that returns the size of the
     * HashMap (aka the number of created nodes).
     *
     * @return size, which is the current number
     * of items in the HashMap.
     */
    public int size() {
        return size;
    }


    /*
     * Purpose: This method uses Java's hashCode() function and
     * a predetermined number of buckets (8) to randomly
     *  determine which index to store the item at (0-7).
     *
     * @return Math.abs(index), is the absolute value of
     * some integer between 0 and 7
     */
    private int hash(K key) {
        int hashCode = key.hashCode();
        int numBuckets = 8;
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    /*
     * Purpose: A method that checks to see if the current
     * size of the HashMap is 0, and thus empty.
     *
     * @return size == 0, which is the boolean referring
     * to whether the list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Purpose: A method that clears all of the previously stored
     * elements, creates null HashNode objects in each of the 8 buckets,
     * and then reverts the size to 0.
     */
    public void clear() {
        buckets.clear();
        for (int i = 0; i < 8; i++) {
            buckets.add(new HashNode<>(null, null));
        }
        size = 0;
    }
}
