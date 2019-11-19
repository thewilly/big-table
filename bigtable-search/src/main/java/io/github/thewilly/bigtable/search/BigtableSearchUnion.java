package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;
import io.github.thewilly.bigtable.index.Index;

import java.util.*;
import java.util.stream.Stream;

/** The type Bigtable search union. */
public class BigtableSearchUnion extends BigtableSearch {

  private final String[] _queries;

  /**
   * Instantiates a new Bigtable search union.
   *
   * @param table the table
   * @param queries the queries
   */
  public BigtableSearchUnion(Table table, String... queries) {
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
              List<Row> allHits = new ArrayList<>();
              Set<Row> addedElements = new HashSet<>();
              for (Row row : row1) {
                allHits.add(row);
                addedElements.add(row);
              }

              // Add all elements in row2 if they had not been already added.
              for (Row row : row2) {
                if (!addedElements.contains(row)) allHits.add(row);
              }
              return allHits;
            })
        .get()
        .parallelStream();
  }
}
