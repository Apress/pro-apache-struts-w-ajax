package com.apress.javaedge.search;

/**
 * Schedules a content indexing process. The constructor takes
 * the number of milliseconds of delay between execution of
 * the timer task from the search configuration.
 * 
 * @author Jeff Linwood
 * 
 * 
 */

import java.util.Timer;

public class IndexScheduler {

	//jdk 1.3 and above timer class
    Timer timer;
	
	/**
	 * The constructor gets the delay for execution of the indexer task from
	 * the search configuration.  It waits a second to start the index task.
	 */
	
    public IndexScheduler(long time) {
        timer = new Timer();
        timer.schedule(new IndexTask(), 1000, time);
    }
}
