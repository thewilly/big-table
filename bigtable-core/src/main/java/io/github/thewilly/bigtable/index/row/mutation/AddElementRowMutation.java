package io.github.thewilly.bigtable.index.row.mutation;

/**
 * The type Add element row mutation.
 *
 * @param <T> the type parameter
 */
public class AddElementRowMutation<T extends Comparable<T>> extends BigtableIndexRowMutation<T> {

    private final T _element;

    /**
     * Instantiates a new Add element row mutation.
     *
     * @param element the element
     */
    public AddElementRowMutation(T element) {
        super();
        _element = element;
    }

    @Override
    public boolean executeAction() {
        return _rowToMutate.getData().remove(_element);
    }
}
