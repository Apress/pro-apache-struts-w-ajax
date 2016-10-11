package com.apress.javaedge.struts.poststory;

import com.apress.javaedge.common.VulgarityFilter;
import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is an alternate version of the PostStoryForm that uses
 * an internal Map to store properties rather then individual attributes.
 */
public class PostStoryMapForm extends ActionForm{
    private HashMap attributeMap = new HashMap();

    /**
     * Wrapper around the hash map.  I can change the underlying Map object without
     * breaking the rest of the code.
     *
     * @returns The map containg the attributes.
     */
    private Map getMap(){
       return attributeMap;
    }

    /**
     * Sets a form bean attribute in the form's internal Map.
     * @param attributeKey Name of the attribute.
     * @param attributeValue Attribute value to be stored.  This must be a Java object and not a primitive.
     */
    public void setAttribute(String attributeKey, Object attributeValue){
      getMap().put(attributeKey, attributeValue);
    }

    /**
     * Returns the individual attribute.
     * @param attributeKey Name of the attribute being looked up
     * @return An the object.  If the object is null it returns an empty "".
     */

    public Object getAttribute(String attributeKey){

        Object holder = getMap().get(attributeKey);

        if (holder==null) return "";

        return holder;
    }

    //Checks to make sure field being checked is not null
    private void checkForEmpty(String fieldName, String fieldKey, String value, ActionErrors errors){

        System.out.println("Field Name "+ fieldName + " is "+ value.trim().length());
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

    /**
     * Standard Struts validate method.  The key difference here is that the Map object is being accessed
     * to retrieve the data.
     *
     * @param mapping  Struts ActionMapping class
     * @param request  HttpServletRequest
     * @return An ActionErrors class containing any errors raised during validation.
     */

    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {
       ActionErrors errors = new ActionErrors();

       checkForEmpty("Story Title", "error.storytitle.empty", (String)getAttribute("storyTitle"),errors);
       checkForEmpty("Story Intro", "error.storyintro.empty", (String)getAttribute("storyIntro"), errors);
       checkForEmpty("Story Body",  "error.storybody.empty", (String) getAttribute("storyBody"), errors);

       checkForVulgarities("Story Title", "error.storytitle.vulgarity", (String) getAttribute("storyTitle"), errors);
       checkForVulgarities("Story Intro", "error.storyintro.vulgarity", (String) getAttribute("storyIntro"), errors);
       checkForVulgarities("Story Body",  "error.storybody.vulgarity", (String) getAttribute("storyBody"), errors);

       checkForLength("Story Title", "error.storytitle.length", (String) getAttribute("storyTitle"), 100, errors);
       checkForLength("Story Intro", "error.storyintro.length", (String) getAttribute("storyIntro"), 2048, errors);
       checkForLength("Story Body", "error.storybody.length",   (String) getAttribute("storyBody"),  10000, errors);

       return errors;
    }

    /**
     * Standard Struts reset() method.
     * @param mapping  Struts ActionMapping class
     * @param request  HttpServletRequest
     */
	public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
      ActionServlet servlet =  this.getServlet();
      MessageResources messageResources = servlet.getInternal();

      setAttribute("storyTitle", messageResources.getMessage("javaedge.poststory.title.instructions"));
      setAttribute("storyIntro", messageResources.getMessage("javaedge.poststory.intro.instructions"));
      setAttribute("storyBody" , messageResources.getMessage("javaedge.poststory.body.instructions"));
    }

}
