package io.github.thewilly.bigtable;

public class CreateBigtable<T extends Comparable<T>> {

    private String tableId = "";
    private int numberOfIndexes = -1;

    protected CreateBigtable() {}

    public CreateBigtable<T> withName(String tableId) {
       this.tableId = tableId;
       return this;
    }

    public CreateBigtable<T> withMaxSize(int numberOfIndexes) {
       this.numberOfIndexes = numberOfIndexes;
       return this;
    }

    public Bigtable<T> create() {
        final String _tableId = tableId;
        final int _numberOfIndexes = numberOfIndexes;
        this.tableId = "";
        this.numberOfIndexes = -1;

        if(_numberOfIndexes == -1)
            return new Bigtable<T>(_tableId);

        return new Bigtable<T>(_tableId, _numberOfIndexes);
    }

}
