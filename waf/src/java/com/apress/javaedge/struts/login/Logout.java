package com.apress.javaedge.struts.login;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.apress.javaedge.member.dao.MemberDAO;
import com.apress.javaedge.member.MemberVO;

/**
 * 
 * This class invalidates the user's session and then redirects the 
 * user to the homePageSetup action.  This will force them to become
 * am anonymous user. 
 * @author jcarnell
 *
 *----------------XDoclet Tags--------------------
 * @struts:action path="/logout" 
 *                input="/execute/homePageSetup"
 *                scope="request"
 * @struts:action-forward name="logout.success" path="/execute/homePageSetup"
 *----------------XDoclet Tags--------------------
 */
public class Logout extends Action {
	
	public ActionForward execute(ActionMapping mapping,
								   ActionForm     form,
								   HttpServletRequest request,
								   HttpServletResponse response){
          HttpSession session = request.getSession();


          //For our example we just pull the anonymous use.  This is not the world's cleanest solution (but it is pretty simple), 
          //but it does effectively "logout" the person out of the JavaEdge application.

          MemberDAO memberDAO = new MemberDAO();
          MemberVO memberVO   = (MemberVO) memberDAO.findByPK("1");
          session.setAttribute("memberVO", memberVO);

	  return (mapping.findForward("logout.success"));							   
	}

}
