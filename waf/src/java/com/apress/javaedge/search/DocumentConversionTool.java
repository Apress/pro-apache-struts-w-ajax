package com.apress.javaedge.search;

import com.apress.javaedge.story.StoryCommentVO;
import com.apress.javaedge.story.StoryVO;
import org.apache.lucene.document.DateField;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.util.Iterator;

/**
 * Converts JavaEdge Story value objects to Lucene Document objects
 * Has a JUnit test class, com.apress.javaedge.search.DocumentConverstionTest
 * 
 * 
 * @author Jeff Linwood
 *
 */
public class DocumentConversionTool {

	//the name of the field that contains the content, title, etc.
	public static final String CONTENT_FIELD = "content";
	
	/**
	 * Creates a Lucene Document out of a JavaEdge Story value object
	 * Also uses the comments on the story value object if they exist
	 * 
	 * @param story A fully populated story value object
	 * @return A complete Lucene Document ready to go in the index
	 */
	public static Document createDocument(StoryVO story) {

    	// create a Document object
	    Document document = new Document();

		// create a field that is only stored in the index
		Long storyId = story.getStoryId();
		if (storyId != null) {
			document.add(Field.UnIndexed("storyId",storyId.toString()));
		}
		
		// create a field that is tokenized, indexed, and stored in the index		
		String title = story.getStoryTitle();
		document.add(Field.Text("title",title));	
		
		// create a field that is tokenized, indexed, and stored in the index
		String introduction = story.getStoryIntro();
		document.add(Field.Text("introduction",introduction));
		
		//create a field that is indexed and stored in the index
		java.util.Date date = story.getSubmissionDate();
		if (date != null) {
			document.add(Field.Keyword("date",DateField.dateToString(date)));
		}
		
		addContent(story, document, title, introduction);
		
		return document;		
	}

	/**
	 * Add the story title, introduction, body content, and comment bodies to
	 * the content.
	 * 
	 * @param story A fully populated story
	 * @param document The Document object we are populating
	 * @param title The title we already took off the value object
	 * @param introduction The intro we already took off the value object
	 */
	protected static void addContent(StoryVO story,Document document,
							String title, String introduction) {
		//create the content for the search query
		//and then store it in the index, tokenized and indexed
		String content = title + " " + introduction + 
					" " + story.getStoryBody();

		
		Iterator iter = story.getComments().iterator();
		while (iter.hasNext()) {
			StoryCommentVO comment = (StoryCommentVO) iter.next();
			if (comment != null) {
				content = content + " " + comment.getCommentBody();
			}
		}
		
		document.add(Field.UnStored(CONTENT_FIELD,content));
	}	


}
