package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A cell is a unit of storage for bigtable, it consists of the following fields: 1) column
 * qualifier 2) timestamp 3) number of versions 4) versions
 */
public interface Cell<T extends Comparable<T>> extends Serializable, Comparable<Cell<T>> {

  Comparator<Cell> DEFAULT_COMPARATOR = Comparator.comparing(Cell::getColumnQualifier);

  /**
   * Gets the column qualifier. The column qualifier is the unique string that identifies a column.
   *
   * @return the column qualifier as a String.
   */
  String getColumnQualifier();

  /**
   * Gets the time stamp. The timestamp represent the last univresal time when the cell was updated.
   *
   * @return the time stamp as a long.
   */
  long getTimeStamp();

  /**
   * Gets the number of versions that the cell stores.
   *
   * @return the number of versions as an Integer.
   */
  int getNumberOfVersions();

  /**
   * Gets the data versions including the current valid one.
   *
   * @return the data contained by the cell.
   */
  VersionableDataArray<T> getDataVersions();

  /**
   * Adds a new data version to the cell by invalidating the previous one and seting this one as the
   * valid current one.
   *
   * @param data is the data version to add to the cell.
   * @return true if added. false otherwise.
   */
  boolean addDataVersion(VersionableData<T> data);
}
