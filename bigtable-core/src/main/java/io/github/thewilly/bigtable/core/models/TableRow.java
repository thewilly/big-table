package io.github.thewilly.bigtable.core.models;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/** The type Table row. */
public class TableRow implements Row {

  private final Logger log = LoggerFactory.getLogger(TableRow.class);

  private final List<Cell> _rowCells;

  public TableRow(int size) {
    _rowCells = new ArrayList<>(size);
  }

  public TableRow(Cell... cells) {
    this(cells.length);
    for(Cell cell : cells) {
      _rowCells.add(cell);
    }
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
  public List<Cell> getCells() {
    return _rowCells;
  }

  @Override
  public Iterator<Cell> iterator() {
    return _rowCells.iterator();
  }

  @Override
  public Iterable<Cell> iterable() {
    return _rowCells;
  }

  @Override
  public Stream<Cell> stream() {
    List<Cell> cells = _rowCells;
    cells.sort(Cell.DEFAULT_COMPARATOR);

    return cells.parallelStream();
  }

  @Override
  public int columnCount() {
    int nonNullCells = _rowCells.size();

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

  @Override
  public String toString() {
    List<Cell> cells = _rowCells;
    cells.sort(Cell.DEFAULT_COMPARATOR);

    StringBuilder sb = new StringBuilder();
    for(Cell cell : cells) {
      sb.append(cell.toString());
      sb.append("\t\t");
    }
    return sb.toString();
  }
}
