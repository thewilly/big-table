package io.github.thewilly.bigtable.index.algorithm;

import io.github.thewilly.bigtable.index.BigtableIndex;

/**
 * The type Bigtable index algorithm.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableIndexAlgorithm<T extends Comparable<T>> {

    private final BigtableIndex _index;

    /**
     * Instantiates a new Bigtable index algorithm.
     *
     * @param index the index
     */
    public BigtableIndexAlgorithm(BigtableIndex index) {
        _index = index;
    }

    /**
     * Compute keys int [ ].
     *
     * @param element the element
     * @return the int [ ]
     */
    public abstract int[] computeKeys(T element);
}
