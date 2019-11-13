package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.BigtableIndex;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Stream;

public class BigtableSearchIntersection<T extends Comparable<T>> extends BigtableSearch<T> {

    private final T[] _queries;

    public BigtableSearchIntersection(Bigtable<T> table, T... queries) {
        super(table);
        _queries = queries;
    }

    @Override
    public Stream<BigtableIndexRow<T>> readRows(String indexIdentifier) {
        BigtableIndex<T> indexToReadRows = _table.getIndex(indexIdentifier);

        throw new NotImplementedException();
    }
}
