package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Index tree.
 *
 * @param <T> the type parameter
 */
public class IndexTree<T extends Comparable<T>> implements Serializable {

  /** The root node. */
  private IndexTreeNode<T> rootNode;

  /** Main Constructor. It doesn't need any parameter. will create an AVL Tree with a null root. */
  public IndexTree() {
    setRootNode(null);
  }

  /**
   * Allocates an AVLTree object and initializes it with the root as a root element of the tree.
   *
   * @param root of the tree
   */
  public IndexTree(T root) {
    setRootNode(null);
    add(root);
  }

  /**
   * Allocates an AVLTree object and initializes with the elements given.
   *
   * @param elements to create the tree.
   */
  @SafeVarargs
  public IndexTree(T... elements) {
    for (T element : elements) {
      this.add(element);
    }
  }

  /**
   * Returns the root of the tree, if there is. Otherwise null.
   *
   * @return the AVLNode that represents the root of the tree if there is. Otherwise null.
   */
  private IndexTreeNode<T> getRootNode() {
    return this.rootNode;
  }

  /**
   * Sets the root of the tree. As null is a dangerous value if the root is set to null will print a
   * warning message.
   *
   * @param root AVLNode to be the root of the tree.
   */
  private void setRootNode(IndexTreeNode<T> root) {
    this.rootNode = root;
  }

  /**
   * Adds an element to the tree. By calling the recursive and private method add(AVLNode<T> root, T
   * element), that will return the top node of the tree.
   *
   * @param content to be added to the tree.
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void add(T content) throws IllegalArgumentException {
    setRootNode(add(getRootNode(), content));
  }

  /**
   * Recursive add method. If the root given is null it will create a new AVLNode assigning the
   * value of the node we want to add there. If not will allocate the element in its place and then
   * will return again the root.
   *
   * @param root of the AVL tree.
   * @param content to be added to the tree.
   * @return the root of the tree.
   * @throws IllegalArgumentException the illegal argument exception
   */
  private IndexTreeNode<T> add(IndexTreeNode<T> root, T content) throws IllegalArgumentException {
    if (content == null) {
      throw new IllegalArgumentException("The element you want to add was null.");
    } else if (root == null) {
      return new IndexTreeNode<T>(content);
    } else if (root.getContent().equals(content)) {
      throw new IllegalArgumentException("No repeated elements are allowed inside a tree.");
    } else if (content.compareTo(root.getContent()) < 0) {
      root.setLeft(add(root.getLeft(), content));
    } else {
      root.setRight(add(root.getRight(), content));
    }
    return (this.updateBalanceFactor(root));
  }

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
  public String toString() {
    return toString(rootNode);
  }

  /**
   * toString private recursive method. While the root is different from null the method will
   * continue traversing the tree and adding the nodes to the StringBuilder. Instead of String
   * concatenation it uses StringBuilder because as it is a recursive method we improve the
   * performance.
   *
   * @param root the root
   * @return null if root = null. Otherwise: "root+left+right". Pre-Order.
   */
  private String toString(IndexTreeNode<T> root) {
    StringBuilder str = new StringBuilder();
    if (root == null) {
      str.append("-");
    } else {
      str.append(root.toString());
      str.append(toString(root.getLeft())).append(toString(root.getRight()));
    }
    return str.toString();
  }

  /**
   * Search method. Given a T element it returns true if the element is in the tree. False
   * otherwise.
   *
   * @param content the content
   * @return true if the element is in the tree, false otherwise.
   */
  public boolean contains(T content) {
    return contains(content, getRootNode());
  }

  /**
   * Search private and reflexive method. Given a T element and a root it checks if the T element is
   * in the tree.
   *
   * @param content the content
   * @param root the root
   * @return true if the element is in the tree, false otherwise.
   */
  private boolean contains(T content, IndexTreeNode<T> root) {
    if (root == null || content == null) {
      return false;
    } else if (root.getContent().equals(content)) {
      return true;
    } else if (content.compareTo(root.getContent()) < 0) {
      return contains(content, root.getLeft());
    } else if (content.compareTo(root.getContent()) > 0) {
      return contains(content, root.getRight());
    }
    return false;
  }

