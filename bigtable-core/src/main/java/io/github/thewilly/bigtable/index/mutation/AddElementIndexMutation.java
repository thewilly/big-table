package io.github.thewilly.bigtable.index.mutation;

import io.github.thewilly.bigtable.index.row.mutation.AddElementRowMutation;
import io.github.thewilly.bigtable.index.row.mutation.BigtableIndexRowMutation;

/**
 * The type Add element index mutation.
 *
 * @param <T> the type parameter
 */
public class AddElementIndexMutation<T extends Comparable<T>> extends BigtableIndexMutation<T> {

    private final T _element;

    /**
     * Instantiates a new Add element index mutation.
     *
     * @param element the element
     */
    public  AddElementIndexMutation(T element) {
        super();
        _element = element;
    }

    @Override
    public boolean executeAction() {
        int[] indexes = _indexToMutate.getIndexAlgorithm().computeKeys(_element);
        BigtableIndexRowMutation rowMutation = new AddElementRowMutation(_element);

        for(int index : indexes) {
            rowMutation.setRowToMutate(_indexToMutate.getRowAtIndex(index));
            rowMutation.executeAction();
        }
        return true;
    }
}
