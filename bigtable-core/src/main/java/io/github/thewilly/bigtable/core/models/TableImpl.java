package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

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
public class TableImpl implements Table {

  private final Logger log = LoggerFactory.getLogger(TableImpl.class);

  private final String _id;
  private final List<TableRow> _rows;
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
  public Stream<TableRow> getRows() {
    return _rows.parallelStream();
  }

  @Override
  public Stream<String> getColumns() {
    return _columnQualifiers.parallelStream();
  }

  /**
   * Scan table list.
   *
   * @param filter the filter
   * @return the list
   */
  public List<TableRow> scanTable(Predicate<TableRow> filter) {
    return scanTableAsync(filter).collect(Collectors.toList());
  }

  /**
   * Scan table async stream.
   *
   * @param filter the filter
   * @return the stream
   */
  public Stream<TableRow> scanTableAsync(Predicate<TableRow> filter) {
    return _rows.parallelStream().filter(filter);
  }
}
