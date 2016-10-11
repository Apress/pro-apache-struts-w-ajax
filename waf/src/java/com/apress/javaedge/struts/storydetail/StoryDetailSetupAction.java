package com.apress.javaedge.struts.storydetail;


import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.IStoryManager;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  John Carnell
 */
public class StoryDetailSetupAction extends Action {
    
    
    /**
     *
     * Looks up a story detail information and sees what to do about the application.
     *
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 
	 *
     * @struts.action path="/storyDetailSetup"
     *                name="postStoryForm"
     *                scope="request"
     *                validate="false"
     * @struts.action-forward name="storydetail.success" path="/WEB-INF/jsp/storyDetail.jsp"
	 */
	public ActionForward execute(ActionMapping mapping,
                                    ActionForm     form,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        
        String storyId = request.getParameter("storyId");

        IStoryManager storyManager = StoryManagerBD.getStoryManagerBD();
        StoryVO storyVO = storyManager.retrieveStory(storyId);
            
        request.setAttribute("storyVO",storyVO);
        request.setAttribute("comments", storyVO.getComments());

        return (mapping.findForward("storydetail.success"));
    }
}
