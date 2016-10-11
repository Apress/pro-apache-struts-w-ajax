package com.apress.javaedge.search;

import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.story.StoryVO;
import com.apress.javaedge.story.dao.StoryDAO;
import org.apache.log4j.Category;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Adds stories and comments from JavaEdge to Lucene's index.
 * 
 * Gets a list of all stories from the story object.  Transforms those
 * stories and the related comments into Document objects for Lucene.
 * Uses Lucene's IndexWriter to add them to Lucene's index.
 * 
 * @author Jeff Linwood
 * 
 */
public class IndexContent {

    // Create Log4j category instance for logging
    static private Category log = Category.getInstance(
    		IndexContent.class.getName());
    		
	/**
	 * Get a copy of the writer we use to add entries to the index
	 * Uses the standard analyzer.
	 * 
	 * @return an IndexWriter that points to the configured Lucene index.
	 */
	public IndexWriter getWriter() throws IOException { 
		      
      	Analyzer analyzer = new StandardAnalyzer();
		IndexWriter writer = new IndexWriter(SearchConfiguration.getIndexPath(),
								 analyzer, true);
		return writer;
	}  
	
	/**
	 * Get a writer, get the stories, convert each story to a Lucene Document.
	 * add to index, close index.
	 * 
	 */
	
	public void createIndex() {
		try {
			
			// Lucene's index generator
			IndexWriter writer = getWriter();
		
			//from the DAO
			Collection stories = getAllStories();
			if (stories == null) {
				return;	
			}
			
			//Easier to use an iterator for retrieving the stories
			Iterator iter = stories.iterator();
			while (iter.hasNext()) {

				//this could throw a class cast exception
				StoryVO story = (StoryVO) iter.next();
				
				//we wrote the Document Conversion Tool specifically for the
				//story value objects
				Document doc = DocumentConversionTool.createDocument(story);

				//add the converted document to the lucene index
				writer.addDocument(doc);
			}
			
			closeWriter(writer);
		}
		catch (IOException ie) {
			log.error ("Error creating Lucene index: " + ie.getMessage(),ie);
		}
		catch (ClassCastException cce) {
			log.error ("Error casting object to StoryVO: " + cce.getMessage(),cce);
		}
	}
	
	/**
	 * Make a call out to the DAO to get all the stories
	 * 
	 * @return a Collection of StoryVO objects
	 */
	public Collection getAllStories() {
		try {
			
			StoryDAO storyDAO = new StoryDAO();
		
			return storyDAO.findAllStories();	
		}
		catch (DataAccessException dae) {
			log.error("Error retrieving all stories from DAO: " + dae.getMessage(),
						dae);								
		}
		return null;
	}
	
	/**
	 * Close down the writer, after running the index optimizer.
	 * 
	 * @param writer An open IndexWriter
	 */
	public void closeWriter(IndexWriter writer) throws IOException {		
    	if (writer == null) {
    		return;
    	}
      	writer.optimize();
      	writer.close();		
	}
}
