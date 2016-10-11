package com.apress.javaedge.story;

import com.apress.javaedge.story.ejb.StoryManager;
import com.apress.javaedge.story.ejb.StoryManagerHome;
import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.common.ServiceLocatorException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

/**
 * This implementation is suppose to demonstrate how a business delegate can be implemented using
 * different implementations.
 */
public class StoryManagerEJBImpl{

    StoryManager storyManager = null;

    public StoryManagerEJBImpl() {
        try{
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            StoryManagerHome storyManagerHome = (StoryManagerHome) serviceLocator.getEJBHome(ServiceLocator.STORYMANAGER);
            storyManager = storyManagerHome.create();
        }
        catch(ServiceLocatorException e){
            System.err.println("A ServiceLocator exception has been raised in StoryManagerBD constructor: " + e.toString());
            throw new ApplicationException("A ServiceLocator exception has been raised in StoryManagerBD constructor: " + e.toString());
        }
        catch(CreateException e){
            System.err.println("A Create exception has been raised in StoryManagerBD constructor: " + e.toString());
            throw new ApplicationException("A Create exception has been raised in StoryManagerBD constructor: " + e.toString());
        }
        catch(RemoteException e){
            System.err.println("A remote exception has been raised in StoryManagerBD constructor: " + e.toString());
            throw new ApplicationException("A remote exception has been raised in StoryManagerBD constructor: " + e.toString());
        }

    }


     /* StoryManagerEJBImpl() throws ApplicationException {
        try {
            Context ctx = new InitialContext();
            Object ref = ctx.lookup("storyManager/StoryManager");

            StoryManagerHome storyManagerHome = (StoryManagerHome)
                    PortableRemoteObject.narrow(ref, StoryManagerHome.class);
            storyManager = storyManagerHome.create();
        } catch (NamingException e) {

            throw new ApplicationException("A Naming exception has been raised in " +
                    "StoryManagerBD constructor: " +
                    e.toString());
        } catch (RemoteException e) {
            throw new ApplicationException("A Remote exception has been raised in " +
                    "StoryManagerBD constructor: " +
                    e.toString());
        } catch (CreateException e) {
            throw new ApplicationException("A Create exception has been raised in " +
                    "StoryManagerBD constructor: " +
                    e.toString());
        }
    }*/
}
