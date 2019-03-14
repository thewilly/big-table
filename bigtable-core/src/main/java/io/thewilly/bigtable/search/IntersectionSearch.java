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

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import io.thewilly.bigtable.BigTable;

/**
 * Instance of IntersectionSearch.java
 * 
 * @author 
 * @version 
 */
public class IntersectionSearch implements Search {


	@Override
	public <K, V> Set<V> find( BigTable<K, V> table, @SuppressWarnings("unchecked") K... keys ) {
		Set<V> last = null;
		
		for(K key : keys) {
			
			last = (last!=null) ? 
					Sets.intersection(last, table.getMemoryMap().get( key )) 
					: table.getMemoryMap().get( key );
		}
		
		last = (last == null) ? new HashSet<V>() : last;
		
		return last;
	}

}
