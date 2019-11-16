package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** The type Table row localizer. */
public class IndexNode implements Serializable, Comparable<IndexNode> {

  private final String _indexKey;
  private final List<Row> _value;

  private IndexNode(String key, List<Row> value) {
    _indexKey = key;
    _value = value;
  }

  /**
   * Of table row localizer.
   *
   * @param key the key
   * @param value the position
   * @return the table row localizer
   */
  public static IndexNode of(String key, List<Row> value) {
    return new IndexNode(key, value);
  }

  /**
   * Of table row localizer.
   *
   * @param key the key
   * @param value the position
   * @return the table row localizer
   */
  public static IndexNode of(String key, Row value) {
    return new IndexNode(key, Arrays.asList(value));
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public List<Row> getIndexedRows() {
    return _value;
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
