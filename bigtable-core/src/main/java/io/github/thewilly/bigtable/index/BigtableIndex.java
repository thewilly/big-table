package io.github.thewilly.bigtable.index;

import io.github.thewilly.bigtable.index.algorithm.BigtableIndexAlgorithm;
import io.github.thewilly.bigtable.index.mutation.BigtableIndexMutation;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;

/**
 * The type Bigtable index.
 *
 * @param <T> the type parameter
 */
public class BigtableIndex<T extends Comparable<T>> {

  private static final int DEFAULT_SIZE = 1000;

  private final BigtableIndexRow<T>[] _index;
  private final BigtableIndexAlgorithm<T> _indexAlgorithm;
  private final String _indexIdentifier;

  /**
   * Instantiates a new Bigtable index.
   *
   * @param indexIdentifier the index identifier
   * @param indexAlgorithm the index algorithm
   */
  public BigtableIndex(String indexIdentifier, BigtableIndexAlgorithm indexAlgorithm) {
    this(indexIdentifier, indexAlgorithm, DEFAULT_SIZE);
  }

  /**
   * Instantiates a new Bigtable index.
   *
   * @param indexIdentifier the index identifier
   * @param indexAlgorithm the index algorithm
   * @param size the size
   */
  public BigtableIndex(String indexIdentifier, BigtableIndexAlgorithm indexAlgorithm, int size) {
    _index = new BigtableIndexRow[size];
    _indexIdentifier = indexIdentifier;
    _indexAlgorithm = indexAlgorithm;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public int getSize() {
    return _index.length;
  }

  /**
   * Gets index identifier.
   *
   * @return the index identifier
   */
  public String getIndexIdentifier() {
    return _indexIdentifier;
  }

  /**
   * Mutate index.
   *
   * @param action the action
   */
  public void mutateIndex(BigtableIndexMutation action) {
    action.setIndexToMutate(this);
    action.executeAction();
  }

  /**
   * Gets index algorithm.
   *
   * @return the index algorithm
   */
  public BigtableIndexAlgorithm<T> getIndexAlgorithm() {
    return _indexAlgorithm;
  }

  /**
   * Gets row at index.
   *
   * @param index the index
   * @return the row at index
   */
  public BigtableIndexRow<T> getRowAtIndex(int index) {
    return _index[index];
  }

  /**
   * Get hits for element bigtable index row [ ].
   *
   * @param element the element
   * @return the bigtable index row [ ]
   */
  public BigtableIndexRow<T>[] getHitsForElement(T element) {
    int[] indexes = _indexAlgorithm.computeKeys(element);
    BigtableIndexRow<T>[] hits = new BigtableIndexRow[indexes.length];
    int i = 0;
    for (int index : indexes) {
      hits[i] = _index[index];
      i++;
    }

    return hits;
  }
}
