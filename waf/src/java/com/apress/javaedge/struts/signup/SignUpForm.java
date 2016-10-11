package com.apress.javaedge.struts.signup;

import com.apress.javaedge.member.MemberVO;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.Globals;

/**
 *
 * @author  John Carnell
 * @struts:form name="signUpForm" 
 */
public class SignUpForm extends ActionForm {
    private MemberVO memberVO = new MemberVO();
    private String confirmPassword = "";
    
    
    
    
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        
        return errors;
    }
    
    /**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 * @todo Need to re-factor out the deprecated method in this call.
	 */
	public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
	  // deprecated 1.1
      //ActionServlet servlet =  this.getServlet();
      //MessageResources messageResources = servlet.getResources();

	  // new for 1.2
        MessageResources messageResources =
            (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        
    }
    
    
    /**
     * Returns the confirmPassword.
     * @return String
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    /**
     * Returns the memberVO.
     * @return MemberVO
     */
    public MemberVO getMemberVO() {
        return memberVO;
    }
    
    /**
     * Sets the confirmPassword.
     * @param confirmPassword The confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    /**
     * Sets the memberVO.
     * @param memberVO The memberVO to set
     */
    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }
    
}
