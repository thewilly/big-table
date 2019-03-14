/*
 * This source file is part of the big-table open source project.
 *
 * Copyright (c) 2019 willy and the big-table project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package io.thewilly.bigtable.search;

import java.util.Set;

import io.thewilly.bigtable.BigTable;

/**
 * Instance of Search.java
 * 
 * @author 
 * @version 
 */
public interface Search {

	/**
	 * Finds on the given table the given sets of keys.
	 * 
	 * @param table to execute the search.
	 * @param keys to look for
	 * @return the set that contains the found keys.
	 */
	public <K,V> Set<V> find(BigTable<K,V> table, @SuppressWarnings("unchecked") K... keys);
}
