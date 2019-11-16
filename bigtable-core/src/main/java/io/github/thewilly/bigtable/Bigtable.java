package io.github.thewilly.bigtable;

import io.github.thewilly.bigtable.index.BigtableIndex;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The interface Bigtable.
 *
 * @param <T> the type parameter
 */
public class Bigtable<T extends Comparable<T>> implements Serializable {

  /** The Default number of indexes. */
  static final int DEFAULT_NUMBER_OF_INDEXES = 3;

  private final Map<String, BigtableIndex<T>> _indexes;
  private final int _numberOfIndexes;
  private final String _tableId;

  /**
   * Instantiates a new Bigtable.
   *
   * @param tableId the table id
   */
  protected Bigtable(String tableId) {
    this(tableId, DEFAULT_NUMBER_OF_INDEXES);
  }

  /**
   * Instantiates a new Bigtable.
   *
   * @param tableId the table id
   * @param numberOfIndexes the number of indexes
   */
  protected Bigtable(String tableId, int numberOfIndexes) {
    _indexes = new HashMap<>(numberOfIndexes);
    _numberOfIndexes = numberOfIndexes;
    _tableId = tableId;
  }

  /**
   * Of create bigtable.
   *
   * @param <T> the type parameter
   * @param tableId the table id
   * @return the create bigtable
   */
  public static <T extends Comparable<T>> CreateBigtable<T> of(String tableId) {
    return new CreateBigtable<T>().withName(tableId);
  }

  /**
   * Gets index.
   *
   * @param indexIdentifier the index identifier
   * @return the index
   */
  public BigtableIndex<T> getIndex(String indexIdentifier) {
    return _indexes.get(indexIdentifier);
  }

  /**
   * Add index boolean.
   *
   * @param index the index
   * @return the boolean
   */
  protected boolean addIndex(BigtableIndex<T> index) {
    if (_indexes.size() == _numberOfIndexes) return false;
    _indexes.put(index.getIndexIdentifier(), index);
    return true;
  }
}
