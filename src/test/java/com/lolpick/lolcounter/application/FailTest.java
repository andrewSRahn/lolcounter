package com.lolpick.lolcounter.application;

import static org.junit.Assert.*;

import org.junit.Test;

public class FailTest {

	@Test
	public void testRetry() {
		while(Fail.remains("src/main/resources/fails.txt")) 
			Fail.retry("src/main/resources/fails.txt");
	}
//
//	@Test
//	public void testRemains() {
//		assertFalse(Fail.remains("src/main/resources/fails.txt"));
//		assertTrue(Fail.remains("src/main/resources/empty.txt"));
//		assertFalse(Fail.remains("src/main/resources/append.txt"));
//	}
//	
//	@Test
//	public void testRemove() {
//		Fail.remove("src/main/resources/append.txt", 2);
//		assertEquals(Fail.count("src/main/resources/append.txt"), 1);
//	}
//	
//	@Test
//	public void testWrite() {
//		
//	}
//	
//	@Test
//	public void testCount() {
//		assertEquals(Fail.count("src/main/resources/empty.txt"), 0);
//		assertEquals(Fail.count("src/main/resources/append.txt"), 2);
//		assertEquals(Fail.count("src/main/resources/fails.txt"), 117);
//	}
	
}
