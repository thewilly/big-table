package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/** The interface Tree. */
public interface Tree extends Serializable {
  /**
   * Adds an element to the tree. By calling the recursive and private method add(AVLNode<T> root, T
   * element), that will return the top node of the tree.
   *
   * @param content to be added to the tree.
   * @throws IllegalArgumentException the illegal argument exception
   */
  void add(IndexNode content) throws IllegalArgumentException;

  /**
   * Search method. Given a T element it returns true if the element is in the tree. False
   * otherwise.
   *
   * @param content the content
   * @return true if the element is in the tree, false otherwise.
   */
  boolean contains(IndexNode content);

  /**
   * Search and Return method. If the element is in the tree then it search for the element in the
   * tree and returns it.
   *
   * @param element the element
   * @return the t
   */
  IndexNode getIfPresent(IndexNode element);

  /**
   * Travels the Tree in order, that is: leftSubTtree + root + rightSubTree. Null leaves will be
   * represented as "-".
   *
   * <p>Procedure: Traverse the left subtree by recursively calling the in-order function. Display
   * the data part of root element (or current element). Traverse the right subtree by recursively
   * calling the in-order function
   *
   * @return the result of traveling all the tree in order from the top root.
   */
  String getInOrderTraversal();

  /**
   * Public and not reflexive remove method. Given an element as a paramenter it removes it from the
   * tree.
   *
   * @param content the content
   */
  void remove(IndexNode content);

  /**
   * Given the actual tree and a tree as a parameter will return another tree that will be the
   * composition of both trees.
   *
   * @param tree the tree
   * @return a tree containing all the nodes in the first and the second tree.
   */
  IndexTree join(IndexTree tree);

  /**
   * Get Height method. Returns the height of the tree without accessing to the node parameters. To
   * perform that it calls to the private and reflexive getHeight() method with the root of the tree
   * as a parameter.
   *
   * @return the height of the tree as an integer.
   * @important The method considers the height of a single leaf as 0.
   */
  int getHeight();

  /**
   * If the root of a tree is null means that the tree is empty.
   *
   * @return whether a tree is empty or not.
   */
  boolean isEmpty();

  /**
   * Returns a pointer to an auxiliary tree containing all the elements as this tree.
   *
   * @return an auxiliary tree containing all the elements on the working tree.
   */
  IndexTree clone();

  /**
   * Public toString method. Returns a string representation of the object. In general, the toString
   * method returns a string that "textually represents" this object. The result should be a concise
   * but informative representation that is easy for a person to read. It is recommended that all
   * subclasses override this method. In this case the default format is: root+left+right and null
   * pointers as "-" (dash). (Pre-Order)
   *
   * @return toString private and recursive method
   */
  @Override
  String toString();

  /**
   * The current tree as a List shorted.
   *
   * @return the current tree as a list shorted with the default type comparator.
   */
  List<IndexNode> toList();

  /**
   * The current tree as a List shorted.
   *
   * @param comparator used to sort the list.
   * @return the current tree as a list sorted by the custom comparator.
   */
  List<IndexNode> toList(Comparator<IndexNode> comparator);

  /**
   * Returns the tree as an stream of the nodes that compose the tree itself.
   *
   * @return an stream of the nodes in the tree.
   */
  Stream<IndexNode> stream();

  /**
   * Given a tree this method will give the number of nodes that contains.
   *
   * @return the number of nodes contained by the tree.
   */
  int size();

  /**
   * Returns an AVLTree containing the different elements from one tree and another.
   *
   * @param secondTree is the second AVLTree to get the different elements.
   * @return An AVLTree that contains all the elements that are not contained in the other tree. And
   *     works in both ways, elements that are in the first but not in the second and elements that
   *     are in the second but not in the first.
   */
  IndexTree difference(IndexTree secondTree);
}
