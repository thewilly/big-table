package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.Bigtable;
import io.github.thewilly.bigtable.index.BigtableIndex;
import io.github.thewilly.bigtable.index.row.BigtableIndexRow;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Stream;

/**
 * The type Bigtable search union.
 *
 * @param <T> the type parameter
 */
public class BigtableSearchUnion<T extends Comparable<T>> extends BigtableSearch<T> {

    private final T[] _queries;

    /**
     * Instantiates a new Bigtable search union.
     *
     * @param table   the table
     * @param queries the queries
     */
    public BigtableSearchUnion(Bigtable<T> table, T... queries) {
        super(table);
        _queries = queries;
    }

    @Override
    public Stream<BigtableIndexRow<T>> readRows(String indexIdentifier) {
        BigtableIndex<T> indexToReadRows = _table.getIndex(indexIdentifier);

        BigtableIndexRow<T>[] rowHits = Arrays.stream( _queries ).parallel()
                .map( query -> indexToReadRows.getHitsForElement(query))
                .reduce(
                        (row1, row2) -> {
                            List<BigtableIndexRow<T>> commonRows = new ArrayList<>();
                            Set<BigtableIndexRow<T>> addedElements = new HashSet<>();
                            for(BigtableIndexRow<T> row : row1) {
                                commonRows.add(row);
                                addedElements.add(row);
                            }
                            for(BigtableIndexRow<T> row : row2) {
                                if(!addedElements.contains(row))
                                    commonRows.add(row);
                            }
                            BigtableIndexRow<T>[] commonRowsArray = new BigtableIndexRow[commonRows.size()];
                            return commonRows.toArray(commonRowsArray);
                        }
                ).get();

        return Arrays.stream(rowHits).parallel();
    }
}
