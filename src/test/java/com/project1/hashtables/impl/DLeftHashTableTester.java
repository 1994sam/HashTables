package com.project1.hashtables.impl;

import com.project1.flows.Flow;
import com.project1.hashtables.interfaces.AbstractHashTable;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

import static com.project1.hashtables.util.TestUtil.getFlows;
import static com.project1.hashtables.util.TestUtil.testHashTable;

/**
 * Performs a demo run for DLeftHashTable and prints the hash table to DLeftHashTable.txt.
 */
class DLeftHashTableTester {
    @Test
    void runHashingWithFlows() throws FileNotFoundException, NoSuchAlgorithmException {
        Flow[] flows = getFlows(1000);
        AbstractHashTable<Flow> table = new DLeftHashTable<>(1000, 4);
        testHashTable(table, 4, flows, "DLeftHashTable.txt");
    }
}