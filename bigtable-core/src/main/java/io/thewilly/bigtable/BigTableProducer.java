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

/**
 * Instance of BigTableProducer.java
 * 
 * @author 
 * @version 
 */
public final class BigTableProducer<K,V> {
	
	private BigTable<K,V> _table = null;
	
	public BigTableProducer<K,V> withIndexEngine(IndexEngine indexEngine) {
		if (_table == null)
			_table = new BigTableImpl<>();
		
		_table.setIndexEngine( indexEngine );
		return this;
	}
	
	public BigTable<K,V> build() {
		BigTable<K,V> aux = this._table;
		this._table = new BigTableImpl<>();
		return aux;
		
	}

}
