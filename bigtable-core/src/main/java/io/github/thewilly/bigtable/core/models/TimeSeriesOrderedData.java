package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The type Time series ordered data.
 *
 * @param <T> the type parameter
 */
public class TimeSeriesOrderedData implements VersionableDataArray {

  /** The Log. */
  final Logger log = LoggerFactory.getLogger(TimeSeriesOrderedData.class);

  private final LinkedList<VersionableData> _dataVersions;
  private final int _numberOfVersionsToStore;

  /** Instantiates a new Time series ordered data. */
  public TimeSeriesOrderedData() {
    this(DEFAULT_NUMBER_OF_VERSIONS);
  }

  /**
   * Instantiates a new Time series ordered data.
   *
   * @param numberOfVersionsToStore the number of versions to store
   */
  public TimeSeriesOrderedData(int numberOfVersionsToStore) {
    _dataVersions = new LinkedList<>();
    _numberOfVersionsToStore = numberOfVersionsToStore;
    log.debug("Time series data created.");
  }

  /**
   * Gets all data.
   *
   * @return the all data
   */
  public Collection<VersionableData> getAllVersions() {
    log.debug("Getting all the versions from a time series ordered data.");
    return _dataVersions;
  }

  /**
   * Gets most recent data.
   *
   * @return the most recent data
   */
  @Override
  public VersionableData getMostRecentVersion() {
    log.debug("Getting last version from a time series ordered data.");
    return _dataVersions.getLast();
  }

  @Override
  public int getNumberOfVersionsStored() {
    return _dataVersions.size();
  }

  /**
   * Add data boolean.
   *
   * @param data the data
   * @return the boolean
   */
  @Override
  public VersionableData addVersion(VersionableData data) {
    log.debug("Adding {} to the time series ordered data.", data);
    VersionableData previousData = _dataVersions.getLast();
    previousData.invalidate();

    _dataVersions.add(data); // Adding the data to the array.

    if (getNumberOfVersionsStored() > _numberOfVersionsToStore) {
      _dataVersions.removeFirst();
      log.debug("Removing the oldest element from a time series ordered data.");
    }

    return previousData;
  }
}
