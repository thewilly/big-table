package io.github.cthewilly.bigtable.index;

import io.github.thewilly.bigtable.core.models.Row;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/** The type Table row localizer. */
public class IndexNode implements Serializable, Comparable<IndexNode> {

  private final String _indexKey;
  private final List<Row> _indexedRows;

  private IndexNode(String key, List<Row> value) {
    _indexKey = key;
    _indexedRows = value;
  }

  /**
   * Of table row localizer.
   *
   * @param key the key
   * @param value the position
   * @return the table row localizer
   */
  public static IndexNode of(String key, List<Row> indexedValues) {
    return new IndexNode(key, indexedValues);
  }

  /**
   * Of table row localizer.
   *
   * @param key the key
   * @param value the position
   * @return the table row localizer
   */
  public static IndexNode of(String key, Row indexedValue) {
    return new IndexNode(key, Arrays.asList(indexedValue));
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public List<Row> getIndexedRows() {
    return _indexedRows;
  }

  /**
   * Gets index key.
   *
   * @return the index key
   */
  public String getIndexKey() {
    return _indexKey;
  }

  @Override
  public int compareTo(IndexNode o) {
    return _indexKey.compareTo(o._indexKey);
  }
}
