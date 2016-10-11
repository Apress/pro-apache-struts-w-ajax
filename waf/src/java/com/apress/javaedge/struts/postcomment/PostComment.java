package com.apress.javaedge.struts.postcomment;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.IStoryManager;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @struts:action path="/postComment"
 *                input="/WEB-INF/jsp/postComment.jsp"
 *                name="postCommentForm"
 *                scope="request"
 *                validate="true"
 * @struts:action-forward name="postcommentsetup.success" path="/execute/homePageSetup"
 *
 */
public class PostComment extends Action {
	// Create Log4j category instance for logging
	   static private org.apache.log4j.Category log = org.apache.log4j.Category.getInstance(PostComment.class.getName());
	   
	    public ActionForward execute(ActionMapping mapping, 
                                 ActionForm     form,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        
        if (this.isCancelled(request)){
           System.out.println("*****The user pressed cancelled!!!");
           return (mapping.findForward("poststory.success"));
        }
                                     
        PostCommentForm postCommentForm = (PostCommentForm) form;
        HttpSession session = request.getSession();
        
        MemberVO      memberVO      = (MemberVO) session.getAttribute("memberVO");
        String        storyId       = (String) request.getParameter("storyVO.storyId");
        

        IStoryManager storyManagerBD = StoryManagerBD.getStoryManagerBD();
        StoryVO storyVO = storyManagerBD.retrieveStory(storyId);
          
        StoryCommentVO storyCommentVO = postCommentForm.getStoryCommentVO();
        storyCommentVO.setCommentAuthor(memberVO);
        storyCommentVO.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
        storyVO.getComments().add(storyCommentVO);
        storyManagerBD.updateStory(storyVO);

        return (mapping.findForward("postcomment.success"));
    }
}
