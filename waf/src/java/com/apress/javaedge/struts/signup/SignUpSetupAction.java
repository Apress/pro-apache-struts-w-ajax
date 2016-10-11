package com.apress.javaedge.struts.signup;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * Setup action that is used to pre-populate the sign-up screen with data.
 * @author jcarnell
 *
 *  @struts.action path="/signUpSetup"
 *                name="signUpForm"
 *                scope="request"
 *                validate="false"
 * @struts.action-forward name="signup.success" path="/WEB-INF/jsp/signUp.jsp"
 *
 */
public class SignUpSetupAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, 
                                 ActionForm     form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        return (mapping.findForward("signup.success"));
                         
    }

	
}
