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
import java.util.Random;

import org.junit.Test;

import io.thewilly.bigtable.index.IndexEngine;

/**
 * Instance of LoadTest.java
 * 
 * @author 
 * @version 
 */
public class LoadTest {
	
private IndexEngine longTextIndexEngine = new IndexEngine() {
		
		@SuppressWarnings("unchecked")
		@Override
		public <K, V> boolean index( BigTable<K, V> table, K key, V value ) {
			
			String[] keys = key.toString().split( " " );
			
			for(String ikey : keys) {
				ikey = ikey.toLowerCase();
				
				if (ikey.length() >= 3) {

					if (table.getMemoryMap().containsKey(ikey)) {
						table.getMemoryMap().get(ikey).add(value);
						//System.err.println( "Added value [" +value+ "] to key [" + ikey +"]"  );
					} else {
						table.getMemoryMap().put((K) ikey, new HashSet<V>());
						table.getMemoryMap().get(ikey).add(value);
						//System.err.println( "Added key [" +ikey+ "] and value [" + value +"]"  );
					}
				}
			}
			
			return true;
		}
	};
	
	BigTable<String, String> bt = 
			new BigTableProducer<String, String>()
			.withIndexEngine( longTextIndexEngine )
			.build();

	@Test
	public void warmUpLongTextIndex() {
		String[] words = {"Pepe", "María", "fiebre", "dolor", "tiene", "barriga", "estómago", "avión"};
		
		Random rnd = new Random();
		int wordindex = 0;
		String phrase = "";
		
		long initTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++) {
			phrase = "";
			for(int w = 0; w < 20; w++) {
				wordindex = rnd.nextInt( words.length );
				phrase = phrase + words[wordindex] + " ";
			}
			bt.insert( phrase, Integer.toString( i ) + "@"+phrase );
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(endTime - initTime);
		
		assertTrue( endTime - initTime < 20000 );
	}
}
