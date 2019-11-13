package io.github.thewilly.bigtable;

import io.github.thewilly.bigtable.index.BigtableIndex;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The interface Bigtable.
 *
 */
public class Bigtable<T extends Comparable<T>> implements Serializable {

    static final int DEFAULT_NUMBER_OF_INDEXES = 3;

    private final Map<String, BigtableIndex<T>> _indexes;
    private final int _numberOfIndexes;
    private final String _tableId;

    protected Bigtable(String tableId) {
        this(tableId, DEFAULT_NUMBER_OF_INDEXES);
    }

    protected Bigtable(String tableId, int numberOfIndexes) {
        _indexes = new HashMap<>(numberOfIndexes);
        _numberOfIndexes = numberOfIndexes;
        _tableId = tableId;
    }

    public BigtableIndex<T> getIndex(String indexIdentifier) {
        return _indexes.get(indexIdentifier);
    }

    private boolean addIndex(BigtableIndex<T> index) {
        if(_indexes.size() == _numberOfIndexes)
            return false;
        _indexes.put(index.getIndexIdentifier(), index);
        return true;
    }

    public static <T extends Comparable<T>> CreateBigtable<T> of(String tableId) {
        return new CreateBigtable<T>().withName(tableId);
    }
}
