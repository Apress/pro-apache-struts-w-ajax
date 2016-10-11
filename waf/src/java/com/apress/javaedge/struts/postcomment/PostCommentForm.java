
package com.apress.javaedge.struts.postcomment;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.IStoryManager;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Struts form for holding data submitted by the end user.
 *  
 *  @author  John Carnell
 *  @struts:form name="postCommentForm" 
 */
public class PostCommentForm extends ActionForm {
    StoryCommentVO storyCommentVO = new StoryCommentVO();
    StoryVO        storyVO = new StoryVO();

    /**
     * @todo Need to do some validation on comments in here.
     *
     * @param mapping
     * @param request
     * @return
     */
    public ActionErrors validate(ActionMapping mapping,
    HttpServletRequest request) {


        ActionErrors errors = new ActionErrors();
        
        
        return errors;
    }
    
    /**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 * 
	 */
	public void reset(ActionMapping mapping,
    HttpServletRequest request) {
    
        
        HttpSession    session  = request.getSession();
        MemberVO       memberVO = (MemberVO) session.getAttribute("memberVO");
     
    	try{
    				 
    	  String storyId = request.getParameter("storyVO.storyId");
    	  IStoryManager storyManagerBD = StoryManagerBD.getStoryManagerBD();

    	  StoryVO storyVO = storyManagerBD.retrieveStory(storyId);

    	  setStoryVO(storyVO);
    	
          StoryCommentVO storyCommentVO = new StoryCommentVO();
          storyCommentVO.setCommentAuthor(memberVO);     
          setStoryCommentVO(storyCommentVO);
    	}
    	catch(ApplicationException e){}
        
    }
    
    
    /**
     * Returns the storyCommentVO.
     * @return StoryCommentVO
     */
    public StoryCommentVO getStoryCommentVO() {
        return storyCommentVO;
    }
    
    /**
     * Sets the storyCommentVO.
     * @param storyCommentVO The storyCommentVO to set
     */
    public void setStoryCommentVO(StoryCommentVO storyCommentVO) {
        this.storyCommentVO = storyCommentVO;
    }
    
    /**
     * Returns the storyVO.
     * @return StoryVO
     */
    public StoryVO getStoryVO() {
        return storyVO;
    }
    
    /**
     * Sets the storyVO.
     * @param storyVO The storyVO to set
     */
    public void setStoryVO(StoryVO storyVO) {
        this.storyVO = storyVO;
    }
    
}
