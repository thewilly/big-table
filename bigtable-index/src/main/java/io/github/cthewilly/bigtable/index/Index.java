package io.github.cthewilly.bigtable.index;

import io.github.thewilly.bigtable.core.models.*;

import java.io.Serializable;

/** The type Index. @param <ValueType> the type parameter @param <ValueType> the type parameter */
public class Index implements Serializable {

  private final String _id;
  private final IndexTree _localizers;
  private final IndexAlgorithm _indexAlgorithm;

  private Index(String id, IndexAlgorithm indexAlgorithm) {
    _id = id;
    _localizers = new IndexTree();
    _indexAlgorithm = indexAlgorithm;
  }

  /**
   * Create index.
   *
   * @param <T> the type parameter
   * @param id the id
   * @param indexAlgorithm the index algorithm
   * @param table the table
   * @return the index
   */
  public static <T extends Comparable<T>> Index create(
      String id, IndexAlgorithm indexAlgorithm, TableImpl<T> table) {
    Index index = new Index(id, indexAlgorithm);

    table.getRows()
        .map(index._indexAlgorithm::indexRow)
        .forEach(localizer -> localizer.forEach(index._localizers::add));

    return index;
  }

  /**
   * Find table row localizer.
   *
   * @param indexKey the index key
   * @return the table row localizer
   */
  public IndexNode find(String indexKey) {
    Cell auxCell = new RowCell("");
    Row row = new TableRow(1);
    return _localizers.getIfPresent(IndexNode.of(indexKey, auxCell));
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return _id;
  }

  /**
   * Gets index algorithm.
   *
   * @return the index algorithm
   */
  public IndexAlgorithm getIndexAlgorithm() {
    return _indexAlgorithm;
  }
}
