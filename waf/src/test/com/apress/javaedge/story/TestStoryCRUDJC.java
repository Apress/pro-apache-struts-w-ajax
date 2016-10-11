package com.apress.javaedge.story;

import java.util.Vector;

import junit.framework.TestCase;

import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.member.dao.MemberDAO;
import com.apress.javaedge.story.dao.StoryDAO;


/**
 * Tests all of the CRUD logic for the StoryDAO class.
 * @version $Revision: 1.1.1.1 $
 * @author
 */
public class TestStoryCRUDJC extends TestCase {
    private StoryDAO dao = new StoryDAO();
    private Long pk = null;
    
    public TestStoryCRUDJC(String name){
        super(name);
    }
    
    // Create Log4j category instance for logging
    static private org.apache.log4j.Category log = org.apache.log4j.Category.getInstance(TestStoryCRUDJC.class.getName());
    
    //--------------------------------------------------------------------------
    static public void main(String[] args) {
        junit.textui.TestRunner.run(TestStoryCRUDJC.class);
    }
    
    /**
     * Inserts a single record in the JavaEdge database.
     * @throws DataAccessException
     */
    public void testInsert() throws DataAccessException{
        StoryVO storyVO = new StoryVO();
        
        storyVO.setStoryTitle("This is a story title jcc");
        storyVO.setStoryIntro("This is a unit test story intro.");
        storyVO.setStoryBody("This is a new unit test story body.");
        
        MemberDAO memberDAO = new MemberDAO();
        MemberVO memberVO = (MemberVO) memberDAO.findByPK("2");
        MemberVO commentMemberVO = (MemberVO) memberDAO.findByPK("3");
        storyVO.setStoryAuthor(memberVO);
        
        StoryCommentVO storyCommentVO = new StoryCommentVO();
        storyCommentVO.setCommentBody("This toolis goofy!");
        storyCommentVO.setCommentAuthor(commentMemberVO);
        
        dao.insert(storyVO);
    }
    
    
    /**
     * Retrieves a single story record from the JavaEdge database.
     * @throws DataAccessException
     */
    public void testFindByPK() throws DataAccessException{
        testInsert();
        StoryVO storyVO = (StoryVO) dao.findByPK(pk.toString());
        assertNotNull("Story is not null", storyVO);
        assertNotNull("Story author is not null", storyVO.getStoryAuthor());
    }
    
    
    /**
     * Updates a single story record in the JavaEdge database.
     * @throws DataAccessException
     */
    public void testUpdate() throws DataAccessException{
        testInsert();
        StoryDAO storyDAO = new StoryDAO();
        StoryVO storyVO = (StoryVO)  storyDAO.findByPK(pk.toString());
        
        StoryCommentVO storyCommentVO = new StoryCommentVO();
        storyCommentVO.setCommentBody("This tool very goofy.  I have performed an update!!!!");
        storyCommentVO.setCommentAuthor(storyVO.getStoryAuthor());
        
        Vector comments = new Vector();
        comments.add(storyCommentVO);
        
        storyVO.setComments(comments);
        dao.update(storyVO);
        
    }
    
    /**
     * Deletes a single story record from the JavaEdge database.
     * @throws DataAccessException
     */
    public void testDelete() throws DataAccessException{
        testInsert();
        
        StoryDAO storyDAO = new StoryDAO();
        StoryVO storyVO = (StoryVO)  storyDAO.findByPK(pk.toString());
        
        dao.delete(storyVO);
    }
}
