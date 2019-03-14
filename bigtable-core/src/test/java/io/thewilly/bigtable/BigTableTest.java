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

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import io.thewilly.bigtable.index.IndexEngine;

/**
 * Instance of BigTableTest.java
 * 
 * @author 
 * @version 
 */
public class BigTableTest {
	
	private IndexEngine longTextIndexEngine = new IndexEngine() {
		
		@SuppressWarnings("unchecked")
		@Override
		public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
			
			String[] keys = key.toString().split( " " );
			
			for(String ikey : keys) {
				if (ikey.length() >= 3) {

					if (table.getMemoryMap().containsKey(ikey)) {
						table.getMemoryMap().get(ikey).add(value);
						System.err.println( "Added value [" +value+ "] to key [" + ikey +"]"  );
					} else {
						table.getMemoryMap().put((K) ikey, new HashSet<V>());
						table.getMemoryMap().get(ikey).add(value);
						System.err.println( "Added key [" +ikey+ "] and value [" + value +"]"  );
					}
				}
			}
			
			return true;
		}
	};
	
	private BigTable<String, String> database = 
			new BigTableProducer<String, String>()
			.withIndexEngine( longTextIndexEngine )
			.build();

	@Test
	public void producerTest() {
		assertNotNull( database );
	}
	
	@Test
	public void indexTest() {
		String[] code1 = {"1" ,"María canta alto"};
		String[] code2 = {"2" ,"Pepe canta bajo"};
		
		assertTrue( database.insert( code1[1], code1[0] ));
		assertTrue( database.insert( code2[1], code2[0] ));
		
		assertEquals(2, database.findIntersection( "canta" ).size());
		assertEquals(1, database.findIntersection( "canta", "bajo" ).size());
		assertEquals(2, database.findUnion( "canta", "bajo", "alto" ).size());
	}

}
