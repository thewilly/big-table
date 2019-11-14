package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The type Time series ordered data.
 *
 * @param <T> the type parameter
 */
public class TimeSeriesOrderedData<T extends Comparable<T>> implements Serializable {

    /**
     * The Default number of versions.
     */
    static final int DEFAULT_NUMBER_OF_VERSIONS = 100;

    /**
     * The Log.
     */
    final Logger log = LoggerFactory.getLogger(TimeSeriesOrderedData.class);

    private final LinkedList<T> _dataVersions;
    private final int _numberOfVersions;

    /**
     * Instantiates a new Time series ordered data.
     */
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
        _numberOfVersions = numberOfVersionsToStore;
        log.debug("Time series data created.");
    }

    /**
     * Gets all data.
     *
     * @return the all data
     */
    public Collection<T> getAllVersions() {
        log.debug("Getting all the versions from a time series ordered data.");
        return _dataVersions;
    }

    /**
     * Gets most recent data.
     *
     * @return the most recent data
     */
    public T getMostRecentVersion() {
        log.debug("Getting last version from a time series ordered data.");
        return _dataVersions.getLast();
    }

    /**
     * Add data boolean.
     *
     * @param data the data
     * @return the boolean
     */
    public boolean addVersion(T data) {
        log.debug("Adding {} to the time series ordered data.", data);
        boolean addResult =  _dataVersions.add(data);

        if(_dataVersions.size() > DEFAULT_NUMBER_OF_VERSIONS) {
            _dataVersions.removeFirst();
            log.debug("Removing the oldest element from a time series ordered data.");
        }

        return addResult;
    }

}
