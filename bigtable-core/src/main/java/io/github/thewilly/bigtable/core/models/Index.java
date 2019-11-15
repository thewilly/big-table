package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/** The type Index. */
public class Index<ValueType extends Comparable<ValueType>> implements Serializable {

  private final String _id;
  private final IndexTree<TableRowLocalizer<ValueType>> _localizers;
  private final IndexAlgorithm _indexAlgorithm;

  private Index(String id, IndexAlgorithm indexAlgorithm) {
    _id = id;
    _localizers = new IndexTree<>();
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

    table
        .getRows()
        .parallelStream()
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
  public TableRowLocalizer find(String indexKey) {
    return _localizers.searchAndReturn(TableRowLocalizer.of(indexKey, -1));
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
