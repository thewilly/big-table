package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The type Table row.
 *
 * @param <T> the type parameter
 */
public class TableRow<T extends Comparable<T>> implements Row<T> {

  private final Logger log = LoggerFactory.getLogger(TableRow.class);

  private final Cell<T>[] _rowCells;

  private TableRow(int size) {
    _rowCells = new RowCell[size];
  }

  /**
   * Gets cell for column qualifier.
   *
   * @param qualifier the qualifier
   * @return the cell for column qualifier
   */
  public Cell<T> getCellForColumnQualifier(String qualifier) {
    for (Cell cell : _rowCells) {
      if (cell.getColumnQualifier().equals(qualifier)) return cell;
    }
    return null;
  }

  /**
   * Get all cells table cell [ ].
   *
   * @return the table cell [ ]
   */
  public Cell<T>[] getCells() {
    return _rowCells;
  }

  @Override
  public Iterator<Cell<T>> iterator() {
    return Arrays.asList(_rowCells).iterator();
  }

  @Override
  public Iterable<Cell<T>> cells() {
    List<Cell<T>> cells = Arrays.asList(_rowCells);
    cells.sort(Cell.DEFAULT_COMPARATOR);

    return cells;
  }

  @Override
  public int columnCount() {
    int nonNullCells = _rowCells.length;

    for(Cell cell : _rowCells) {
      if(cell == null)
        nonNullCells--;
    }

    return nonNullCells;
  }

  @Override
  public Cell<T> getCell(String columnQualifier) {
    return null;
  }
}
