package io.github.thewilly.bigtable.index;

import io.github.thewilly.bigtable.core.models.Row;

import java.util.stream.Stream;

/** The interface Index algorithm. */
public interface IndexAlgorithm {

  /**
   * Index row list.
   *
   * @param row the row
   * @return the list
   */
  Stream<IndexNode> indexRow(Row row);
}
