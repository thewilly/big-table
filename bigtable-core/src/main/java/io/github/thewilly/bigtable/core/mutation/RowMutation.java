package io.github.thewilly.bigtable.core.mutation;

import io.github.thewilly.bigtable.core.models.TableRow;

/** The type Row mutation. */
public class RowMutation {

  private final TableRow _rowToMutate;

  private RowMutation(TableRow rowToMutate) {
    _rowToMutate = rowToMutate;
  }

  /**
   * Create row mutation.
   *
   * @param <T> the type parameter
   * @param table the table
   * @return the row mutation
   */
  public static <T extends Comparable<T>> RowMutation create(TableRow<T> table) {
    return new RowMutation(table);
  }

  /**
   * Update cell boolean.
   *
   * @param <T> the type parameter
   * @param columnQualifier the column qualifier
   * @param value the value
   * @return the boolean
   */
  public <T extends Comparable<T>> boolean updateCell(String columnQualifier, T value) {
    return _rowToMutate.getCellForColumnQualifier(columnQualifier).set(value);
  }
}
