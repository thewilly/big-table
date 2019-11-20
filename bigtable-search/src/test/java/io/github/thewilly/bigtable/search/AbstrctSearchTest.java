package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.core.BigtableTable;
import io.github.thewilly.bigtable.core.models.DataVersion;
import io.github.thewilly.bigtable.core.models.RowCell;
import io.github.thewilly.bigtable.core.models.TableRow;
import io.github.thewilly.bigtable.core.models.TimeSeriesOrderedData;
import io.github.thewilly.bigtable.core.mutation.RowMutation;
import io.github.thewilly.bigtable.core.mutation.TableMutation;
import org.junit.Before;
import org.junit.Test;

public class AbstrctSearchTest {

  protected final BigtableTable table = new BigtableTable("test-table");

  @Before
  public void setUp() {
    TableRow tr = null;
    RowCell rc = new RowCell("c_1");

    TableMutation tm = TableMutation.create(table);

    for (int i = 1; i < 1000; i++) {
      rc = new RowCell("c_1"+i);
      tr = new TableRow(rc);
      RowMutation rm = RowMutation.create(tr);

      DataVersion data = new TimeSeriesOrderedData(new byte[1]);

      rm.updateCell("c_1", data);

      tm.addRow(tr);
    }
  }

  @Test
  public void testUniqueSearch() {
    System.out.println("Columns -> " + table.getNumberOfColumns());
    System.out.println("Rows -> " + table.getNumberOfRows());
    System.out.println(table);
  }
}
