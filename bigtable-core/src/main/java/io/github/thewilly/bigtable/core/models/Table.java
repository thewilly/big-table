package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * The interface Table.
 *
 */
public interface Table extends Serializable {

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
  Stream<Row> stream();

  /**
   *
   * @return
   */
  Collection<Row> getRows();

  /**
   * Gets columns.
   *
   * @return the columns
   */
  Stream<String> getColumns();

  /**
   *
   * @return
   */
  int getNumberOfColumns();

  /**
   *
   * @return
   */
  int getNumberOfRows();
}
