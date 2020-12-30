# Hash Tables

This repository contains the implementations of following hash tables:

 * **Multi-hashing table**
 
 In this type of hash table, we intend to increases the efficiency of the hash table by using multiple hash functions to insert a flow into the hash table. 
 
 * **Cuckoo hash table**
 
 This hash table uses an open-addressing solution i.e. cuckoo hashing, which ensures constant lookup and deletion time in the worst case, and constant amortized time for insertions (with low probability that the worst-case will be encountered). It uses two or more hash functions, which means any key/value pair could be in two or more locations. For lookup, the first hash function is used; if the key/value is not found, then the second hash function is used, and so on. If a collision happens during insertion, then the key is re-hashed with the second hash function to map it to another bucket. If all hash functions are used and there is still a collision, then the key it collided with is removed to make space for the new key, and the old key is re-hashed with one of the other hash functions, which maps it to another bucket. If that location also results in a collision, then the process repeats until there is no collision or the process traverses all the buckets, at which point the table is resized. By combining multiple hash functions with multiple cells per bucket, very high space utilization can be achieved.
 
 * **d-left hash table**
 
 This hash table uses the d - left hashing to store data. For details about the algorithm please use this article below:
 
 ["Bloom Filters via d-Left Hashing and Dynamic Bit Reassignment Extended Abstract" by Flavio Bonomi, Michael Mitzenmacher, Rina Panigrahy, Sushil Singh and George Varghese](http://www.eecs.harvard.edu/~michaelm/postscripts/aller2006.pdf)
  
