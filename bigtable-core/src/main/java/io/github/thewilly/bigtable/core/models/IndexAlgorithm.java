package io.github.thewilly.bigtable.core.models;

import java.util.List;

/** The interface Index algorithm. */
public interface IndexAlgorithm {

  /**
   * Index row list.
   *
   * @param <T> the type parameter
   * @param row the row
   * @return the list
   */
  <T extends Comparable<T>> List<TableRowLocalizer> indexRow(TableRow<T> row);
}
