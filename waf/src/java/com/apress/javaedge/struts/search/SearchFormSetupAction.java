package com.apress.javaedge.struts.search;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *	Sets up any needed properties for the search form. Forwards user to the
 * 	@author  Jeff Linwood
 */
public class SearchFormSetupAction extends Action {

	/**
	 * Performs no work for the action
	 * 
	 */
    public ActionForward execute(ActionMapping mapping,
                                ActionForm     form,
                                HttpServletRequest request,
                                HttpServletResponse response){
        
       
        
        
        return (mapping.findForward("search.success"));
    }
}
