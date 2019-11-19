package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.core.BigtableTable;
import io.github.thewilly.bigtable.core.models.*;
import io.github.thewilly.bigtable.core.mutation.RowMutation;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

public class AbstrctSearchTest {

  protected final BigtableTable table = new BigtableTable("test-table");


  @Before
  public void setUp() {
      RowCell rc = new RowCell("c_1");

      for(int i = 1; i < 1000; i++) {
          TableRow tr = new TableRow(rc);
          RowMutation rm = RowMutation.create(tr);


          VersionableData d = new VersionableData() {

              ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
              long timeStamp = System.currentTimeMillis();
              byte[] data = buffer.putLong(timeStamp).array();
              boolean valid = true;

              @Override
              public boolean isValid() {
                  return valid;
              }

              @Override
              public void invalidate() {
                  valid = false;
              }

              @Override
              public long getTimestampt() {
                  return timeStamp;
              }

              @Override
              public byte[] get() {
                  return data;
              }
          };
          rm.updateCell("c_1", d);

          table.getRows().add(tr);
      }
  }

  @Test
  public void testUniqueSearch() {
    System.out.println(table.getNumberOfColumns());
  }
}
