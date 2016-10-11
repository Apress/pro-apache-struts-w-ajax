/*
 * PostStorySetupAction.java
 *
 * Created on September 15, 2002, 8:15 PM
 */

package com.apress.javaedge.struts.poststory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  John Carnell
 *
 * @struts.action path="/postStorySetup"
 *                name="postStoryForm"
 *                scope="request"
 *                validate="false"
 * @struts.action-forward name="poststory.success" path="/WEB-INF/jsp/postStory.jsp"
 *
 */
public class PostStorySetupAction extends Action {

    public ActionForward execute(ActionMapping mapping,
                                ActionForm     form,
                                HttpServletRequest request,
                                HttpServletResponse response){
        
        return (mapping.findForward("poststory.success"));
    }
}
