package com.apress.javaedge.story;

import com.apress.javaedge.common.ApplicationException;

import java.util.Collection;


public interface IStoryManager {

    public void addStory(StoryVO storyVO) throws ApplicationException;

    public Collection findTopStory() throws ApplicationException;

    public StoryVO retrieveStory(String primaryKey);

    public void updateStory(StoryVO storyVO) throws ApplicationException;
}
