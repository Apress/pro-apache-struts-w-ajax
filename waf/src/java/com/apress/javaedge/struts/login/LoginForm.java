package com.apress.javaedge.struts.login;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberManagerBD;
import com.apress.javaedge.member.MemberVO;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jcarnell
 *
 * LoginForm used to hold data retrieved submitted by the user.
 * 
 * @struts:form name="loginForm" 
 */
public class LoginForm extends ValidatorForm {
	
	private String userId = "";
	private String password ="";
	
	/**
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping,
								 HttpServletRequest request) {
	  ActionErrors errors = new ActionErrors();
	  
	  MemberManagerBD memberManagerBD = new MemberManagerBD();
	  MemberVO        memberVO        = null;
	  HttpSession     session         = request.getSession();
	  
	  try{
	    memberVO = memberManagerBD.authenticate(getUserId(), getPassword());
	  }
	  catch(ApplicationException e){}
	  
	  
	  if (memberVO==null){
	  	ActionError error = new ActionError("error.header.invalidlogin");
	  	errors.add("invalid.login", error);
	  }
	  else{
	    session.setAttribute("memberVO",memberVO);
	  }
	  
	  return errors;

	}
	
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping,
						 HttpServletRequest request) {
	  setUserId("");
	  setPassword("");
						 	
	}
	
	/**
	 * Returns the password.
	 * @return String
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the userId.
	 * @return String
	 * 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 *
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the userId.
	 * @param userId The userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
