package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.BigtableIndex;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;

import java.util.Arrays;
import java.util.stream.Stream;

public class BigtableSearchUnique<T extends Comparable<T>> extends BigtableSearch<T> {

    private final T _query;

    public BigtableSearchUnique(Bigtable<T> table, T query) {
        super(table);
        _query = query;
    }

    @Override
    public Stream<BigtableIndexRow<T>> readRows(String indexIdentifier) {
        final BigtableIndex<T> _index = _table.getIndex(indexIdentifier);

        return Arrays.stream(_index.getHitsForElement(_query));
    }
}
