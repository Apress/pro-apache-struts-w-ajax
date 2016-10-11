package com.apress.javaedge.story;


import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.story.dao.StoryDAO;

import java.util.Collection;

/**
 * BusinessDelegate pattern that hides how actual business transactions take place.
 *
 *  business delegates implementations.  Then I can separate my EJB from my POJ
 *  implementation
 */
public class StoryManagerBD {

    public static final IStoryManager getStoryManagerBD() {
        return new StoryManagerPOJOImpl();
    }

}
