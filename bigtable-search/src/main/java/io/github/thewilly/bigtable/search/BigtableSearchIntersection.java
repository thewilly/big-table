package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;
import io.github.thewilly.bigtable.index.Index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/** The type Bigtable search intersection. */
public class BigtableSearchIntersection extends BigtableSearch {

  private final String[] _queries;

  /**
   * Instantiates a new Bigtable search intersection.
   *
   * @param table the table
   * @param queries the queries
   */
  public BigtableSearchIntersection(Table table, String... queries) {
    super(table);
    _queries = queries;
  }

  @Override
  public Stream<Row> findIndexedRows(Index indexToExecuteSearch) {

    return Arrays.stream(_queries)
        .parallel()
        .map(query -> indexToExecuteSearch.find(query).getIndexedRows())
        .reduce(
            (row1, row2) -> {
              List<Row> commonRows = new ArrayList<>();
              for (Row row : row1) {
                if (row2.contains(row)) commonRows.add(row);
              }
              return commonRows;
            })
        .get()
        .parallelStream();
  }
}
