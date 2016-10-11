package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.story.IStoryManager;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jcarnell
 *
 * Struts Action class used to submit a story by the end user.
 * 
 *
 *----------------XDoclet Tags--------------------
 * @struts.action path="/postStory" 
 *                input="/WEB-INF/jsp/postStory.jsp"
 *                name="postStoryForm"
 *                scope="request"
 *                validate="true"
 * @struts.action-forward name="poststory.success" path="/execute/homePageSetup"
 * @struts.action-exception key="error.system"
 *                          type="com.apress.javaedge.common.ApplicationException"
 * 							path="/WEB-INF/jsp/systemError.jsp"
 *----------------XDoclet Tags--------------------
 */
 
public class PostStory extends Action {
    
    /**
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     *
	 */
	public ActionForward execute(ActionMapping mapping,
                                ActionForm     form,
                                HttpServletRequest request,
                                HttpServletResponse response){
        
        if (this.isCancelled(request)){
            return (mapping.findForward("poststory.success"));
        }
        
        PostStoryForm postStoryForm = (PostStoryForm) form;

        StoryVO storyVO = postStoryForm.buildStoryVO(request);

        IStoryManager storyManager = StoryManagerBD.getStoryManagerBD();
        storyManager.addStory(storyVO);
            

        return (mapping.findForward("poststory.success"));
    }
}



