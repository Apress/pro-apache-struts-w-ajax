package com.apress.javaedge.search;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

import java.io.IOException;

/**
 * A utility class that allows access to Lucene's search
 * functionality. Takes a query string, and retuns a 
 * SearchResults object. Doesn't contain any Struts code.
 * 
 * @author Jeff Linwood
 *
 */
public class SearchIndex {

	protected static Logger logger = Logger.getLogger(SearchIndex.class);
	
	/**
	 * Takes a string containing query terms and 
	 * passes them to an IndexSearcher. The resulting hits
	 * are passed back as part of a SearchResults class.
	 * 
	 * @param terms The query from the search form
	 * @return A wrapper object, SearchResults, with the hits and the terms 
	 */ 	
	public SearchResults search(String terms) 
			throws IOException, ParseException {

		SearchResults results = new SearchResults();
		
		logger.info("Search terms are: " + terms);
		
		if (terms == null) {
			logger.warn("Terms passed to SearchIndex are null");
			return results;
		}
			
		//set up our analyzer
		Analyzer analyzer = new StandardAnalyzer();
		
		//set up our searcher
		String indexPath = SearchConfiguration.getIndexPath();		
		Searcher searcher = new IndexSearcher(indexPath);

		//create a query object out of our query
		Query query = QueryParser.parse(terms,
						DocumentConversionTool.CONTENT_FIELD,
						analyzer);
		
		//get all of the hits for the query term out of the index
		Hits hits = searcher.search(query);
		
		results.setHits(hits);
		results.setLength(hits.length());
		results.setTerms(terms);	

		if (hits == null) {
			logger.info("Hits object is null for: " + terms);
		}
		
		//debugging
		logger.info("Number of hits found for: " + terms + " is " + hits.length());
		
		return results;
	}
}
