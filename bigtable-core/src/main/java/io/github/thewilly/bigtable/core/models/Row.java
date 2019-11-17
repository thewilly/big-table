package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.stream.Stream;

/** The interface Row. */
public interface Row extends Serializable, Iterable<Cell> {

  /**
   * An iterable over the cells of this row.
   *
   * <p>The iterable guarantees that cells are returned in order of {@link Cell#DEFAULT_COMPARATOR}.
   *
   * @return an iterable over the cells of this row.
   */
  Iterable<Cell> iterable();

  Stream<Cell> stream();

  /**
   * The number of columns for which data (incl. simple tombstones) is present in this row.
   *
   * @return The number of columns for which data (incl. simple tombstones) is present in this row.
   */
  int columnCount();

  /**
   * Returns a cell for a simple column.
   *
   * @param columnQualifier the column qualifier
   * @return cell cell
   */
  Cell getCell(String columnQualifier);
}
