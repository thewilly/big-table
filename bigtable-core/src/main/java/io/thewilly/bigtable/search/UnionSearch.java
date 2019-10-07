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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import io.thewilly.bigtable.BigTable;

/**
 * Instance of UnionSearch.java
 * 
 * @author 
 * @version 
 */
public class UnionSearch implements Search {

	@Override
	public <K, V> Set<V> find( BigTable<K, V> table, @SuppressWarnings("unchecked") K... keys ) {

		return Arrays.stream(keys)
				.map( k -> table.find( k ))
				.reduce((a,b) -> Sets.union(a,b))
				.get();
	}

}
