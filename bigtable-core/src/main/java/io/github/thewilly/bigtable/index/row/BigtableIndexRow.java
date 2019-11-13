package io.github.thewilly.bigtable.index.row;

import io.github.thewilly.bigtable.index.row.mutation.BigtableIndexRowMutation;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Bigtable index row.
 *
 * @param <T> the type parameter
 */
public class BigtableIndexRow<T extends Comparable<T>> implements Serializable {

    private final List<T> _rowData;

    /**
     * Instantiates a new Bigtable index row.
     */
    public BigtableIndexRow() {
        _rowData = new LinkedList<T>();
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<T> getData() {
        return _rowData;
    }

    /**
     * Gets row size.
     *
     * @return the row size
     */
    public int getRowSize() {
        return _rowData.size();
    }

    /**
     * Mutate row.
     *
     * @param action the action
     */
    public void mutateRow(BigtableIndexRowMutation action) {
        action.setRowToMutate(this);
        action.executeAction();
    }
}
