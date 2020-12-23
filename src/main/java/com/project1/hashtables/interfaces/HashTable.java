package com.project1.hashtables.interfaces;

/**
 * Interface for the hash tables.
 *
 * @param <T> the type of entries to be inserted in the hash table.
 */
public interface HashTable<T> {

    /**
     * @return the size of the hash table.
     */
    int size();

    /**
     * Checks if the element is present in the hash table.
     *
     * @param element the element to be checked.
     * @return true if the element is present in the hash table.
     */
    boolean contains(T element);

    /**
     * Add the element in the hash table.
     *
     * @param element the element to be added.
     * @return true if the element has been added to the table.
     */
    boolean put(T element);

}
