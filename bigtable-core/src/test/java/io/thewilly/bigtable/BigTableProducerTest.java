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
 * Instance of BigTableProducerTest.java
 * 
 * @author 
 * @version 
 */
public class BigTableProducerTest {
	
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
	
	private BigTable<String, String> customIndexTable = 
			new BigTableProducer<String, String>()
					.asParallel()
					.withIndexEngine( longTextIndexEngine )
					.build();
	
	private BigTable<String, String> noIndexTable = new BigTableProducer<String, String>().build();

	@Test
	public void producerTest() {
		assertNotNull( customIndexTable );
		assertNotNull( noIndexTable );
	}
	
	@Test
	public void indexLiteTest() {
		String[] code1 = {"1" ,"María canta alto"};
		String[] code2 = {"2" ,"Pepe canta bajo"};
		
		assertTrue( customIndexTable.insert( code1[1], code1[0] ));
		assertTrue( customIndexTable.insert( code2[1], code2[0] ));

		assertEquals(2, customIndexTable.findIntersection( "canta" ).size());
		assertEquals(0, customIndexTable.findIntersection( "canta", "mal" ).size());
		assertEquals(1, customIndexTable.findIntersection( "canta", "bajo" ).size());
		assertEquals(2, customIndexTable.findUnion( "canta", "bajo", "alto" ).size());
	}

}
