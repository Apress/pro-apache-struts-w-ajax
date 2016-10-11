package com.apress.javaedge.story;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.story.dao.StoryDAO;

import java.util.Collection;

import org.apache.commons.logging.Log;

/**
 *  Plain Old Java Implementation of the Story Manager.
 */
public class StoryManagerPOJOImpl implements IStoryManager {
    StoryDAO storyDAO = new StoryDAO();
    
    private static Log log = ServiceLocator.getInstance().getLog(StoryManagerPOJOImpl.class);

    public void addStory(StoryVO storyVO) throws ApplicationException {
    	log.debug("addStory() started");
        try {
            storyDAO.insert(storyVO);
        } catch (DataAccessException e) {
        	log.error("DataAccessException in addStory()", e);
            throw new ApplicationException(
                    "DataAccessException Error in StoryManagerBean.addStory(): "
                    + e.toString(),
                    e);
        }
        log.debug("addStory() finished");
    }

    public Collection findTopStory() throws ApplicationException {
		log.debug("findTopStory() started");
        Collection topStories = null;

        try {

            topStories = storyDAO.findTopStory();

        } catch (DataAccessException e) {
			log.error("DataAccessException in findTopStory()", e);
            throw new ApplicationException("Data access exception raised in StoryManagerBD.findTopStory()", e);
        }

		log.debug("findTopStory() finished");
        return topStories;
    }

    public StoryVO retrieveStory(String primaryKey) {
		log.debug("retrieveStory() started");
        try {
			log.debug("retrieveStory() finished");
            return (StoryVO) storyDAO.findByPK(primaryKey);
        } catch (DataAccessException e) {
			log.error("DataAccessException in retrieveStory()", e);
            throw new ApplicationException(
                    "DataAccessException Error in StoryManagerBean.retrieveStory(): "
                    + e.toString(),
                    e);
        }
		
    }

    public void updateStory(StoryVO storyVO) throws ApplicationException {
		log.debug("updateStory() started");
        try {
            storyDAO.insert(storyVO);
        } catch (DataAccessException e) {
			log.error("DataAccessException in updateStory()", e);
            throw new ApplicationException(
                    "DataAccessException Error in StoryManagerBean.updateStory(): "
                    + e.toString(),
                    e);
        }
		log.debug("updateStory() finished");
    }
}
