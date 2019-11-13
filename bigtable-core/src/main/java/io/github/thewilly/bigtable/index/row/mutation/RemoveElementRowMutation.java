package io.github.thewilly.bigtable.index.row.mutation;

/**
 * The type Remove element row mutation.
 *
 * @param <T> the type parameter
 */
public class RemoveElementRowMutation<T extends Comparable<T>> extends BigtableIndexRowMutation<T> {

    private final T _element;

    /**
     * Instantiates a new Remove element row mutation.
     *
     * @param element the element
     */
    public RemoveElementRowMutation(T element) {
        super();
        _element = element;
    }

    @Override
    public boolean executeAction() {
        return _rowToMutate.getData().add(_element);
    }
}
