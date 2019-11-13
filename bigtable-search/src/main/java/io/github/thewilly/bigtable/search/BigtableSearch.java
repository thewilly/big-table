package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;

import java.util.stream.Stream;

public abstract class BigtableSearch<T extends Comparable<T>> {

    protected final Bigtable<T> _table;


    public BigtableSearch(Bigtable<T> table) {
        _table = table;
    }

    public abstract Stream<BigtableIndexRow<T>> readRows(String indexIdentifier);
}
