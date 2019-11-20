package io.github.thewilly.bigtable.core.mutation;

import io.github.thewilly.bigtable.core.BigtableTable;
import io.github.thewilly.bigtable.core.models.Cell;
import io.github.thewilly.bigtable.core.models.Table;
import io.github.thewilly.bigtable.core.models.TableRow;

import java.util.Collection;

/** The type Table mutation. */
public class TableMutation {

  private final Table _tableToMutate;

  private TableMutation(BigtableTable tableToMutate) {
    _tableToMutate = tableToMutate;
  }

  /**
   * Create table mutation.
   *
   * @param table the table
   * @return the table mutation
   */
  public static TableMutation create(BigtableTable table) {
    return new TableMutation(table);
  }

  /**
   * Add row boolean.
   *
   * @param <T> the type parameter
   * @param row the row
   * @return the boolean
   */
  public <T extends Comparable<T>> boolean addRow(TableRow row) {
    _tableToMutate.getRows().add(row);
    Collection<String> columnsInTable = _tableToMutate.accessColumns();
    for (Cell cell : row.getCells()) {
      if (!columnsInTable.contains(cell.getColumnQualifier())) {
        _tableToMutate.accessColumns().add(cell.getColumnQualifier());
      }
    } return true;
  }

  /**
   * Remove row boolean.
   *
   * @param row the row
   * @return the boolean
   */
  public boolean removeRow(TableRow row) {
    return _tableToMutate.getRows().remove(row);
  }
}
