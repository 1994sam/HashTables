package com.project1.hashtables.interfaces;

import com.project1.hashtables.impl.TableEntry;
import com.project1.hashtables.util.PrintTableUtility;

import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;

/**
 * Provides a skeletal implementation of the {@code HashTable} interface.
 *
 * @param <T> The type of Entries to be maintained in the Map.
 */
public abstract class AbstractHashTable<T> implements HashTable<T> {

    private final int[] functionValues;
    private final MessageDigest messageDigest;

    protected AbstractHashTable(int numberOfHashFunctions) throws NoSuchAlgorithmException {
        functionValues = new int[numberOfHashFunctions];
        Random random = new Random();
        for (int i = 0; i < numberOfHashFunctions; i++) {
            functionValues[i] = random.nextInt(numberOfHashFunctions);
        }
        messageDigest = MessageDigest.getInstance("MD5");
    }

    @Override
    public int size() {
        return entries().length;
    }

    @Override
    public boolean contains(final T element) {
        int functionNumber = 1;
        TableEntry<T>[] entries = entries();
        while (functionNumber <= numberOfHashFunctions()) {
            int hash = hashFunction(element, functionNumber++);
            if (entries[hash] != null && element.equals(entries[hash].getObject())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the hash table entries in two  columns:
     * 1. Item -> Flow ID if the hash table entry is set.
     * 2. Index -> the index of the entry in the array.
     *
     * @param stream the stream to which the table is to be printed. For example the console output stream.
     */
    public void printTable(PrintStream stream) {
        TableEntry<T>[] entries = entries();
        String[][] table = new String[entries.length + 1][2];
        table[0][0] = "Item";
        table[0][1] = "Index";
        for (int i = 0; i < entries.length; i++) {
            TableEntry<T> entry = entries[i];
            if (entry != null && entry.getObject() != null) {
                table[i + 1][0] = entry.getObject().toString();
            } else {
                table[i + 1][0] = "0";
            }
            table[i + 1][1] = String.valueOf(i);
        }
        stream.println("Number of Flows = " + size());
        PrintTableUtility.printTableToStream(table, false, false, new HashSet<>(), stream);
    }

    /**
     * The function which calculates the hash value to add the element to the hash table. This function creates a
     * hash value which lies from 0 to capacity of the hash table.
     *
     * @param element        the element to be inserted in the hash table.
     * @param functionNumber the hash function which is to be used.
     * @return the hash value for the element.
     */
//        public int hashFunction(final T element, int functionNumber) {
//            return Math.abs(element.hashCode() ^ functionValues[functionNumber - 1]) % (getCapacity() - 1);
//        }
    public int hashFunction(final T element, int functionNumber) {
        messageDigest.reset();
        byte[] bytes = ByteBuffer.allocate(4).putInt(element.hashCode()).array();
        messageDigest.update(bytes, 0, bytes.length);
        return Math.abs(new BigInteger(1, messageDigest.digest()).intValue() * functionNumber) % (getCapacity() - 1);
    }

    /**
     * The function which calculates the hash value to add the element to the hash table. This function creates a
     * hash value which lies between 0 and the capacity of the hash table. This hash function is useful for calculation
     * hash value in case of d - left hash table.
     *
     * @param element        the element to be inserted in the hash table.
     * @param functionNumber the hash function which is to be used.
     * @param offset         the offset of the segment in which the element is to be inserted.
     * @return the hash value for the element.
     */
    public int hashFunction(final T element, int functionNumber, int offset) {
        int temp = Math.abs(element.hashCode() ^ functionValues[functionNumber - 1]) % (offset - 1);
        return temp + (functionNumber - 1) * (offset);
    }

    public abstract TableEntry<T>[] entries();

    public abstract int numberOfHashFunctions();

    protected abstract int getCapacity();
}
