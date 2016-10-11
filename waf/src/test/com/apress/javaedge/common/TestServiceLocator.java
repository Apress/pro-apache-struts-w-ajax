package com.apress.javaedge.common;

import junit.framework.TestCase;
/**
 * @author Administrator
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TestServiceLocator extends TestCase {

    public TestServiceLocator(String name) {
        super(name);
    }

//    public void testRetrieveStoryManagerEJB() {
//        try {
//            ServiceLocator serviceLocator = ServiceLocator.getInstance();
//
//            StoryManagerHome storyManager = (StoryManagerHome) serviceLocator.getEJBHome(ServiceLocator.STORYMANAGER);
//            assertNotNull("Story Manager EJB is null", storyManager);
//        }
//        catch(ServiceLocatorException e){
//            fail("ServiceLocatorException thrown in TestServiceLocator.testRetrieveStoryManagerEJB(): " + e.toString());
//        }
//    }
//
//    public void testDatabaseConnection() {
//        fail("TestServiceLocator.testDatabaseConnection(): Not Yet Implemented");
//    }

    public void testRetrievePersistenceBroker() {
        fail("TestServiceLocator.testRetrievePersistenceBroker() Not Yet Implemented");
    }

}