/*
 * @(#)TestStoryCRUD.java
 *
 */
package com.apress.javaedge;
import junit.framework.*;
/**
 * TestSuite that runs all the JUnit tests
 *
 * @version $Revision: 1.1.1.1 $
 * @author  Maciej Zawadzki
 */
public class AllTests {
    
    //**************************************************************************
    // CLASS
    //**************************************************************************
    
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
    
    //--------------------------------------------------------------------------
    public static Test suite() {
        TestSuite suite= new TestSuite("JavaEdge Tests");
        suite.addTestSuite(com.apress.javaedge.member.TestMemberCRUD.class);
        suite.addTestSuite(com.apress.javaedge.story.TestStoryCRUD.class);
        
        return suite;
    }
}