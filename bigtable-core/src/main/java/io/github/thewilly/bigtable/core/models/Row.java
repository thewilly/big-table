package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

public interface Row<T extends Comparable<T>> extends Serializable, Iterable<Cell<T>> {

  /**
   * An iterable over the cells of this row.
   *
   * <p>The iterable guarantees that cells are returned in order of {@link Cell#DEFAULT_COMPARATOR}.
   *
   * @return an iterable over the cells of this row.
   */
  Iterable<Cell<T>> cells();

  /**
   * The number of columns for which data (incl. simple tombstones) is present in this row.
   *
   * @return The number of columns for which data (incl. simple tombstones) is present in this row.
   */
  int columnCount();

  /**
   * Returns a cell for a simple column.
   *
   * @param columnQualifier
   * @return
   */
  Cell<T> getCell(String columnQualifier);
}
