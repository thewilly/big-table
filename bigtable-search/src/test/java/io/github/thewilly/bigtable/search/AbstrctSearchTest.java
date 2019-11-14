package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.BigtableIndex;
import io.github.thewilly.bigtable.index.algorithm.BigtableIndexAlgorithm;
import io.github.thewilly.bigtable.index.mutation.AddElementIndexMutation;
import org.junit.Before;
import org.junit.Test;

public class AbstrctSearchTest {


    protected final Bigtable<String> table =
            (Bigtable<String>) Bigtable
                    .of("test-table")
                    .withMaxSize(10)
                    .withIndex(new BigtableIndex(
                            "string-first-index",
                            new BigtableIndexAlgorithm<String>(10) {
                                @Override
                                public int[] computeKeys(String element) {
                                    int[] keys = new int[1];
                                    keys[0] = element.hashCode() % 10;
                                    return new int[0];
                                }
                            }))
                    .create();

    @Before
    public void setUp() {
        table.getIndex("string-first-index").mutateIndex(new AddElementIndexMutation("hola"));
        table.getIndex("string-first-index").mutateIndex(new AddElementIndexMutation("que"));
        table.getIndex("string-first-index").mutateIndex(new AddElementIndexMutation("tal"));
    }

    @Test
    public void testUniqueSearch() {
        System.out.println(table.getIndex("string-first-index").getHitsForElement("hola").length);
    }
}
