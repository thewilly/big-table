package io.github.thewilly.bigtable.core.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The type Table cell. */
public class RowCell implements Cell {

  /** The Log. */
  final Logger log = LoggerFactory.getLogger(RowCell.class);

  private final VersionableDataArray _cellData;
  private final String _columnQualifier;

  private long timestamp;

  /**
   * Instantiates a new Table cell.
   *
   * @param columnQualifier the column qualifier
   */
  public RowCell(String columnQualifier) {
    _cellData = new TimeSeriesOrderedData();
    _columnQualifier = columnQualifier;
    this.timestamp = System.currentTimeMillis();
    log.debug("Table cell created.");
  }

  @Override
  public String getColumnQualifier() {
    log.debug("Reading the column qualifier of a table cell.");
    return _columnQualifier;
  }

  @Override
  public long getTimeStamp() {
    log.debug("Reading the timestamp of a table cell.");
    return timestamp;
  }

  @Override
  public int getNumberOfVersions() {
    log.debug("Reading the number of versions of a table cell.");
    return (int) _cellData.getAllVersions().count();
  }

  @Override
  public VersionableDataArray getDataVersions() {
    log.debug("Reading value of the table cell.");
    return _cellData;
  }

  @Override
  public VersionableData addDataVersion(VersionableData data) {
    log.debug("Added {} to the table cell.", data);
    return _cellData.addVersion(data);
  }

  @Override
  public int compareTo(Cell o) {
    log.debug("Comparing table cell " + this.toString() + " against " + o.toString());
    return 0;
  }

  @Override
  public String toString() {
    return "CELL -> "
        + "["
        + _cellData
        + "]"
        + " CQ -> "
        + _columnQualifier
        + " TIME -> "
        + timestamp;
  }
}
