package WordDictionary;

import java.util.*;

    /*
    Design a custom Word Dictionary that provides definitions about the words. Users should be able to lookup
    specific words and find their corresponding definitions, add/remove words with definitions and search words
    partially.
    The dictionary should have a unique definition for the given word and these words are case insensitive. Please
    implement the below WordDictionary class without using built-in java HashMap.
     */

class WordDictionary {
    // the size of our dataTable, it should be a prime number
    // and here for facilitating the process I used 23
    private int size = 23;
    // dataTable is array of Nodes for our implemented hash table
    private Node[] dataTable;
    public WordDictionary() {
        // initializes the WordDictionary
        dataTable = new Node[size];
    }

    /**
     * Inserts the word and corresponding definition into the dictionary, if the word already exists, it
     * will override the word and definition and should insert case-insensitive
     * @param word is the one we look for the definition
     * @param definition and the one we find in the dictionary
     */
    public void insertWord(String word, String definition) {
        // first we change the word to lowercase because the requirement needs us to
        // check the word case-insensitive
        String lowerCaseWord = word.toLowerCase();
        // calculate the index by using our hash function
        int index = hashFunction(lowerCaseWord);
        // check whether there is any data in the specific index, if it's not it
        // will create a node and enter it there
        if (dataTable[index] == null) {
            dataTable[index] = new Node(lowerCaseWord, definition);
        } else {
            Node current = dataTable[index];
            while (current.next != null) {
                // if there is already a node with the key which is equals to
                // word then rewrite the definition
                if (current.key.equals(lowerCaseWord)) {
                    dataTable[index].value = definition;
                    return;
                }
                // move the current pointer to the next node
                current = current.next;
            }
            // in another case if it is not the word with same key, it iterates through the nodes until
            // it is next to the null one and enter new node there
            current.next = new Node(lowerCaseWord, definition);
        }
    }

    /**
     * Returns the definition for the word case-insensitive
     * @param word the key that we are looking for
     * @return the definition that is in our dictionary, if nothing found it returns empty string
     */
    public String findDefinition(String word) {
        // calculate the index by using our hash function
        int index = hashFunction(word.toLowerCase());
        Node current = dataTable[index];
        while (current != null) {
            if (current.key.equalsIgnoreCase(word)) {
                break;
            }
            // move the current pointer to the next node
            current = current.next;
        }
        return current == null ? "" : current.value;
    }

    /**
     * Returns the definitions for the words that are matched partially
     * @param partialWord partial word that we are looking for
     * @return list of definitions we found related to partial word
     */
    public List<String> partialSearch(String partialWord) {
        // a list of definitions we want to find, if
        // it won't find any, it will return an empty list
        List<String> definitions = new ArrayList<>();
        // start iterating through all the nodes
        for (Node node : dataTable) {
            // if there is a node it checks the node and nodes that are connected to it
            if (node != null) {
                Node current = node;
                while (current != null) {
                    // it uses contains method of string class to see
                    // if this string contains partial word we are looking for
                    if (current.key.contains(partialWord.toLowerCase())) definitions.add(current.value);
                    current = current.next;
                }
            }
        }
        return definitions;
    }

    /**
     * Removes the word with definition from the dictionary
     * @param word search for the word, if it finds it, it removes from
     * the hash table, if not it won't do anything
     */
    public void remove(String word) {
        // calculate the index by using our hash function
        int index = hashFunction(word.toLowerCase());
        // check whether it contains a head node in the array
        if (dataTable[index] != null) {
            // create a current pointer
            Node current = dataTable[index];
            // create a previous pointer to have the previous node if we find anything to remove
            Node prev = dataTable[index];
            while (current != null) {
                // if it finds the word we are looking for
                if (current.key.equalsIgnoreCase(word)) {
                    // check whether the reference of the prev and current are equal,
                    // it means th pointers didn't move and the word is the head node
                    if (prev == current) {
                        // set the next one in the node list in the array container as our head of the list
                        dataTable[index] = current.next;
                        // set the pointer of the node to the next we want to remove to null for garbage collector to
                        // remove it later because it is not pointing to anything and nothing is pointing to it
                        current.next = null;
                        // same as current since both where pointing at a same node that we should remove
                        prev.next = null;
                    } else {
                        // since it is not the head here, prev and current are pointing to different nodes, so
                        // we set prev pointer to the current next
                        prev.next = current.next;
                        current.next = null;
                    }
                    break;
                }
                // here we move prev forward to current
                prev = current;
                // here we move current to next
                current = current.next;
            }
        }
    }

    /**
     * hashFunction is for calculating a unique hash value for each word, and returning the index of array that
     * we want to store our word and definition pair
     * @param key is the word definition and the key for our hash table
     * @return integer representing index value of our array container which this word should be stored
     */
    private int hashFunction(String key) {
        // create an array of characters for the key and augment the sum value with char value
        char[] chars = key.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += chars[i];
        }
        // and then return the augmented sum remainder to the size of our array which is a prime number
        return sum % size;
    }

    /**
     * Node class for implementing a hash table data structure, and in this
     * hashTable we solve collision by using linked Node in each container of array
     *
     * key attribute is for storing a word that user is looking for
     *
     * value attribute is for storing the definition that user is looking for
     *
     * next attribute for having the pointer to the next element if we have collision in our hashTable
     */
    private class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
