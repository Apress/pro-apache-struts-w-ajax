package com.apress.javaedge.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


/**
 * Simple ExceptionHandler that send an Email every time a Struts Action class throws an
 * ApplicationException.
 */
public class MailExceptionHandler extends ExceptionHandler{

    private static Log log = ServiceLocator.getInstance().getLog(MailExceptionHandler.class);

    public ActionForward execute(Exception e,
                                 ExceptionConfig ex,
                                 ActionMapping mapping,
                                 ActionForm    form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException{


        ActionForward forward = super.execute(e, ex, mapping, form, request, response);

        Properties props = new Properties();

        //Getting the name of the email server.
        props.put("mail.smtp.host", "netchange.us");
        props.put("mail.from", "JavaEdgeApplication");

        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);

		// write the exception details to the log file
		log.error("An ApplicationException has occured", e);

        Message msg = new MimeMessage(session);

        try{
          msg.setFrom();

          //Setting who is suppose to recieve the email
          InternetAddress to = new InternetAddress("john.carnell@netchange.us");

          //Setting the important text
          msg.setRecipient(MimeMessage.RecipientType.TO, to);
          msg.setSubject("Error message occurred in Action:" + mapping.getName());
          msg.setText("An error occurred while trying to invoke execute() on Action:" + mapping.getName() +
                      ". Error is: " + e.getMessage());
          Transport.send(msg);
          log.debug("E-Mail sent to:" + to);
        }
        catch(Exception exception){
			log.error("=====================================================================================");
			log.error("An error has occurred in the MailExceptionHandler while trying to process Action: "
                       + mapping.getName());
			log.error("Exception raised is : " + exception.getMessage());
			log.error("Original Exception: " + e.getMessage());
			log.error("=====================================================================================");

        }

        return forward;
    }

}