  /**
   * Search and Return method. If the element is in the tree then it search for the element in the
   * tree and returns it.
   *
   * @param element the element
   * @return the t
   */
  public T getIfPresent(T element) {
    if (contains(element)) return getIfPresent(element, getRootNode());
    else return null;
  }

  /**
   * Search and return private and reflexive method. Given a T element and a root it returns the
   * element.
   *
   * @param element the element
   * @param root the root
   * @return The element you are looking for.
   */
  private T getIfPresent(T element, IndexTreeNode<T> root) {
    if (root.getContent().equals(element)) {
      return root.getContent();
    } else if (element.compareTo(root.getContent()) < 0) {
      return getIfPresent(element, root.getLeft());
    } else {
      return getIfPresent(element, root.getRight());
    }
  }

  /**
   * Get the maximum value in the tree. Calls the recursive and private method T getMax(AVLNode<T>
   * root).
   *
   * @return the maximum value in the tree.
   */
  private T getMax() {
    return getMax(getRootNode());
  }

  /**
   * Private and recursive getMax Method, gets the maximum value in the tree by means of recursion.
   * Notice that the max value of a tree is always allocated at the bottom right position.
   *
   * @param root of the tree.
   * @return the maximum value of the tree.
   */
  private T getMax(IndexTreeNode<T> root) {
    if (root == null) {
      return null;
    } else if (root.getRight() != null) {
      return getMax(root.getRight());
    }
    return root.getContent();
  }

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
  public String getInOrderTraversal() {
    return getInOrderTraversal(this.getRootNode());
  }

  /**
   * Recursive method to traverse the tree from a given root. It travels in order as left + root +
   * right. Null leaves will be represented as "-"
   *
   * @param root of the tree or subtree.
   * @return an String containing all the traverse path.
   */
  private String getInOrderTraversal(IndexTreeNode<T> root) {
    StringBuilder aux = new StringBuilder();
    if (root == null) return "-";

    aux.append(getInOrderTraversal(root.getLeft()));
    aux.append(root.getContent());
    aux.append(getInOrderTraversal(root.getRight()));

    return aux.toString();
  }

  /**
   * Public and not reflexive remove method. Given an element as a paramenter it removes it from the
   * tree.
   *
   * @param content the content
   */
  public void remove(T content) {
    setRootNode(remove(rootNode, content));
  }

