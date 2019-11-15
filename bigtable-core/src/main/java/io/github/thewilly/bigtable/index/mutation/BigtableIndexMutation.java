package io.github.thewilly.bigtable.index.mutation;

import io.github.thewilly.bigtable.index.BigtableIndex;

/**
 * The type Bigtable index mutation.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableIndexMutation<T extends Comparable<T>> {

  /** The Index to mutate. */
  protected BigtableIndex<T> _indexToMutate;

  /**
   * Add element bigtable index mutation.
   *
   * @param <T> the type parameter
   * @param element the element
   * @return the bigtable index mutation
   */
  public static <T extends Comparable<T>> BigtableIndexMutation<T> addElement(T element) {
    return new AddElementIndexMutation(element);
  }

  /**
   * Remove element bigtable index mutation.
   *
   * @param <T> the type parameter
   * @param element the element
   * @return the bigtable index mutation
   */
  public static <T extends Comparable<T>> BigtableIndexMutation<T> removeElement(T element) {
    return new RemoveElementIndexMutation(element);
  }

  /**
   * Sets index to mutate.
   *
   * @param indexToMutate the index to mutate
   */
  public void setIndexToMutate(BigtableIndex<T> indexToMutate) {
    _indexToMutate = indexToMutate;
  }

  /**
   * Execute action boolean.
   *
   * @return the boolean
   */
  public abstract boolean executeAction();
}
