package io.github.thewilly.bigtable.core.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.Serializable;

/**
 * The type Table row.
 *
 * @param <T> the type parameter
 */
public class TableRow<T extends Comparable<T>> implements Serializable {

    private final Logger log = LoggerFactory.getLogger(TableRow.class);

    private final RowCell<T>[] _rowCells;

    private TableRow(int size) {
        _rowCells = new RowCell[size];
    }

    /**
     * Gets cell for column qualifier.
     *
     * @param qualifier the qualifier
     * @return the cell for column qualifier
     */
    public RowCell<T> getCellForColumnQualifier(String qualifier) {
        for(RowCell cell : _rowCells) {
            if(cell.getColumnQualifier().equals(qualifier))
                return cell;
        } return null;
    }

    /**
     * Get all cells table cell [ ].
     *
     * @return the table cell [ ]
     */
    public RowCell<T>[] getCells() {
        return _rowCells;
    }
}
