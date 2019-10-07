package io.thewilly.bigtable;

import io.thewilly.bigtable.index.IndexEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SearchTest {

    private IndexEngine longTextTestIndexEngine = new IndexEngine() {

        @SuppressWarnings("unchecked")
        @Override
        public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {

            String[] keys = key.toString().split( " " );

            for(String ikey : keys) {
                if (ikey.length() >= 3) {

                    if (table.getMemoryMap().containsKey(ikey)) {
                        table.getMemoryMap().get(ikey).add(value);
                        System.err.println( "Added value [" +value+ "] to key [" + ikey +"]"  );
                    } else {
                        table.getMemoryMap().put((K) ikey, new HashSet<V>());
                        table.getMemoryMap().get(ikey).add(value);
                        System.err.println( "Added key [" +ikey+ "] and value [" + value +"]"  );
                    }
                }
            }

            return true;
        }
    };

    private BigTable<String, String> testTable =
            new BigTableProducer<String, String>()
                    .asParallel()
                    .withIndexEngine( longTextTestIndexEngine )
                    .build();


    @Before
    public void setUp() {
        testTable.clear();

        String[] code1 = {"1" ,"maria canta alto"};
        String[] code2 = {"2" ,"pepe canta bajo"};

        assertTrue( testTable.insert( code1[1], code1[0] ));
        assertTrue( testTable.insert( code2[1], code2[0] ));
    }

    @Test
    public void findUnionTest() {

        // Testing null set of words to search.
        assertEquals(0, testTable.findUnion(null).size());

        // Testing empty set of words to search.
        assertEquals(0, testTable.findUnion().size());

        // Testing non existing key.
        assertEquals(0, testTable.findUnion("non-existing-key").size());

        // Testing a key that appears on both entries.
        assertEquals(2, testTable.findUnion("canta").size());

        // Testing two existing keys, one on each entry.
        assertEquals(2, testTable.findUnion( "maria", "pepe").size());

        // Testing three existing keys, one from one entry and two from another.
        assertEquals(2, testTable.findUnion( "maria", "pepe", "bajo").size());

        // Testing a key that appears on both entries and one that only appears on one.
        assertEquals(2, testTable.findUnion( "canta", "alto", "bajo").size());
    }

    @Test
    public void findIntersectionTest() {

        // Testing null set of words to search.
        assertEquals(0, testTable.findIntersection(null).size());

        // Testing empty set of words to search.
        assertEquals(0, testTable.findIntersection().size());

        // Testing non existing key.
        assertEquals(0, testTable.findIntersection("non-existing-key").size());

        // Testing a key that appears on both entries.
        assertEquals(2, testTable.findIntersection("canta").size());

        // Testing and existing work on both entries and a word that only exists on one entry.
        assertEquals(1, testTable.findIntersection( "canta", "bajo" ).size());

        // Testing two existing keys, one on each entry.
        assertEquals(0, testTable.findIntersection( "maria", "pepe").size());

        // Testing three existing keys, one from one entry and two from another.
        assertEquals(0, testTable.findIntersection( "maria", "pepe", "bajo").size());

        // Testing a key that appears on both entries and one that only appears on one.
        assertEquals(0, testTable.findIntersection( "canta", "alto", "bajo").size());
    }

    @Test
    public void findTest() {

        // Testing null set of words to search.
        assertEquals(0, testTable.find(null).size());

        // Testing empty set of words to search.
        assertEquals(0, testTable.find("").size());

        // Testing non existing key.
        assertEquals(0, testTable.find("non-existing-key").size());

        // Testing a key that appears on both entries.
        assertEquals(1, testTable.find("maria").size());

        // Testing a key that appears on both entries.
        assertEquals(2, testTable.find("canta").size());
    }
}
