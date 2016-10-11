package com.apress.javaedge.search;

/**
 * @author jlinwood
 *
 */
public class SearchConfiguration {

	public static String getIndexPath() {
		return "/tmp/search";	
	}
	
	public static long getIndexPeriod() {
		return 1000*60;	
	}
}
