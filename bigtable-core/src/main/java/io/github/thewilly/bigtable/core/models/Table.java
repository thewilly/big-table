package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

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
  Collection<TableRow<T>> getRows();

  /**
   * Gets columns.
   *
   * @return the columns
   */
  Collection<String> getColumns();
}
