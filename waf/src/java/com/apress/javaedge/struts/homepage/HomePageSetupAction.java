package com.apress.javaedge.struts.homepage;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.story.StoryManagerBD;
import com.apress.javaedge.story.IStoryManager;

import org.apache.commons.logging.Log;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 *  Retrieves the top ten posting on JavaEdge.
 * @author  John Carnell
 * @struts:action path="/homePageSetup" scope="request" unknown="true" validate="false"
 */
public class HomePageSetupAction extends Action {
    
    /** The execute() method comes from the base Struts Action class.  We
     * override this method and put the logic to carry out the user's
     * request in the overridden method
     * @param mapping An ActionMapping class that will be used by the Action class to tell
     * the ActionServlet where to send the end-user.
     *
     * @param form The ActionForm class that will contain any data submitted
     * by the end-user via a form.
     * @param request A standard Servlet HttpServletRequest class.
     * @param response A standard Servlet HttpServletResponse class.
     * @return An ActionForward class that will be returned to the ActionServlet indicating where
     *         the user is to go next.
     *
     */
    public ActionForward execute(ActionMapping mapping,
                                    ActionForm     form,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {


        IStoryManager storyManagerBD = StoryManagerBD.getStoryManagerBD();
        Collection topStories = storyManagerBD.findTopStory();
        request.setAttribute("topStories", topStories);

        return (mapping.findForward("homepage.success"));
    }
}
