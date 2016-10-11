package com.apress.javaedge.struts.search;

import com.apress.javaedge.search.IndexScheduler;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
//import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Jeff Linwood
 *
 *
 */
public class IndexerPlugIn implements PlugIn {
	
	protected IndexScheduler scheduler;

	//default is one hour	
	protected long time = 60*60*1000;


	/**
	 * Doesn't do anything in this implementation
	 */
	public void destroy() {
	}
	
	/**
	 * Fire off the IndexScheduler class
	 * 
	 */
	public void init(ActionServlet servlet, ModuleConfig config) {
		scheduler = new IndexScheduler(time);
	}
	

	/**
	 * Sets the time.
	 * @param time The time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
