package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/**
 * The type Index tree node.
 *
 * @param <T> the type parameter
 */
public class IndexTreeNode<T extends Comparable<T>> implements Serializable {

  /** The right. */
  private IndexTreeNode<T> left, right;

  /** The height. */
  private int height;

  /** The content. */
  private T content;

  /**
   * Instantiates a new AVL node.
   *
   * @param content the content
   */
  public IndexTreeNode(T content) {
    this.content = content;
    left = null;
    right = null;
  }

  /**
   * Instantiates a new AVL node.
   *
   * @param content the content
   * @param left the left
   * @param right the right
   */
  public IndexTreeNode(T content, IndexTreeNode<T> left, IndexTreeNode<T> right) {
    this.content = content;
    setLeft(left);
    setRight(right);
  }

  /**
   * Gets the content.
   *
   * @return the content
   */
  public T getContent() {
    return this.content;
  }

  /**
   * Sets the content.
   *
   * @param content the new content
   */
  public void setContent(T content) {
    this.content = content;
  }

  /**
   * Gets the height.
   *
   * @return the height
   */
  public int getHeight() {
    updateHeight();
    return this.height;
  }

  /**
   * Sets the height.
   *
   * @param height the new height
   */
  public void setHeight(int height) {
    if (height < 0) throw new IllegalArgumentException("The height must be possitive");
    this.height = height;
  }

  /**
   * Gets the left.
   *
   * @return the left
   */
  public IndexTreeNode<T> getLeft() {
    return this.left;
  }

  /**
   * Gets the right.
   *
   * @return the right
   */
  public IndexTreeNode<T> getRight() {
    return this.right;
  }

  /**
   * Sets the left.
   *
   * @param left the new left
   */
  public void setLeft(IndexTreeNode<T> left) {
    this.left = left;
  }

  /**
   * Sets the right.
   *
   * @param right the new right
   */
  public void setRight(IndexTreeNode<T> right) {
    this.right = right;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return getContent().toString() + "(" + getBalanceFactor() + ")";
  }

  /** Prints the. */
  public void print() {
    System.out.println(this.toString());
  }

  /** Update height. */
  protected void updateHeight() {
    // If the node doesn't have children, its height is just 0
    if (getLeft() == null && getRight() == null) setHeight(0);

    // If it has a child on one side, its height is its child's height plus
    // one
    else if (getLeft() != null && getRight() == null) setHeight(1 + getLeft().getHeight());
    else if (getLeft() == null && getRight() != null) setHeight(1 + getRight().getHeight());

    // If it has one child on each side, its height will be the greatest of
    // its childrens's height plus one
    else if (getLeft().getHeight() > getRight().getHeight()) setHeight(1 + getLeft().getHeight());
    else setHeight(1 + getRight().getHeight());
  }

  /**
   * Gets the balance factor.
   *
   * @return the balance factor
   */
  public int getBalanceFactor() {
    return computeBF();
  }

  /**
   * Compute BF.
   *
   * @return the int
   */
  private int computeBF() {

    // If the node doesn't have children, its BF is just 0
    if (getLeft() == null && getRight() == null) return 0;
    // If it has a child on one side
    else if (getRight() == null) return -1 - getLeft().getHeight();
    else if (getLeft() == null && getRight() != null) return 1 + getRight().getHeight();
    // If it has one child on each side
    else return (getRight().getHeight() - getLeft().getHeight());
  }
}
