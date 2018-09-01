package com.lolpick.lolcounter.application;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	@Test
	public void test() {
		Logger logger = LoggerFactory.getLogger(LogTest.class);
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
	
}
