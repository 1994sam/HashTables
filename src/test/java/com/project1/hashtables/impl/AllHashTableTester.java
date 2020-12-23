package com.project1.hashtables.impl;

import com.project1.flows.Flow;
import com.project1.hashtables.interfaces.AbstractHashTable;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

import static com.project1.hashtables.util.TestUtil.getFlows;
import static com.project1.hashtables.util.TestUtil.testHashTable;

/**
 * Does a demo run for all the hash tables and prints the tables to their respective files.
 */
public class AllHashTableTester {
    @Test
    void testHashingWithFlows() throws FileNotFoundException, NoSuchAlgorithmException {
        Flow[] flows = getFlows(1000);
        AbstractHashTable<Flow> table = new MultiHashingTable<>(1000, 3);
        testHashTable(table, 3, flows, "MultiHashingTable.txt");
        table = new CuckooHashTable<>(1000, 3, 2);
        testHashTable(table, 3, flows, "CuckooHashTable.txt");
        table = new DLeftHashTable<>(1000, 4);
        testHashTable(table, 4, flows, "DLeftHashTable.txt");
    }
}