  /**
   * Private and reflexive remove method. Given an element as a parameter and a root it removes the
   * element from the tree.
   *
   * @param root where you start to look for the node
   * @param content the content
   * @return the deleted node.
   */
  public IndexTreeNode<T> remove(IndexTreeNode<T> root, T content) {
    if (!contains(content)) {
      throw new IllegalArgumentException("The provided element is not in the tree.");
    } else if (root == null) {
      throw new IllegalArgumentException("The provided root is null.");
    } else if (root.getContent().equals(content)) {
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      } else {
        root.setContent(getMax(root.getLeft()));
        root.setLeft(remove(root.getLeft(), root.getContent()));
      }
    } else if (content.compareTo(root.getContent()) < 0) {
      root.setLeft(remove(root.getLeft(), content));
    } else {
      root.setRight(remove(root.getRight(), content));
    }
    return (updateBalanceFactor(root));
  }

  /**
   * Given the actual tree and a tree as a parameter will return another tree that will be the
   * composition of both trees.
   *
   * @param tree the tree
   * @return a tree containing all the nodes in the first and the second tree.
   */
  public IndexTree<T> join(IndexTree<T> tree) {
    IndexTree<T> joinTree = this.clone();
    return join(joinTree, tree);
  }

  /**
   * Private and recursive method for join two trees. Given the first tree and the second tree will
   * look first; If the second tree contains a root. If yes will continue looking if the root of the
   * second tree is contained in the first one. If not, will add to the first tree the root of the
   * second one. Then we can start to work with the left and right trees. For left. we set the root
   * of an auxiliary tree as the root of the very first left element of our second tree. and then we
   * will call recursively to our join method with the first tree and the left tree of our second
   * tree. And finally the same for the right as for the left.
   *
   * @param tree1 first tree to join.
   * @param tree2 second tree to join.
   * @return a tree containing all the nodes from tree1 and tree2. Following the rules of the AVL
   *     trees, that is: No repeated elements, ordered as BST...
   */
  private IndexTree<T> join(IndexTree<T> tree1, IndexTree<T> tree2) {
    IndexTreeNode<T> joinRoot = tree2.getRootNode();
    if (joinRoot != null) {
      if (!tree1.contains(joinRoot.getContent())) tree1.add(joinRoot.getContent());

      IndexTree<T> treeL = new IndexTree<T>();
      treeL.setRootNode(joinRoot.getLeft());
      tree1.join(tree1, treeL);

      IndexTree<T> treeR = new IndexTree<T>();
      treeR.setRootNode(joinRoot.getRight());
      tree1.join(tree1, treeR);
    }
    return tree1;
  }

  /**
   * Updates the Balance Factor of the Node.
   *
   * @param root the root
   * @return processRotations(node) index tree node
   */
  private IndexTreeNode<T> updateBalanceFactor(IndexTreeNode<T> root) {
    root.updateHeight();
    return computeRotations(root);
  }

  /**
   * This method decides if we have to perform a single or double and left or right rotation.
   *
   * @param root the root
   * @return single[Left / Right]Rotation(root)
   */
  private IndexTreeNode<T> computeRotations(IndexTreeNode<T> root) {
    if (root.getBalanceFactor() == -2) {
      if (root.getLeft().getBalanceFactor() <= 0) return singleLeftRotation(root);
      else return doubleLeftRotation(root);

    } else if (root.getBalanceFactor() == 2) {
      if (root.getRight().getBalanceFactor() >= 0) return singleRightRotation(root);
      else return doubleRightRotation(root);
    }
    return root;
  }

  /**
   * Performs a single left rotation to a given subtree.
   *
   * @param root the root
   * @return Balanced subtree.
   */
  private IndexTreeNode<T> singleLeftRotation(IndexTreeNode<T> root) {
    IndexTreeNode<T> aux = root.getLeft();
    root.setLeft(aux.getRight());
    aux.setRight(root);
    root = aux;

    root.updateHeight();
    return root;
  }

  /**
   * Performs a single right rotation to a given subtree.
   *
   * @param root the root
   * @return Balanced subtree.
   */
  private IndexTreeNode<T> singleRightRotation(IndexTreeNode<T> root) {
    IndexTreeNode<T> aux = root.getRight();
    root.setRight(aux.getLeft());
    aux.setLeft(root);
    root = aux;

    root.updateHeight();
    return root;
  }

  /**
   * Performs a double left rotation.
   *
   * @param root the root
   * @return balanced subtree
   */
  private IndexTreeNode<T> doubleLeftRotation(IndexTreeNode<T> root) {
    IndexTreeNode<T> aux = root.getLeft().getRight();

    root.getLeft().setRight(aux.getLeft());
    aux.setLeft(root.getLeft());

    root.setLeft(aux.getRight());
    aux.setRight(root);

    root = aux;

    root.updateHeight();
    return root;
  }

  /**
   * Performs a double right rotation.
   *
   * @param root the root
   * @return balanced subtree
   */
  private IndexTreeNode<T> doubleRightRotation(IndexTreeNode<T> root) {
    IndexTreeNode<T> aux = root.getRight().getLeft();

    root.getRight().setLeft(aux.getRight());
    aux.setRight(root.getRight());

    root.setRight(aux.getLeft());
    aux.setLeft(root);

    root = aux;

    root.updateHeight();
    return root;
  }

  /**
   * Get Height method. Returns the height of the tree without accessing to the node parameters. To
   * perform that it calls to the private and reflexive getHeight() method with the root of the tree
   * as a parameter.
   *
   * @return the height of the tree as an integer.
   * @important The method considers the height of a single leaf as 0.
   */
  public int getHeight() {
    return getHeight(this.getRootNode());
  }

  /**
   * Get Height [ PRIVATE AND REFLEXIVE ]. It returns the height of the tree without accessing to
   * the node parameters.
   *
   * @param root where you start to perform the algorithm
   * @return the height as an integer.
   * @important The method considers the height of a single leaf as 0.
   */
  private int getHeight(IndexTreeNode<T> root) {
    // If the tree is empty...
    if (root == null) return 0;

    // That check is to know whether a node is a leaf or not.
    // If one single leaf is counted as 1 remove two following lines.
    else if (root.getLeft() == null && root.getRight() == null) return 0;

    // Depending on the balance factor we can know which subtree has more
    // height, if the balance factor equals or less 0 we will take the left
    // subtree as the one with more height, the right one otherwise.
    if (root.getBalanceFactor() <= 0) return 1 + getHeight(root.getLeft());
    else return 1 + getHeight(root.getRight());
  }

  /**
   * If the root of a tree is null means that the tree is empty.
   *
   * @return whether a tree is empty or not.
   */
  public boolean isEmpty() {
    return (rootNode == null);
  }

  /**
   * Returns a pointer to an auxiliary tree containing all the elements as this tree.
   *
   * @return an auxiliary tree containing all the elements on the working tree.
   */
  public IndexTree<T> clone() {
    IndexTree<T> copy = new IndexTree<T>();
    return clone(copy, this.getRootNode());
  }

  /**
   * Private and recursive version for make a copy of the actual tree.
   *
   * @param tree that will be the auxiliary one.
   * @param root to start working to make the copy.
   * @return the aux tree.
   */
  private IndexTree<T> clone(IndexTree<T> tree, IndexTreeNode<T> root) {
    if (root != null) {
      tree.add(root.getContent());
      clone(tree, root.getLeft());
      clone(tree, root.getRight());
    }
    return tree;
  }

  /**
   * The current tree as a List shorted.
   *
   * @return the current tree as a list shorted with the default type comparator.
   */
  public List<T> toList() {
    List<T> toReturn = new ArrayList<T>();
    toReturn = toList(toReturn, this.getRootNode());
    return toReturn;
  }

  /**
   * Private and recursive method to get the tree as a List.
   *
   * @param list where the elements will be stored.
   * @param root of the working tree.
   * @return the list containing all the elements not shorted.
   */
  private List<T> toList(List<T> list, IndexTreeNode<T> root) {
    if (root != null) {
      list.add(root.getContent());
      toList(list, root.getLeft());
      toList(list, root.getRight());
    }
    return list;
  }

  /**
   * The current tree as a List shorted.
   *
   * @param comparator used to sort the list.
   * @return the current tree as a list sorted by the custom comparator.
   */
  public List<T> toList(Comparator<T> comparator) {
    List<T> toReturn = new ArrayList<T>();
    toReturn = toList(toReturn, this.getRootNode());
    Collections.sort(toReturn, comparator);
    return toReturn;
  }

  /**
   * Given a tree this method will give the number of nodes that contains.
   *
   * @return the number of nodes contained by the tree.
   */
  public int size() {
    return size(this.getRootNode());
  }

  /**
   * Given a tree and the root of it this method will return the number of nodes that contains.
   *
   * @param root of the tree.
   * @return the number of nodes contained by the tree.
   */
  private int size(IndexTreeNode<T> root) {
    if (root == null) return 0;
    return (1 + size(root.getLeft()) + size(root.getRight()));
  }

  /**
   * Returns an AVLTree containing the different elements from one tree and another.
   *
   * @param secondTree is the second AVLTree to get the different elements.
   * @return An AVLTree that contains all the elements that are not contained in the other tree. And
   *     works in both ways, elements that are in the first but not in the second and elements that
   *     are in the second but not in the first.
   */
  public IndexTree<T> difference(IndexTree<T> secondTree) {
    IndexTree<T> toReturn = new IndexTree<T>();
    for (T element : this.toList()) {
      if (!secondTree.contains(element)) toReturn.add(element);
    }
    for (T element : secondTree.toList()) {
      if (!this.contains(element)) toReturn.add(element);
    }
    return toReturn;
  }
}
