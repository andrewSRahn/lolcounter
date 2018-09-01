package com.lolpick.lolcounter.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	private static Logger logger = null;
	
	public static Logger getLogger() {
		if(logger == null) {
			logger = LoggerFactory.getLogger(Log.class);
		}
		
		return logger;
	}
}
