package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * The interface Table.
 *
 * @param <T> the type parameter
 */
public interface Table<T extends Comparable<T>> extends Serializable {

  /**
   * Gets table id.
   *
   * @return the table id
   */
  String getTableId();

  /**
   * Gets rows.
   *
   * @return the rows
   */
  Stream<TableRow> getRows();

  /**
   * Gets columns.
   *
   * @return the columns
   */
  Stream<String> getColumns();
}
