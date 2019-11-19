package io.github.thewilly.bigtable.core.models;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * The type Time series ordered data.
 *
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
  public Stream<VersionableData> getAllVersions() {
    log.debug("Getting all the versions from a time series ordered data.");
    return _dataVersions.parallelStream();
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
