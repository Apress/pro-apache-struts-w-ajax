/*
 * @(#)TestStoryCRUD.java
 *
 */
package com.apress.javaedge.story;
import com.apress.javaedge.story.dao.*;
import com.apress.javaedge.member.*;
import com.apress.javaedge.member.dao.*;
import com.apress.javaedge.common.*;
import junit.framework.*;
/**
 * @version $Revision: 1.1.1.1 $
 * @author
 */
public class TestStoryCRUD extends TestCase {
    
    //**************************************************************************
    // CLASS
    //**************************************************************************
    
    // Create Log4j category instance for logging
    static private org.apache.log4j.Category log = org.apache.log4j.Category.getInstance(TestStoryCRUD.class.getName());
    
    //--------------------------------------------------------------------------
    static public void main(String[] args) {
        junit.textui.TestRunner.run(TestStoryCRUD.class);
    }

    //**************************************************************************
    // INSTANCE
    //**************************************************************************
    private StoryDAO dao = null;
    
    //--------------------------------------------------------------------------
    public void setUp()
    throws DataAccessException {
        dao = new StoryDAO();
    }

    //--------------------------------------------------------------------------
    public void tearDown() {
        dao = null;
    }
    
    //--------------------------------------------------------------------------
    public void testCRUD() 
    throws DataAccessException {
        MemberVO member = new MemberVO();
        member.setEmail("sam@somewhere.com");
        member.setFirstName("Sam");
        member.setLastName("Samski");
        member.setUserId("SSamski");
        member.setPassword("secret");
        MemberDAO memberDao = new MemberDAO();
        memberDao.insert(member);
        log.info("member before story insert: " + member);
        
        StoryVO story = new StoryVO();
        story.setStoryTitle("storyTitle");
        story.setStoryIntro("storyIntro");
        story.setStoryBody("storyBody");
        story.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
        story.setStoryAuthor(member);
        
        dao.insert(story);
        Long pk = story.getStoryId();
        log.info("Inserted story: " + story);
        log.info("member: " + member);
        
        story = (StoryVO) dao.findByPK(pk.toString());
        log.info("Restored story: " + story);
        log.info("Restored member: " + story.getStoryAuthor());
        
    }
        
}
