package com.apress.javaedge.search;

import java.util.TimerTask;

/**
 * Run the content index generator.  This is a simple timer task
 * that just executes a method on another class.
 * 
 * @author Jeff Linwood
 *
 */
public class IndexTask extends TimerTask {

	/**
	 * Run the content index generator
	 * 
	 * @see IndexScheduler
	 */
	public void run() {
		IndexContent indexer = new IndexContent();
        indexer.createIndex();		
	}

}
