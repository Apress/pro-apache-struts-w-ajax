/*
 * Created on 27-Nov-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.apress.javaedge.struts.plugin;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.member.MemberManagerBD;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.IStoryManager;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;

/**
 * @author robh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NewsletterManager {

	private static Log log = ServiceLocator.getInstance().getLog(NewsletterManager.class);
	
	private String _smtpServer = "";
	private String _fromAddress = "";
	
	public NewsletterManager(String smtpServer, String fromAddress) {
		_smtpServer = smtpServer;
		_fromAddress = fromAddress;
	}
	
	public void sendNewsletter() throws ApplicationException {
		
		String mailContent = getNewsletterContent();
		
		Session mailSession = getMailSession();
		
		Collection recipients = loadRecipients();
		
		Message msg = new MimeMessage(mailSession);
		
		try {
			// from address
			Address fromAddress = new InternetAddress(_fromAddress);
			
			// subject line
			msg.setSubject("JavaEdge Newsletter");
			
			// body content
			msg.setText(mailContent);
			
			// recpient addresses
			Iterator iter = recipients.iterator();
			while(iter.hasNext()) {
				MemberVO member = (MemberVO)iter.next();
				
				if(member.getEmail().length() > 0) {
					Address bccAddress = new InternetAddress(member.getEmail());
					msg.addRecipient(Message.RecipientType.BCC, bccAddress);
				}
			}
			
			// send
			Transport.send(msg);
		} catch (AddressException e) {
			log.error("AddressException in NewsletterManager", e);
			throw new ApplicationException("AddressException in NewsletterManager", e);
		} catch (MessagingException e) {
			log.error("MessagingException in NewsletterManager", e);
			throw new ApplicationException("MessagingException in NewsletterManager", e);
		}
		
	}
	
	private String getNewsletterContent() {
		
		// load the top stories
		IStoryManager manager = StoryManagerBD.getStoryManagerBD();
		Collection stories = manager.findTopStory();
		
		// now build the content
		StringBuffer buffer = new StringBuffer();
		
		// header
		buffer.append("Dear Member,\n\n")
			.append("Here are the top stories from the JavaEdge web site:\n\n");
		
		// body
		Iterator iter = stories.iterator();
		
		while(iter.hasNext()) {
			StoryVO story = (StoryVO)iter.next();
			
			buffer.append("***************************************************")
				.append(story.getStoryTitle())
				.append("\n")
				.append(story.getStoryIntro())
				.append("\n")
				.append("<http://localhost:8080/JavaEdge/>")
				.append("\n");
		}
		
		// footer
		buffer.append("***************************************************");
		
		return buffer.toString();
	}
	
	private Session getMailSession() {
		
		// set properties
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", _smtpServer);
		
		return Session.getDefaultInstance(mailProps);
			
	}
	
	private Collection loadRecipients() throws ApplicationException {
		MemberManagerBD manager = new MemberManagerBD();
		Collection result = null;
	
		result = manager.getAll();
				
		return result;
	}
}
