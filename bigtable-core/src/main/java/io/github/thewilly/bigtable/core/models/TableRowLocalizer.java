package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/** The type Table row localizer. */
public class TableRowLocalizer<V extends Comparable<V>>
    implements Serializable, Comparable<TableRowLocalizer<V>> {

  private final String _indexKey;
  private final V _value;

  private TableRowLocalizer(String key, V value) {
    _indexKey = key;
    _value = value;
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public V getPosition() {
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

  /**
   * Of table row localizer.
   *
   * @param key the key
   * @param value the position
   * @return the table row localizer
   */
  public static <T extends Comparable<T>> TableRowLocalizer of(String key, T value) {
    return new TableRowLocalizer<T>(key, value);
  }

  @Override
  public int compareTo(TableRowLocalizer<V> o) {
    return _indexKey.compareTo(o._indexKey);
  }
}
