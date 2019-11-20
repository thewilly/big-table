package io.github.thewilly.bigtable.core;


import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Comparator;
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
  public Collection<String> accessColumns() {
    return _columnQualifiers;
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

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    _columnQualifiers.sort(new Comparator<String>() {
      @Override
      public int compare(String s, String t1) {
        return s.compareTo(t1);
      }
    });

    for(String s : _columnQualifiers) {
      sb.append(s.toUpperCase());
      sb.append("\t\t");
    }
    sb.append("\n");

    for(Row row : _rows) {
      sb.append(row);
      sb.append("\n");
    }

    return sb.toString();
  }
}
