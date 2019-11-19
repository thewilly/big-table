package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.index.Index;
import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;

import java.util.stream.Stream;

/**
 * The type Bigtable search.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableSearch<T extends Comparable<T>> {

  /** The Table. */
  protected final Table _table;

  /**
   * Instantiates a new Bigtable search.
   *
   * @param table the table
   */
  public BigtableSearch(Table table) {
    _table = table;
  }

  /**
   * Finds the indexed rows under the given key.
   *
   * @param indexToExecuteTheSearch is the index where we will execute the search operation.
   * @return an stream containing all the hits.
   */
  public abstract Stream<Row> findIndexedRows(Index indexToExecuteTheSearch);
}
