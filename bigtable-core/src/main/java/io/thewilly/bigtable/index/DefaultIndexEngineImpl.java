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

import java.util.HashSet;

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
	public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
		if(table.getMemoryMap().containsKey( key )) {
			table.getMemoryMap().get( key ).add( value );
		} else {
			table.getMemoryMap().put( key, new HashSet<V>() );
			this.index(table, key, value);
		}
		return true;
	}

}
