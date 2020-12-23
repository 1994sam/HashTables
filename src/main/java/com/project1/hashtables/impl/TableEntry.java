package com.project1.hashtables.impl;

/**
 * Bean class for a table entry in a Hash table.
 *
 * @param <T> The type object to be stored in this entry object.
 */
public class TableEntry<T> {

    private T object;

    public TableEntry(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
