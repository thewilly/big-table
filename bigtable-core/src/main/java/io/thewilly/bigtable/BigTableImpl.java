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

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import io.thewilly.bigtable.index.IndexEngine;
import io.thewilly.bigtable.search.IntersectionSearch;
import io.thewilly.bigtable.search.UnionSearch;

/**
 * Instance of BigTableImpl.java
 * 
 * @author 
 * @version 
 */
public final class BigTableImpl<K,V> implements BigTable<K, V> {
	
	/**
	 * Memory map representation to store data.
	 */
	private SortedMap<K,Set<V>> _memoryMap = null;
	
	/**
	 * The index engine to compute indexes.
	 */
	private IndexEngine _indexEngine = null;
	
	/**
	 * Allocates a [] object and initializes it so that it represents 
	 */
	protected BigTableImpl() {
		this._memoryMap = new TreeMap<K, Set<V>>();
		this._indexEngine = IndexEngine.DEFAULT_ENGINE;
	}
	
	@Override
	public Map<K,Set<V>> getMemoryMap() {
		return this._memoryMap;
	}

	@Override
	public boolean insert( K key, V value ) {
		return this._indexEngine.index( this, key, value );
	}

	@Override
	public void setIndexEngine( IndexEngine indexEngine ) {
		if( indexEngine == null )
			throw new IllegalArgumentException("No index engine provided.");
		
		this._indexEngine = indexEngine;
	}

	@Override
	public Set<V> find( K key ) {
		if( key == null )
			throw new IllegalArgumentException("No valid key provided");
		
		return this._memoryMap.get( key );
	}

	@Override
	public Set<V> findIntersection( @SuppressWarnings("unchecked") K... keys ) {
		return new IntersectionSearch().find( this, keys );
	}
	
	@Override
	public Set<V> findUnion( @SuppressWarnings("unchecked") K... keys ) {
		return new UnionSearch().find( this, keys );
	}

	@Override
	public void clear() {
		this._memoryMap.clear();
	}

}