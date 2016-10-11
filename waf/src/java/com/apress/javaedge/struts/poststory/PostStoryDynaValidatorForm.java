package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.common.VulgarityFilter;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import javax.servlet.http.HttpServletRequest;

/**
 *   This implementation of the PostStoryForm class uses Dynamic Forms to define the attributes for the class.
 *   This saves a lot of extraneous typing of the get()/set() attributes of Struts.  It also uses the validator
 *   framework to elminiate the need to write and call rules for length checking and rules checking.
 *
 *   @author jcarnell
 */
public class PostStoryDynaValidatorForm extends DynaValidatorForm {
    //Checks to make sure the field being checked does not violate our vulgarity list
    private void checkForVulgarities(String fieldName, String fieldKey, String value, ActionErrors errors){
       VulgarityFilter filter = VulgarityFilter.getInstance();
        
        if (filter.isOffensive(value)){
          ActionMessage error = new ActionMessage("error.poststory.field.vulgar", fieldName);
          errors.add(fieldKey, error);
        }
    }
    
    
    /** 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

       /*  The first action take in the validate() method is to call the parent validate() method.
        *   This will cause the validation rules defined in the validate.xml file to be fired off.
        */ 
       ActionErrors errors = super.validate(mapping, request); 
       
       /*
        *   Pulling each of the values out of the via the get() and set() methods on the DynaActionForm class.
        *   However, it is the responsiblity of the developer to do proper type casting and ensure that the values
        *   are not NULL.
       */
       
       checkForVulgarities("Story Title", "error.storytitle.vulgarity", (String) super.get("storyTitle"), errors);
       checkForVulgarities("Story Intro", "error.storyintro.vulgarity", (String) super.get("storyIntro"), errors);
       checkForVulgarities("Story Body",  "error.storybody.vulgarity",  (String) super.get("storyBody"), errors);
       
       
       return errors;
    }
}
