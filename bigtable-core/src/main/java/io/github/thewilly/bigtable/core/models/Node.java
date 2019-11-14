package io.github.thewilly.bigtable.core.models;

import java.io.Serializable;

/**
 * The type Node.
 *
 * @param <T> the type parameter
 */
public class Node<T extends Comparable<T>> implements Serializable {

    /** The content. */
    private T content;

    /**
     * Instantiates a new node.
     *
     * @param content the content
     */
    public Node(T content) {
        setContent(content);
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

}
