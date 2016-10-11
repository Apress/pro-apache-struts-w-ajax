package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.IStoryManager;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Vector;

/**
 * @author jcarnell
 *
 * Example of a PostStory class using a DynaActionForm to retrieve data.
 * 
 * 
 */
public class PostStoryValidator extends Action {
    
    /**
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,
                                ActionForm     form,
                                HttpServletRequest request,
                                HttpServletResponse response){
        
        if (this.isCancelled(request)){
            System.out.println("*****The user pressed cancelled!!!");
            return (mapping.findForward("poststory.success"));
        }
        
        DynaActionForm postStoryForm = (DynaActionForm) form;
        
        HttpSession session = request.getSession();
        
        MemberVO      memberVO      = (MemberVO) session.getAttribute("memberVO");

        StoryVO storyVO = new StoryVO();
            
        storyVO.setStoryIntro((String)postStoryForm.get("storyIntro"));
        storyVO.setStoryTitle((String)postStoryForm.get("storyTitle"));
        storyVO.setStoryBody((String)postStoryForm.get("storyBody"));
        storyVO.setStoryAuthor(memberVO);
        storyVO.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
        storyVO.setComments(new Vector());
            
        IStoryManager storyManager = StoryManagerBD.getStoryManagerBD();
        storyManager.addStory(storyVO);

        return (mapping.findForward("poststory.success"));
    }
}



