/*
 * This source file is part of the big-table open source project.
 *
 * Copyright (c) 2019 willy and the big-table project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package io.thewilly.bigtable.index;

import io.thewilly.bigtable.BigTable;

/**
 * Instance of IndexEngine.java
 * 
 * @author 
 * @version 
 */
public interface IndexEngine {

	public static final IndexEngine DEFAULT_ENGINE = new DefaultIndexEngineImpl();

	/**
	 * Computes the hash function for the given value.
	 * 
	 * @param value to compute the hash
	 * @return
	 */
	public <K,V> boolean index(BigTable<K, V> table, V value);
}
