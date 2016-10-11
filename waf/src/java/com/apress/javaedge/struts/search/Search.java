package com.apress.javaedge.struts.search;

import com.apress.javaedge.search.SearchIndex;
import com.apress.javaedge.search.SearchResults;
import org.apache.log4j.Logger;
import org.apache.lucene.queryParser.ParseException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jeff Linwood
 *
 *
 */
public class Search extends Action {
	
	private static Logger logger = Logger.getLogger(Search.class);

    public ActionForward execute (ActionMapping mapping,
                                ActionForm     form,
                                HttpServletRequest request,
                                HttpServletResponse response){
        
		logger.info("***Entering Search***");

		if (!(form instanceof SearchForm)) {
			logger.warn("Form passed to Search not an instance of SearchForm"); 	
			return (mapping.findForward("system.failure"));
		}
		
		//get the query from the form bean
		String query = ((SearchForm)form).getQuery();
		
		//get the search results
		SearchIndex searchIndex = new SearchIndex();

		SearchResults results = null;
		try {
			results = searchIndex.search(query);
			logger.info("Found " + results.getLength() + " hits.");
		} 
		catch (IOException e) {
			logger.error("IOException with search index for: " + query,e);
			return (mapping.findForward("system.failure"));
		} 
		catch (ParseException e) {
			logger.error("ParseException with search index for: " + query,e);	
			return (mapping.findForward("system.failure"));
		}
		
		//put the search results into the request
		request.setAttribute("searchResults", results);

		logger.info("***Leaving Search***");
        
        return (mapping.findForward("search.success"));
    }

}
