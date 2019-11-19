package io.github.thewilly.bigtable.search;

import io.github.thewilly.bigtable.core.models.Row;
import io.github.thewilly.bigtable.core.models.Table;
import io.github.thewilly.bigtable.index.Index;

import java.util.stream.Stream;

/**
 * The type Bigtable search unique.
 *
 */
public class BigtableSearchUnique extends BigtableSearch {

  private final String _query;

  /**
   * Instantiates a new Bigtable search unique.
   *
   * @param table the table
   * @param query the query
   */
  public BigtableSearchUnique(Table table, String query) {
    super(table);
    _query = query;
  }

  @Override
  public Stream<Row> findIndexedRows(Index indexToExecuteSearch) {
    Row hit = indexToExecuteSearch.find(_query).getIndexedRows().get(0);
    return Stream.of(hit).parallel();
  }
}
