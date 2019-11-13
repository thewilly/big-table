package io.github.thewilly.bigtable;

import io.github.thewilly.bigtable.index.BigtableIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Create bigtable.
 *
 * @param <T> the type parameter
 */
public class CreateBigtable<T extends Comparable<T>> {

    private String tableId = "";
    private int numberOfIndexes = -1;
    private List<BigtableIndex<T>> indexes;

    /**
     * Instantiates a new Create bigtable.
     */
    protected CreateBigtable() {}

    /**
     * With name create bigtable.
     *
     * @param tableId the table id
     * @return the create bigtable
     */
    protected CreateBigtable<T> withName(String tableId) {
       this.tableId = tableId;
       return this;
    }

    /**
     * With max size create bigtable.
     *
     * @param numberOfIndexes the number of indexes
     * @return the create bigtable
     */
    public CreateBigtable<T> withMaxSize(int numberOfIndexes) {
       this.numberOfIndexes = numberOfIndexes;
       return this;
    }

    public CreateBigtable<T> withIndex(BigtableIndex<T> index) {
        if(this.indexes == null)
            this.indexes = new ArrayList<>();

        this.indexes.add(index);

        return this;
    }

    /**
     * Create bigtable.
     *
     * @return the bigtable
     */
    public Bigtable<T> create() {
        final String _tableId = tableId;
        final int _numberOfIndexes = numberOfIndexes;
        this.tableId = "";
        this.numberOfIndexes = -1;

        Bigtable<T> table;

        if(_numberOfIndexes == -1) {
            table = new Bigtable<T>(_tableId);
        } else {
            table = new Bigtable<T>(_tableId, _numberOfIndexes);
        }
        indexes.parallelStream().forEach(index -> table.addIndex(index));

        return table;
    }

}
