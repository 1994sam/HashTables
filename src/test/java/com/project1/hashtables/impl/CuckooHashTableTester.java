package com.project1.hashtables.impl;

import com.project1.flows.Flow;
import com.project1.hashtables.interfaces.AbstractHashTable;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

import static com.project1.hashtables.util.TestUtil.getFlows;
import static com.project1.hashtables.util.TestUtil.testHashTable;

/**
 * Performs a demo run for CuckooHashTable and prints the hash table to CuckooHashTable.txt.
 */
class CuckooHashTableTester {
    @Test
    void testHashingWithFlows() throws FileNotFoundException, NoSuchAlgorithmException {
        Flow[] flows = getFlows(1000);
        AbstractHashTable<Flow> table = new CuckooHashTable<>(1000, 3, 2);
        testHashTable(table, 3, flows, "CuckooHashTable.txt");
    }
}