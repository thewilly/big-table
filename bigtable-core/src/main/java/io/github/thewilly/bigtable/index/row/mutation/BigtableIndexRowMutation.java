package io.github.thewilly.bigtable.index.row.mutation;

import io.github.thewilly.bigtable.index.row.BigtableIndexRow;

/**
 * The interface Bigtable index row action.
 *
 * @param <T> the type parameter
 */
public abstract class BigtableIndexRowMutation<T extends Comparable<T>> {

    /**
     * The Row to mutate.
     */
    protected BigtableIndexRow<T> _rowToMutate;

    /**
     * Add element bigtable index row mutation.
     *
     * @param <T>     the type parameter
     * @param element the element
     * @return the bigtable index row mutation
     */
    public static <T extends Comparable<T>> BigtableIndexRowMutation<T> addElement(T element) {
        return new AddElementRowMutation(element);
    }

    /**
     * Remove element bigtable index row mutation.
     *
     * @param <T>     the type parameter
     * @param element the element
     * @return the bigtable index row mutation
     */
    public static <T extends Comparable<T>> BigtableIndexRowMutation<T> removeElement(T element) {
        return new RemoveElementRowMutation(element);
    }

    /**
     * Sets row to mutate.
     *
     * @param rowToMutate the row to mutate
     */
    public void setRowToMutate(BigtableIndexRow<T> rowToMutate) {
        _rowToMutate = rowToMutate;
    }

    /**
     * Execute action boolean.
     *
     * @return the boolean
     */
    public abstract boolean executeAction();
}
