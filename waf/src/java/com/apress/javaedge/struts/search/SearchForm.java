package com.apress.javaedge.struts.search;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.apache.struts.action.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.Globals;
/**
 * @author Jeff Linwood
 * @struts:form name="searchForm" 
 *
 * The form class for the Search page
 */
public class SearchForm extends ActionForm {

	protected String query = "";

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        return errors;
    }
    
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
	  // deprecated 1.1
      //ActionServlet servlet =  this.getServlet();
      //MessageResources messageResources = servlet.getResources();

	  // new for 1.2
        MessageResources messageResources =
            (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
    }
    
    /** Getter for property query.
     * @return Value of property query.
     */
    public String getQuery() {
        return query;
    }
    
    /** Setter for property query.
     * @param query New value of property query.
     */
    public void setQuery(String query) {
        this.query = query;
    }    

}
