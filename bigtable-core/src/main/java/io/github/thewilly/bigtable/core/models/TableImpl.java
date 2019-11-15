package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
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
public class TableImpl<T extends Comparable<T>> implements Table<T> {

  private final Logger log = LoggerFactory.getLogger(TableImpl.class);

  private final String _id;
  private final List<TableRow<T>> _rows;
  private final List<String> _columnQualifiers;

  /**
   * Instantiates a new Table.
   *
   * @param id the id
   */
  public TableImpl(String id) {
    _id = id;
    _rows = new CopyOnWriteArrayList<>();
    _columnQualifiers = new CopyOnWriteArrayList<>();
  }

  @Override
  public String getTableId() {
    return _id;
  }

  @Override
  public List<TableRow<T>> getRows() {
    return _rows;
  }

  @Override
  public Collection<String> getColumns() {
    return _columnQualifiers;
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
}
