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
 * Instance of DefaultIndexEngineImpl.java
 * 
 * Allways return false.
 * 
 * @author 
 * @version 
 */
public class DefaultIndexEngineImpl implements IndexEngine {

	/* (non-Javadoc)
	 * @see io.thewilly.bigtable.index.IndexEngine#index(io.thewilly.bigtable.BigTable, java.lang.Object)
	 */
	@Override
	public <K, V> boolean index( BigTable<K, V> table, V value ) {
		return false;
	}

}
