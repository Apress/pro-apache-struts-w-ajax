/*
 * PostStoryForm.java
 *
 * Created on September 15, 2002, 9:04 PM
 */

package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.common.VulgarityFilter;
import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.member.MemberVO;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Vector;
import java.lang.reflect.InvocationTargetException;
import org.apache.struts.Globals;

/**
 * Standard Struts class that collects data 
 * submitted by the end-user.
 * @author jcarnell
 * 
 * ----------XDoclet Tag----------------
 * @struts.form name="postStoryForm"
 * ------------------------------------- 
 */
public class PostStoryForm extends ActionForm {
    
    
    String storyTitle = "";
    String storyIntro = "";
    String storyBody  = "";
    
    //Checks to make sure field being checked is not null
    private void checkForEmpty(String fieldName, String fieldKey, 
		String value, ActionErrors errors){
        if (value.trim().length()==0){
          ActionMessage error = new 
			  ActionMessage("error.poststory.field.null", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    //Checks to make sure the field being checked does 
	// not violate our vulgarity list
    private void checkForVulgarities(String fieldName, String fieldKey, 
		String value, ActionErrors errors){
       VulgarityFilter filter = VulgarityFilter.getInstance();
        
        if (filter.isOffensive(value)){
          ActionMessage error = 
			  new ActionMessage("error.poststory.field.vulgar", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    //Checks to make sure the field in question 
	//does not exceed a maximum length
    private void checkForLength(String fieldName, 
		String fieldKey, String value, int maxLength, ActionErrors errors){
        if (value.trim().length()>maxLength){
          ActionMessage error = 
			  new ActionMessage("error.poststory.field.length", fieldName);
          errors.add(fieldKey, error);  
        }      
    }
    
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();

       checkForEmpty("Story Title", "error.storytitle.empty", 
		   getStoryTitle(),errors);
       checkForEmpty("Story Intro", "error.storyintro.empty", 
		   getStoryIntro(), errors);
       checkForEmpty("Story Body",  "error.storybody.empty", 
		   getStoryBody(), errors);
       
       checkForVulgarities("Story Title", "error.storytitle.vulgarity", 
		   getStoryTitle(), errors);
       checkForVulgarities("Story Intro", "error.storyintro.vulgarity", 
		   getStoryIntro(), errors);
       checkForVulgarities("Story Body",  "error.storybody.vulgarity", 
		   getStoryBody(), errors);
       
       checkForLength("Story Title", "error.storytitle.length", 
		   getStoryTitle(), 100, errors);
       checkForLength("Story Intro", "error.storyintro.length", 
		   getStoryIntro(), 2048, errors);
       checkForLength("Story Body", "error.storybody.length", 
		   getStoryBody(),  10000, errors);
       
       return errors;
    }
    
    /**
	 * @see org.apache.struts.action.ActionForm#reset
	 (org.apache.struts.action.ActionMapping, 
	 javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping,
                      HttpServletRequest request) {  
	  // deprecated 1.1
      //ActionServlet servlet =  this.getServlet();
      //MessageResources messageResources = servlet.getResources();

	  // new for 1.2
        MessageResources messageResources =
            (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);

	  storyTitle = messageResources.getMessage(
		  "javaedge.poststory.title.instructions");
      storyIntro = messageResources.getMessage(
		  "javaedge.poststory.intro.instructions");
      storyBody  = messageResources.getMessage(
		  "javaedge.poststory.body.instructions");  
    }
    
    /** Getter for property storyTitle.
     * @return Value of property storyTitle.
     */
    public java.lang.String getStoryTitle() {
        return storyTitle;
    }
    
    /** Setter for property storyTitle.
     * @param storyTitle New value of property storyTitle.
     */
    public void setStoryTitle(java.lang.String storyTitle) {
        this.storyTitle = storyTitle;
    }
    
    /** Getter for property storyIntro.
     * @return Value of property storyIntro.
     */
    public java.lang.String getStoryIntro() {
        return storyIntro;
    }
    
    /** Setter for property storyIntro.
     * @param storyIntro New value of property storyIntro.
     */
    public void setStoryIntro(java.lang.String storyIntro) {
        this.storyIntro = storyIntro;
    }
    
    /** Getter for property storyBody.
     * @return Value of property storyBody.
     */
    public java.lang.String getStoryBody() {
        return storyBody;
    }
    
    /** Setter for property storyBody.
     * @param storyBody New value of property storyBody.
     */
    public void setStoryBody(java.lang.String storyBody) {
        this.storyBody = storyBody;
    }


   /**
    * Builds a StoryVO object based on the data 
	* contained within the ActionForm
    * @param request HttpServletRequest.Used to build out info in the StoryVO
    * @return StoryVO containing data from the PostStoryForm class.
    * @throws ApplicationException
    */
    public StoryVO buildStoryVO(HttpServletRequest request) 
		throws ApplicationException{
        HttpSession session = request.getSession();

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        StoryVO storyVO = new StoryVO();

        /*Example how to use the BeanUtils class to populatea valueobject.*/
        try{
           BeanUtils.copyProperties(storyVO, this);
        }
        catch(IllegalAccessException e) {
           throw new ApplicationException("IllegalAccessException in PostStoryForm.buildStoryVO()",e);
        }
        catch(InvocationTargetException e){
           throw new ApplicationException("InvocationTargetException in PostStoryForm.buildStoryVO()",e);
        }

        storyVO.setStoryAuthor(memberVO);
        storyVO.setSubmissionDate(
			new java.sql.Date(System.currentTimeMillis()));
        storyVO.setComments(new Vector());

        return storyVO;
    }
}
