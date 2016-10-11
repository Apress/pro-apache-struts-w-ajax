package com.apress.javaedge.story.ejb;


import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * @todo Need to see how to use XDoclet to generate this class.
 */
public interface StoryManagerHome extends EJBHome {
    public StoryManager create() throws RemoteException, CreateException;
}