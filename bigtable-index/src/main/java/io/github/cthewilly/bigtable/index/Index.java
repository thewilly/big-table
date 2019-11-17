package io.github.cthewilly.bigtable.index;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * @author Guillermo Facundo Colunga (@thewilly)
 */
public interface Index extends Serializable {

  /**
   * Gets the index unique identifier.
   *
   * @return the string containing the index unique identifier.
   */
  String getId();

  /**
   * Gets the index algorithm that is being used to index data.
   *
   * @return the index algorithm that is being used to index data.
   */
  IndexAlgorithm getIndexAlgorithm();

  /**
   * Gets all the nodes in the index as an stream so they can be processed without loading them in
   * to memory.
   *
   * @return the stream containing all the nodes that compose the index.
   */
  Stream<IndexNode> stream();

  /**
   * Gets the index node indexed with the given indexKey.
   *
   * @param indexKey to search for the indexNode.
   * @return the index node if present. Null otherwise.
   */
  IndexNode find(String indexKey);
}
