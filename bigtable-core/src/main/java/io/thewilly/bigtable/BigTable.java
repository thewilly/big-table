/*
 * This source file is part of the big-table open source project.
 *
 * Copyright (c) 2019 willy and the big-table project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package io.thewilly.bigtable;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import io.thewilly.bigtable.index.IndexEngine;

/**
 * Instance of BigTable.java
 * 
 * @author
 * @version
 */
public interface BigTable<K, V> extends Serializable {
	
	/**
	 * Gets access to the memory map.
	 * 
	 * @return the memory map.
	 */
	public Map<K,Set<V>> getMemoryMap();

	/**
	 * Inserts the given value at the given key.
	 * 
	 * @param key to use as the index.
	 * @param value to store at the given index.
	 */
	public boolean insert( K key, V value );

	/**
	 * Sets the index engine to the desired one.
	 * 
	 * @param indexEngine to be set.
	 */
	public void setIndexEngine( IndexEngine indexEngine );
	
	/**
	 * Find the corresponding indexed set for the given key.
	 * 
	 * @param key to look for.
	 * @return the set that is indexed at the given key.
	 */
	public Set<V> find( K key );

	/**
	 * Finds the intersection between all the entries in the table that are
	 * indexed at one of the keys provided.
	 * 
	 * @param keys to look for in the table.
	 * @return find(key_1) INTERSECTION find(key_2) ...
	 */
	public Set<V> findIntersection( @SuppressWarnings("unchecked") K... keys );
	
	/**
	 * Finds the union between all the entries in the table that are
	 * indexed at one of the keys provided.
	 * 
	 * @param keys to look for in the table.
	 * @return find(key_1) INTERSECTION find(key_2) ...
	 */
	public Set<V> findUnion( @SuppressWarnings("unchecked") K... keys );

	/**
	 * Clears all the data in the table.
	 */
	public void clear();
	
	/**
	 * Returns the current table as a stream.
	 * 
	 * @return the current table as a stream.
	 */
	public Stream<Map.Entry<K,Set<V>>> stream();
}
