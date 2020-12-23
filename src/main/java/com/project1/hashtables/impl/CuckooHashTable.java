package com.project1.hashtables.impl;

import com.project1.hashtables.interfaces.AbstractHashTable;

import java.security.NoSuchAlgorithmException;

/**
 * Cuckoo Hash table implementation.
 * To instantiate the table you need to pass the initial capacity of the table, the number of hash functions to be
 * used and the number of cuckoo steps to do to insert a new object.
 *
 * @param <T> The type of object we intend to store in the hash table.
 */
public class CuckooHashTable<T> extends AbstractHashTable<T> {

    private final int numberOfHashFunctions;
    private final int numberOfCuckooSteps;
    private int capacity;
    private int size;
    private final TableEntry<T>[] entries;

    public CuckooHashTable(int capacity, int numberOfHashFunctions, int numberOfCuckooSteps) throws NoSuchAlgorithmException {
        super(numberOfHashFunctions);
        this.numberOfHashFunctions = numberOfHashFunctions;
        this.numberOfCuckooSteps = numberOfCuckooSteps;
        this.capacity = capacity;
        size = 0;
        entries = new TableEntry[capacity];
    }

    public int size() {
        return size;
    }


    @Override
    public boolean put(T element) {

        for (int i = 1; i <= numberOfHashFunctions; i++) {
            int hash = hashFunction(element, i);
            if (entries[hash] == null) {
                entries[hash] = new TableEntry<>(element);
                size++;
                return true;
            }
        }

        for (int i = 1; i <= numberOfHashFunctions; i++) {
            int hash = hashFunction(element, i);
            if (move(hash, numberOfCuckooSteps)) {
                entries[hash] = new TableEntry<>(element);
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the object from current location to a new empty location.
     *
     * @param hash                hash value of the element we want to insert.
     * @param numberOfCuckooSteps the number of cuckoo steps to take before stopping.
     * @return true if the element at the current position is able to move to a new position.
     */
    private boolean move(int hash, int numberOfCuckooSteps) {
        if (numberOfCuckooSteps == 0) {
            return false;
        }
        T current = entries[hash].getObject();
        for (int i = 1; i <= numberOfHashFunctions; i++) {
            int newHash = hashFunction(current, i);
            if (newHash != hash && entries[newHash] == null) {
                entries[newHash] = new TableEntry<>(current);
                return true;
            }
        }

        for (int i = 1; i <= numberOfHashFunctions; i++) {
            int newHash = hashFunction(current, i);
            if (newHash != hash && move(newHash, numberOfCuckooSteps - 1)) {
                entries[newHash] = new TableEntry<>(current);
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
