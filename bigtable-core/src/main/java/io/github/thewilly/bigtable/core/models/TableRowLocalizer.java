package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/**
 * The type Table row localizer.
 */
public class TableRowLocalizer implements Serializable, Comparable<TableRowLocalizer> {

    private final String _indexKey;
    private final int _position;

    private TableRowLocalizer(String key, int position) {
        _indexKey = key;
        _position = position;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return _position;
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
     * @param key      the key
     * @param position the position
     * @return the table row localizer
     */
    public static TableRowLocalizer of(String key, int position) {
        return new TableRowLocalizer(key, position);
    }

    @Override
    public int compareTo(TableRowLocalizer o) {
       return _indexKey.compareTo(o._indexKey);
    }
}
