package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/** The type Table row. */
public class TableRow implements Row {

  private final Logger log = LoggerFactory.getLogger(TableRow.class);

  private final Cell[] _rowCells;

  public TableRow(int size) {
    _rowCells = new RowCell[size];
  }

  /**
   * Gets cell for column qualifier.
   *
   * @param qualifier the qualifier
   * @return the cell for column qualifier
   */
  public Cell getCellForColumnQualifier(String qualifier) {
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
  public Cell[] getCells() {
    return _rowCells;
  }

  @Override
  public Iterator<Cell> iterator() {
    return Arrays.asList(_rowCells).iterator();
  }

  @Override
  public Iterable<Cell> iterable() {
    return Arrays.asList(_rowCells);
  }

  @Override
  public Stream<Cell> stream() {
    List<Cell> cells = Arrays.asList(_rowCells);
    cells.sort(Cell.DEFAULT_COMPARATOR);

    return cells.parallelStream();
  }

  @Override
  public int columnCount() {
    int nonNullCells = _rowCells.length;

    for (Cell cell : _rowCells) {
      if (cell == null) nonNullCells--;
    }

    return nonNullCells;
  }

  @Override
  public Cell getCell(String columnQualifier) {
    for(Cell cell : _rowCells) {
      if(cell.getColumnQualifier().equals(columnQualifier))
        return cell;
    }
    return null;
  }
}
