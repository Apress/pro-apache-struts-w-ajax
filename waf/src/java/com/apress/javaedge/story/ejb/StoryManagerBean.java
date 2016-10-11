package com.apress.javaedge.story.ejb;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.StoryManagerPOJOImpl;
import com.apress.javaedge.story.IStoryManager;
import com.apress.javaedge.story.dao.StoryDAO;
import com.apress.javaedge.struts.poststory.PostStoryForm;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * @author jcarnell
 *
 * EJB bean implementation for our StoryManager.
 */
public class StoryManagerBean implements SessionBean, IStoryManager {
    private SessionContext ctx;
    private StoryManagerPOJOImpl storyManager;

    public void setSessionContext(SessionContext sessionCtx) {
        this.ctx = sessionCtx;
        this.storyManager = new  StoryManagerPOJOImpl();
    }

    /**
     * Adds a story to the JavaEdge database.
     *
     * @param storyVO  Story to be added
     * @throws ApplicationException
     * @throws RemoteException
     */
    public void addStory(StoryVO storyVO) throws ApplicationException{
      this.storyManager.addStory(storyVO);
    }

    public Collection findTopStory() throws ApplicationException{
      return this.storyManager.findTopStory();
    }

    public StoryVO retrieveStory(String primaryKey){
        return this.storyManager.retrieveStory(primaryKey);
    }

    public void updateStory(StoryVO storyVO) throws ApplicationException{
        this.storyManager.updateStory(storyVO);
    }



    /**
     * Example of tier leakage and hardwiring.
     *
     * @param postStoryForm
     * @param memberVO
     * @throws ApplicationException
     * @throws RemoteException
     */
    public void addStory(PostStoryForm postStoryForm, MemberVO memberVO)
            throws ApplicationException, RemoteException {
        System.out.println("This is an example of Tier Leakage.  I am passing");
        System.out.println("passing an ActionForm class (postStoryForm) to    ");
        System.out.println("EJB.  This creates a tight dependency between the ");
        System.out.println("Struts framework and a piece of business logic. ");
    }


    public void ejbCreate() {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }
}
