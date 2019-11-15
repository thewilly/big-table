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
public class TimeSeriesOrderedData<T extends Comparable<T>> implements VersionableDataArray<T> {

  /** The Log. */
  final Logger log = LoggerFactory.getLogger(TimeSeriesOrderedData.class);

  private final LinkedList<VersionableData<T>> _dataVersions;
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
  public Collection<VersionableData<T>> getAllVersions() {
    log.debug("Getting all the versions from a time series ordered data.");
    return _dataVersions;
  }

  /**
   * Gets most recent data.
   *
   * @return the most recent data
   */
  @Override
  public VersionableData<T> getMostRecentVersion() {
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
  public boolean addVersion(VersionableData<T> data) {
    log.debug("Adding {} to the time series ordered data.", data);
    _dataVersions.getLast().invalidate();
    boolean addResult = _dataVersions.add(data);

    if (getNumberOfVersionsStored() > _numberOfVersionsToStore) {
      _dataVersions.removeFirst();
      log.debug("Removing the oldest element from a time series ordered data.");
    }

    return addResult;
  }
}