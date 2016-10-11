package com.apress.javaedge.search;

import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryVO;
import junit.framework.TestCase;
import org.apache.log4j.Category;
import org.apache.lucene.document.DateField;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.util.Vector;

/**
 * Test case for conversion of stories and comments to Lucene documents
 * 
 * @author Jeff Linwood
 *
 */
public class DocumentConversionTest extends TestCase {
    
    //story for testing
    static private StoryVO story;
    
    // Create Log4j category instance for logging
    static private Category log = Category.getInstance(
    		DocumentConversionTest.class.getName());
    
    //--------------------------------------------------------------------------
    static public void main(String[] args) {
        junit.textui.TestRunner.run(DocumentConversionTest.class);
    }

	//create all of our value objects for testing
	public void setUp() {
		//build a test user
        MemberVO member = new MemberVO();
        member.setEmail("sam@somewhere.com");
        member.setFirstName("Sam");
        member.setLastName("Samski");
        member.setUserId("SSamski");
        member.setPassword("secret");
		
		//build a test story
		story = new StoryVO();
		story.setStoryId(new Long(12));
        story.setStoryTitle("storyTitle");
        story.setStoryIntro("storyIntro");
        story.setStoryBody("storyBody");
        story.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
        story.setStoryAuthor(member);	
		
		//build a test comment
		StoryCommentVO comment = new StoryCommentVO();
		comment.setCommentBody("Test comment body 1");
		comment.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
		comment.setCommentAuthor(member);
		
		//build another test comment
		StoryCommentVO comment2 = new StoryCommentVO();
		comment2.setCommentBody("Test comment body 2");
		comment2.setSubmissionDate(new java.sql.Date(System.currentTimeMillis()));
		comment2.setCommentAuthor(member);		
		
		//make a vector of comments for the story
		Vector comments = new Vector();
		
        comments.add(comment);
        comments.add(comment2);
     	
     	//add the comments to the story   
        story.setComments(comments);
        
	}
	
	//test to see if the document was actually created and not null
	public void testDocumentConversion() {
		
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);
		
		//test the document
		assertTrue(doc!=null);
	}
	
	
	//test to see if the title works	
	public void testTitle() {
		
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);
			
		//test the title
		Field title = doc.getField("title");
		assertTrue(title!=null);	
		assertTrue("storyTitle".equals(title.stringValue()));
	}

	//test to see if the storyId works
	public void testStoryId() {
		
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);
			
		//test the story id
		Field storyId = doc.getField("storyId");
		assertTrue(storyId!=null);
		assertTrue("12".equals(storyId.stringValue()));
	}
	
	//test to see if the intro works
	public void testIntro() {
		
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);
			
		//test the story introduction
		Field intro = doc.getField("introduction");
		assertTrue(intro!=null);
		assertTrue("storyIntro".equals(intro.stringValue()));
	}
	
	//test to see if the date works
	public void testDate() {
	
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);		

		//test the story date - we'll output this to the system console to
		//see if it worked.
		Field date = doc.getField("date");
		assertTrue(date!=null);
		assertTrue(date.stringValue() !=null);
		System.out.println("Submission Date: " + DateField.stringToDate(
							date.stringValue()));
	}
	
	//test to see if the story content and comment content work
	public void testContent() {
		//create the document
		Document doc = DocumentConversionTool.createDocument(story);
		
		//test the content out
		Field content = doc.getField("content");		
		assertTrue(content!=null);
		System.out.println(content.stringValue());
		String s = "storyTitle storyIntro storyBody Test comment body 1 Test comment body 2";
		assertTrue(s.equals(content.stringValue()));
	}
	
	
	
	public void tearDown() {
		
	}
	
	
	
	
}
