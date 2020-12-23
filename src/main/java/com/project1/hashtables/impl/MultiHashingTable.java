package com.project1.hashtables.impl;


import com.project1.hashtables.interfaces.AbstractHashTable;

import java.security.NoSuchAlgorithmException;

/**
 * Multi - hash table implementation.
 * To instantiate the hash table you need to pass the initial capacity and the number of hash functions for the hash table.
 *
 * @param <T> The type of object we intend to store in the hash table.
 */
public class MultiHashingTable<T> extends AbstractHashTable<T> {
    private final int numberOfHashFunctions;
    private int capacity;
    private int size;
    private final TableEntry<T>[] entries;

    public MultiHashingTable(int capacity, int numberOfHashFunctions) throws NoSuchAlgorithmException {
        super(numberOfHashFunctions);
        this.numberOfHashFunctions = numberOfHashFunctions;
        this.capacity = capacity;
        entries = new TableEntry[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean put(T element) {
        if (contains(element)) {
            return false;
        }
        int functionNumber = 1;
        while (functionNumber < numberOfHashFunctions) {
            int hash = hashFunction(element, functionNumber++);
            if (entries[hash] == null) {
                entries[hash] = new TableEntry<>(element);
                size++;
                return true;
            }
        }
        return false;
    }

    @Override
    public TableEntry<T>[] entries() {
        return entries;
    }

    @Override
    public int numberOfHashFunctions() {
        return numberOfHashFunctions;
    }

    @Override
    protected int getCapacity() {
        return capacity;
    }
}
