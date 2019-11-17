package io.github.thewilly.bigtable.core;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Table.
 *
 */
public class BigtableTable implements Table {

  private final Logger log = LoggerFactory.getLogger(BigtableTable.class);

  private final String _id;
  private final List<Row> _rows;
  private final List<String> _columnQualifiers;

  /**
   * Instantiates a new Table.
   *
   * @param id the id
   */
  public BigtableTable(String id) {
    _id = id;
    _rows = new CopyOnWriteArrayList<>();
    _columnQualifiers = new CopyOnWriteArrayList<>();
  }

  @Override
  public String getTableId() {
    return _id;
  }

  @Override
  public Stream<Row> stream() {
    return _rows.parallelStream();
  }

  @Override
  public Collection<Row> getRows() { return _rows; }

  @Override
  public Stream<String> getColumns() {
    return _columnQualifiers.parallelStream();
  }

  @Override
  public int getNumberOfColumns() {
    return _columnQualifiers.size();
  }

  @Override
  public int getNumberOfRows() {
    return _rows.size();
  }

  /**
   * Scan table list.
   *
   * @param filter the filter
   * @return the list
   */
  public List<Row> scanTable(Predicate<Row> filter) {
    return scanTableAsync(filter).collect(Collectors.toList());
  }

  /**
   * Scan table async stream.
   *
   * @param filter the filter
   * @return the stream
   */
  public Stream<Row> scanTableAsync(Predicate<Row> filter) {
    return _rows.parallelStream().filter(filter);
  }
}
