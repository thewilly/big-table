package io.github.thewilly.bigtable.core.mutation;

import io.github.thewilly.bigtable.core.models.Cell;
import io.github.thewilly.bigtable.core.models.RowCell;
import io.github.thewilly.bigtable.core.models.TableRow;
import io.github.thewilly.bigtable.core.models.DataVersion;

import java.util.Objects;

/** The type Row mutation. */
public class RowMutation {

  private final TableRow _rowToMutate;

  private RowMutation(TableRow rowToMutate) {
    _rowToMutate = rowToMutate;
  }

  /**
   * Creates a row mutation object.
   *
   * @param table the table
   * @return the row mutation object ready to perform any operation.
   */
  public static RowMutation create(TableRow table) {
    return new RowMutation(table);
  }

  /**
   * Updates a cell by adding a data version to it. If the cell had content on it previously will
   * return the last version.
   *
   * @param columnQualifier is the column name of the cell in the current row to update.
   * @param data is the data to add to the cell versions.
   * @return the previous data stored in the cell. If no previous data null.
   */
  public DataVersion updateCell(String columnQualifier, DataVersion data) {
    Cell cellToUpdate = _rowToMutate.getCellForColumnQualifier(columnQualifier);
    if (Objects.isNull(cellToUpdate)) {
      cellToUpdate = new RowCell(columnQualifier);
      cellToUpdate.addDataVersion(data);
      _rowToMutate.getCells().add(cellToUpdate);
      return null; // Previous value was null
    }
      return _rowToMutate.getCellForColumnQualifier(columnQualifier).addDataVersion(data);
  }
}
