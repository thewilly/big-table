package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Table.
 *
 * @param <T> the type parameter
 */
public class Table<T extends Comparable<T>> implements Serializable {

    private final Logger log = LoggerFactory.getLogger(Table.class);

    private final String _id;
    private final List<TableRow<T>> _rows;
    private final List<String> _columnQualifiers;

    /**
     * Instantiates a new Table.
     *
     * @param id the id
     */
    public Table(String id) {
        _id = id;
        _rows = new CopyOnWriteArrayList<>();
        _columnQualifiers = new CopyOnWriteArrayList<>();
    }

    /**
     * Read all rows list.
     *
     * @return the list
     */
    public List<TableRow<T>> readAllRows() {
        return _rows;
    }

    /**
     * Scan table async stream.
     *
     * @param filter the filter
     * @return the stream
     */
    public Stream<TableRow<T>> scanTableAsync(Predicate<TableRow<T>> filter) {
        return _rows.parallelStream().filter(filter);
    }

    /**
     * Scan table list.
     *
     * @param filter the filter
     * @return the list
     */
    public List<TableRow<T>> scanTable(Predicate<TableRow<T>> filter) {
        return scanTableAsync(filter).collect(Collectors.toList());
    }

    /**
     * Gets number of columns.
     *
     * @return the number of columns
     */
    public int getNumberOfColumns() {
        return _columnQualifiers.size();
    }
}
