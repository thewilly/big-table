package io.github.thewilly.bigtable.index.algorithm;

/**
 * The type Bigtable index algorithm.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableIndexAlgorithm<T extends Comparable<T>> {

  private final int _indexSize;

  /**
   * Instantiates a new Bigtable index algorithm.
   *
   * @param indexSize the index
   */
  public BigtableIndexAlgorithm(int indexSize) {
    _indexSize = indexSize;
  }

  /**
   * Compute keys int [ ].
   *
   * @param element the element
   * @return the int [ ]
   */
  public abstract int[] computeKeys(T element);
}
