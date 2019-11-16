package io.github.thewilly.bigtable.core.models;

import java.util.stream.Stream;

/** The interface Index algorithm. */
public interface IndexAlgorithm {

  /**
   * Index row list.
   *
   * @param <T> the type parameter
   * @param row the row
   * @return the list
   */
  <T extends Comparable<T>> Stream<IndexNode> indexRow(TableRow row);
}
