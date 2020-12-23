package com.project1.hashtables.impl;


import com.project1.hashtables.interfaces.AbstractHashTable;

import java.security.NoSuchAlgorithmException;

/**
 * D - Left Hash Table implementation.
 * To instantiate the hash table, you need to specify the initial capacity and the d value.
 *
 * @param <T> The type of object we intend to store in the hash table.
 */
public class DLeftHashTable<T> extends AbstractHashTable<T> {
    private final int capacity;
    private int size;
    private final TableEntry<T>[] entries;
    private final int sizeOfSegment;
    private final int d;

    public DLeftHashTable(int capacity, int d) throws NoSuchAlgorithmException {
        super(d);
        this.capacity = capacity;
        this.entries = new TableEntry[capacity];
        this.d = d;
        this.sizeOfSegment = capacity / d;
    }

    @Override
    public int hashFunction(T element, int functionNumber) {
        return hashFunction(element, functionNumber, sizeOfSegment);
    }

    @Override
    public boolean put(T element) {
        if (contains(element)) {
            return false;
        }
        int functionNumber = 1;
        while (functionNumber <= d) {
            int hash = hashFunction(element, functionNumber++, sizeOfSegment);
            if (entries[hash] == null) {
                entries[hash] = new TableEntry<>(element);
                size++;
                return true;
            }
        }
        return false;
    }


    public int size() {
        return size;
    }

    @Override
    protected int getCapacity() {
        return capacity;
    }

    @Override
    public TableEntry<T>[] entries() {
        return entries;
    }

    @Override
    public int numberOfHashFunctions() {
        return d;
    }
}
