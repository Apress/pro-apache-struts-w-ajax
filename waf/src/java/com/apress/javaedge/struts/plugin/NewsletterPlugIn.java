package com.apress.javaedge.struts.plugin;

import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;

import com.apress.javaedge.common.ServiceLocator;

public class NewsletterPlugIn implements PlugIn {

	/**
	 * Commons Log Interface for this class
	 */
	private static Log log = ServiceLocator.getInstance().getLog(NewsletterPlugIn.class);
	
	/**
	 * Timer used to maintain send schedule
	 */
	private Timer timer = null;
	
	/**
	 * Teardown the plugin
	 */
	public void destroy() {
		log.info("NewsletterPlugIn.destroy() called");
	}
	
	/**
	 * Factor that is applied to the interval
	 * figure in order to modify the timer
	 * task to work in minutes
	 */
	private long intervalFactor = 1000 * 60;
	
	/**
	 * Interval for sending - default is every 72 hours
	 */
	private long interval = (60 * 72);
	
	/**
	 * SMTP Server to use for sending mails
	 * default is localhost
	 */
	private String smtpServer = "localhost";
	
	/**
	 * E-Mail address to use in the from field
	 * 
	 */
	private String fromAddress = "javaedge@apress.com";
	
	/**
	 * Startup the plugin
	 */
	public void init(ActionServlet servlet, ModuleConfig config)
		throws ServletException {
		log.info("NewsletterPlugIn.init() called");
		
		loadConfigData(config);
		startTimer();
	}
	
	/**
	 * Find the configuration section for this plug-in
	 * and load the parameters into the current instance
	 * @param config Configuration data from the Struts configuration file.
	 */
	private void loadConfigData(ModuleConfig config) {
		
		PlugInConfig[] pluginConfigs = config.findPlugInConfigs();
		
		for(int x = 0; x < pluginConfigs.length; x++) {
			if(pluginConfigs[x].getClassName().equals(this.getClass().getName())) {
				log.debug("Found Plug-In Configuration");
				
				Map props = pluginConfigs[x].getProperties();
				
				// load in the interval property
				if(props.containsKey("interval")) {
					try {
						interval = Long.parseLong(props.get("interval").toString());
						log.debug("Interval set to: " + interval);
					} catch(Exception ignored) {
						log.debug("Specified Interval was not a valid log value");
					}
				}
				
				// load the smtp server property
				if(props.containsKey("smtp.server")) {
					smtpServer = props.get("smtp.server").toString();
					log.debug("smtpServer set to: " + smtpServer);
				}
				
				// load the from address property
				if(props.containsKey("fromAddress")) {
					fromAddress = props.get("fromAddress").toString();
					log.debug("fromAddress set to: " + fromAddress);
				}
			}
		}
	}
	
	private void startTimer() {
		timer = new Timer();
		
		long timerInterval = (interval * intervalFactor);
		
		timer.schedule(new NewsletterTask(smtpServer, fromAddress), timerInterval, timerInterval);
	}

}
