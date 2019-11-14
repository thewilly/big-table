package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;

/**
 * The type Table cell.
 *
 * @param <T> the type parameter
 */
public class RowCell<T extends Comparable<T>> implements Serializable {

    /**
     * The Log.
     */
    final Logger log = LoggerFactory.getLogger(RowCell.class);

    private final TimeSeriesOrderedData<T> _cellData;
    private final String _columnQualifier;

    /**
     * Instantiates a new Table cell.
     *
     * @param columnQualifier the column qualifier
     */
    public RowCell(String columnQualifier) {
        _cellData = new TimeSeriesOrderedData<>();
        _columnQualifier = columnQualifier;
        log.debug("Table cell created.");
    }

    /**
     * Gets column qualifier.
     *
     * @return the column qualifier
     */
    public String getColumnQualifier() {
        return _columnQualifier;
    }

    /**
     * Set boolean.
     *
     * @param data the data
     * @return the boolean
     */
    public boolean set(T data) {
        log.debug("Added {} to the table cell.", data);
        return _cellData.addVersion(data);
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public TimeSeriesOrderedData<T> getValue() {
        log.debug("Reading value of the table cell.");
        return _cellData;
    }
}
