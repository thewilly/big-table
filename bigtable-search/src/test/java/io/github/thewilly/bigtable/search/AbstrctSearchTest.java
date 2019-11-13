package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.BigtableIndex;
import io.github.thewilly.bigtable.index.algorithm.BigtableIndexAlgorithm;

public class AbstrctSearchTest {


    protected final Bigtable<String> table =
            (Bigtable<String>) Bigtable
                    .of("test-table")
                    .withMaxSize(10)
                    .withIndex(new BigtableIndex<String>(
                            "string-first-index",
                            new BigtableIndexAlgorithm<String>(10) {
                                @Override
                                public int[] computeKeys(String element) {
                                    return new int[0];
                                }
                            }))
                    .create();

    public AbstrctSearchTest() {
        table.
    }
}
