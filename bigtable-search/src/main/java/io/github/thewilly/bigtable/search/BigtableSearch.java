package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;

import java.util.stream.Stream;

/**
 * The type Bigtable search.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableSearch<T extends Comparable<T>> {

  /** The Table. */
  protected final Bigtable<T> _table;

  /**
   * Instantiates a new Bigtable search.
   *
   * @param table the table
   */
  public BigtableSearch(Bigtable<T> table) {
    _table = table;
  }

  /**
   * Read rows stream.
   *
   * @param indexIdentifier the index identifier
   * @return the stream
   */
  public abstract Stream<BigtableIndexRow<T>> readRows(String indexIdentifier);
}
