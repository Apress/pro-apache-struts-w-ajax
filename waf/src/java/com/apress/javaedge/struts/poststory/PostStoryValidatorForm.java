/*
 * PostStoryForm.java
 *
 * Created on September 15, 2002, 9:04 PM
 */

package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.common.VulgarityFilter;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.Globals;

/**
 *
 * @author  John Carnell
 * @struts.form name="postStoryValidatorForm"
 */
public class PostStoryValidatorForm extends ValidatorForm {
    
    
    String storyTitle = "";
    String storyIntro = "";
    String storyBody  = "";
    
    
    //Checks to make sure the field being checked does not violate our vulgarity list
    private void checkForVulgarities(String fieldName, String fieldKey, String value, ActionErrors errors){
       VulgarityFilter filter = VulgarityFilter.getInstance();
        
        if (filter.isOffensive(value)){
          ActionMessage error = new ActionMessage("error.poststory.field.vulgar", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
       ActionErrors errors = super.validate(mapping,request);
       
       System.out.println("!!!!!I am in the PostStoryValidate!!!!!!");
       checkForVulgarities("Story Title", "error.storytitle.vulgarity", getStoryTitle(), errors);
       checkForVulgarities("Story Intro", "error.storyintro.vulgarity", getStoryIntro(), errors);
       checkForVulgarities("Story Body",  "error.storybody.vulgarity", getStoryBody(), errors);
       
       return errors;
    }
    
    /**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 * @todo Need to deal with the deprecated method call in here.
	 */
	public void reset(ActionMapping mapping,
                      HttpServletRequest request) {  
	  // deprecated 1.1
      //ActionServlet servlet =  this.getServlet();
      //MessageResources messageResources = servlet.getResources();

	  // new for 1.2
        MessageResources messageResources =
            (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);

      storyTitle = messageResources.getMessage("javaedge.poststory.title.instructions");
      storyIntro = messageResources.getMessage("javaedge.poststory.intro.instructions");
      storyBody  = messageResources.getMessage("javaedge.poststory.body.instructions");  
    }
    
    /** Getter for property storyTitle.
     * @return Value of property storyTitle.
     */
    public java.lang.String getStoryTitle() {
        return storyTitle;
    }
    
    /** Setter for property storyTitle.
     * @param storyTitle New value of property storyTitle.
     * @struts.validator type="required"
     *                   msgkey="error.poststory.field.null"
     *                   
     * @struts.validator type="maxlength"
     *                   msgkey="error.poststory.field.length"
     * 					 arg1value="${var:maxlength}"
     * 
     *
     * @struts.validator type="vulgaritychecker"
     * 					 msgkey="error.vulgarity"
     * 
     * @struts.validator-var name="maxlength" value="100"
     * @struts.validator-var name="vulgarities" value="dummy,stupid,ninny"
     */
    public void setStoryTitle(java.lang.String storyTitle) {
        this.storyTitle = storyTitle;
    }
    
    /** Getter for property storyIntro.
     * @return Value of property storyIntro.
     * 
     */
    public java.lang.String getStoryIntro() {
        return storyIntro;
    }
    
    /** Setter for property storyIntro.
     * @param storyIntro New value of property storyIntro.
     * @struts.validator type="required"
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
     * @struts.validator type="required" 	
     * 
     */
    public void setStoryBody(java.lang.String storyBody) {
        this.storyBody = storyBody;
    }
}
