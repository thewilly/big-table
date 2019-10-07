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

import io.thewilly.bigtable.index.IndexEngine;
import io.thewilly.bigtable.search.IntersectionSearch;
import io.thewilly.bigtable.search.UnionSearch;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

/**
 * Instance of BigTableImpl.java
 * 
 * @author 
 * @version 
 */
final class ConcurrentBigTableImpl<K,V> implements BigTable<K, V> {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = -4192407345274521773L;

	/**
	 * Memory map representation to store data.
	 */
	private ConcurrentMap<K,Set<V>> _memoryMap = null;

	/**
	 * The index engine to compute indexes.
	 */
	private IndexEngine _indexEngine = null;

	/**
	 * Allocates a [] object and initializes it so that it represents
	 */
	protected ConcurrentBigTableImpl() {
		this._memoryMap = new ConcurrentHashMap<>();
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
		if( key == null || key == "")
			return new HashSet<>();

		Set<V> result = this._memoryMap.get(key);
		return result == null ? new HashSet<V>() : result;
	}

	@Override
	public Set<V> findIntersection( @SuppressWarnings("unchecked") K... keys ) {
		if(keys == null || keys.length == 0) return new HashSet<>();
		return new IntersectionSearch().find( this, keys );
	}
	
	@Override
	public Set<V> findUnion( @SuppressWarnings("unchecked") K... keys ) {
		if(keys == null || keys.length == 0) return new HashSet<>();
		return new UnionSearch().find( this, keys );
	}

	@Override
	public void clear() {
		this._memoryMap.clear();
	}
	
	@Override
	public Stream<Map.Entry<K,Set<V>>> stream() {
		return this._memoryMap.entrySet().stream();
	}

}
