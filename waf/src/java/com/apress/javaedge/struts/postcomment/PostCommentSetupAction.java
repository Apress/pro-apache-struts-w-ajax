package com.apress.javaedge.struts.postcomment;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jcarnell
 * @struts:action path="/postCommentSetup"
 *                input="/WEB-INF/jsp/postComment.jsp"
 *                name="postCommentForm"
 *                scope="request"
 *                validate="false"
 * @struts:action-forward name="postcomment.success" path="/WEB-INF/postComment.jsp"
 */
public class PostCommentSetupAction extends Action {
    
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm     form,
                                    HttpServletRequest request,
                                    HttpServletResponse response){


        System.out.println("************I am in the postCommentSetupAction*************");
        return (mapping.findForward("postcomment.success"));
        
    }
    
    
}
