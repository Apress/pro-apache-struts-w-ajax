package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.common.VulgarityFilter;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.common.ApplicationException;
import org.apache.commons.beanutils.BeanUtils;
import java.util.Vector;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import org.apache.struts.Globals;
/**
 *   @author jcarnell
 *   This implementation of the PostStory class uses a Dynamic Forms to define the attributes for the class.
 *   This saves a lot of extraneous typing of the get()/set() attributes of Struts.
 *

 */
 
public class PostStoryDynaForm extends DynaActionForm {
    //Checks to make sure field being checked is not null
    private void checkForEmpty(String fieldName, String fieldKey, String value, ActionErrors errors){
        if (value.trim().length()==0){
          ActionMessage error = new ActionMessage("error.poststory.field.null", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    //Checks to make sure the field being checked does not violate our vulgarity list
    private void checkForVulgarities(String fieldName, String fieldKey, String value, ActionErrors errors){
       VulgarityFilter filter = VulgarityFilter.getInstance();
        
        if (filter.isOffensive(value)){
          ActionMessage error = new ActionMessage("error.poststory.field.vulgar", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    //Checks to make sure the field in question does not exceed a maximum length
    private void checkForLength(String fieldName, String fieldKey, String value, int maxLength, ActionErrors errors){
        if (value.trim().length()>maxLength){
          ActionMessage error = new ActionMessage("error.poststory.field.length", fieldName);
          errors.add(fieldKey, error);  
        }      
    }
    
    /*
     *   Standard Struts Validate Method  
     *
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();
       
       /*
        *   Pulling each of the values out of the via the get() and set() methods on the DynaActionForm class.
        *   However, it is the responsiblity of the developer to do proper type casting and ensure that the values
        *   are not NULL.
       */
       checkForEmpty("Story Title", "error.storytitle.empty", (String) super.get("storyTitle"),errors);
       checkForEmpty("Story Intro", "error.storyintro.empty", (String) super.get("storyIntro"), errors);
       checkForEmpty("Story Body",  "error.storybody.empty",  (String) super.get("storyBody"), errors);
       
       checkForVulgarities("Story Title", "error.storytitle.vulgarity", (String) super.get("storyTitle"), errors);
       checkForVulgarities("Story Intro", "error.storyintro.vulgarity", (String) super.get("storyIntro"), errors);
       checkForVulgarities("Story Body",  "error.storybody.vulgarity",  (String) super.get("storyBody"), errors);
       
       checkForLength("Story Title", "error.storytitle.length", (String) super.get("storyTitle") , 100, errors);
       checkForLength("Story Intro", "error.storyintro.length", (String) super.get("storyIntro"), 2048, errors);
       checkForLength("Story Body", "error.storybody.length",   (String) super.get("storyBody"),  10000, errors);
       
       return errors;
    }

    public StoryVO buildStoryVO(HttpServletRequest request) throws ApplicationException{
        HttpSession session = request.getSession();

        MemberVO      memberVO      = (MemberVO) session.getAttribute("memberVO");


        StoryVO storyVO = new StoryVO();


        /*Example how to use the BeanUtils class to populatea valueobject.*/
        try{
           BeanUtils.copyProperties(storyVO, this.getMap());
        }
        catch(IllegalAccessException e) {
           throw new ApplicationException("IllegalAccessException in PostStoryForm.buildStoryVO()",e);
        }
        catch(InvocationTargetException e){
           throw new ApplicationException("InvocationTargetException in PostStoryForm.buildStoryVO()",e);
        }

        storyVO.setStoryAuthor(memberVO);
        storyVO.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
        storyVO.setComments(new Vector());

        return storyVO;
    }
    
    /**
     *   Standard Struts reset() method.
     * 
     *    @todo Need to visit the servlet.getResources() method.  This method has been
     *          deprecated.
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {  
	  // deprecated 1.1
      //ActionServlet servlet =  this.getServlet();
      //MessageResources messageResources = servlet.getResources();

	  // new for 1.2
        MessageResources messageResources =
            (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);

      super.set("storyTitle",messageResources.getMessage("javaedge.poststory.title.instructions"));
      super.set("storyIntro", messageResources.getMessage("javaedge.poststory.intro.instructions"));
      super.set("storyBody", messageResources.getMessage("javaedge.poststory.body.instructions"));
    }
}
