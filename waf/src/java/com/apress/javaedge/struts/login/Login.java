package com.apress.javaedge.struts.login;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author jcarnell
 *
 *----------------XDoclet Tags--------------------
 * @struts:action path="/login" 
 *                input="/execute/homePageSetup"
 *                name="loginForm"
 *                scope="request"
 *                validate="true"
 * @struts:action-forward name="login.success" path="/execute/homePageSetup"
 *----------------XDoclet Tags--------------------
 */
public class Login extends Action {
	
	public ActionForward execute(ActionMapping mapping,
								   ActionForm     form,
								   HttpServletRequest request,
								   HttpServletResponse response){
	  return (mapping.findForward("login.success"));							   
	}

}
