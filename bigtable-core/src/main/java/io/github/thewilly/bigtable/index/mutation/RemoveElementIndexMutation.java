package io.github.thewilly.bigtable.index.mutation;

import io.github.thewilly.bigtable.index.row.mutation.BigtableIndexRowMutation;
import io.github.thewilly.bigtable.index.row.mutation.RemoveElementRowMutation;

/**
 * The type Remove element index mutation.
 *
 * @param <T> the type parameter
 */
public class RemoveElementIndexMutation<T extends Comparable<T>> extends BigtableIndexMutation<T> {

    private final T _element;

    /**
     * Instantiates a new Remove element index mutation.
     *
     * @param element the element
     */
    public RemoveElementIndexMutation(T element) {
        super();
        _element = element;
    }

    @Override
    public boolean executeAction() {
        int[] indexes = _indexToMutate.getIndexAlgorithm().computeKeys(_element);
        BigtableIndexRowMutation rowMutation = new RemoveElementRowMutation(_element);

        for(int index : indexes) {
            rowMutation.setRowToMutate(_indexToMutate.getRowAtIndex(index));
            rowMutation.executeAction();
        }
        return true;
    }
}
