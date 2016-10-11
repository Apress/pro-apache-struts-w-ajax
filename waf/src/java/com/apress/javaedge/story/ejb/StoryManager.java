package com.apress.javaedge.story.ejb;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.IStoryManager;
import com.apress.javaedge.struts.poststory.PostStoryForm;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * @author jcarnell
 *
 * Remote Interface for my StoryManager EJB.
 */
public interface StoryManager extends EJBObject, IStoryManager {
}
