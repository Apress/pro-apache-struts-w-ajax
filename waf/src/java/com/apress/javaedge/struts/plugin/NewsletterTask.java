package com.apress.javaedge.struts.plugin;

import java.util.TimerTask;

import org.apache.commons.logging.Log;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.ServiceLocator;

public class NewsletterTask extends TimerTask {

	private static Log log = ServiceLocator.getInstance().getLog(NewsletterTask.class);
	
	private NewsletterManager manager = null;
	
	public NewsletterTask(String smtpServer, String fromAddress) {
		manager = new NewsletterManager(smtpServer, fromAddress);
	}
	
	public void run() {
		log.info("Newsletter.run() started");
		
		try {
			manager.sendNewsletter();
		} catch(ApplicationException e) {
			log.error("Could not send newsletter", e);
		}
		
		log.debug("Newsletter.run() completed");
	}

}
