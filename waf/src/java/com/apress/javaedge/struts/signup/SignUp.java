package com.apress.javaedge.struts.signup;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberManagerBD;
import com.apress.javaedge.member.MemberVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Administrator
 *
 * Action class that deals with a new user submitting a request.
 */
public class SignUp extends Action {
    
    /**
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * @booknote Need to talk about in the patterns section about the use of ValueObjects in conjunction with
     *           ActionForms and how value objects can be used to simplify code examples.
     * @struts.action path="/signUp"
     *                input="/WEB-INF/jsp/signUp.jsp"
     *                name="signUpForm"
     *                scope="request"
     *                validate="true"
     * @struts.action-forward name="signup.success" path="/execute/homePageSetup"
     *
	 */
	public ActionForward execute(ActionMapping mapping, 
                                 ActionForm     form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

        SignUpForm signUpForm = (SignUpForm) form;
        
        MemberVO memberVO = signUpForm.getMemberVO();
        HttpSession session = request.getSession();
        
        session.setAttribute("memberVO", memberVO);

        MemberManagerBD memberManagerBD = new MemberManagerBD();
        memberManagerBD.addUser(memberVO);

        
        return (mapping.findForward("signup.success"));
    }
}
