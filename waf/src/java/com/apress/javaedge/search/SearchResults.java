package com.apress.javaedge.search;

import org.apache.lucene.search.Hits;

/**
 * A wrapper object for the Lucene search results.
 * 
 * @author Jeff Linwood
 *
 */
public class SearchResults {

	protected int length = 0;
	
	protected Hits hits = null;
	
	protected String terms = null;

	/**
	 * Returns the hits.
	 * @return Hits
	 */
	public Hits getHits() {
		return hits;
	}

	/**
	 * Returns the length.
	 * @return int
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the hits.
	 * @param hits The hits to set
	 */
	public void setHits(Hits hits) {
		this.hits = hits;
	}

	/**
	 * Sets the length.
	 * @param length The length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Returns the terms.
	 * @return String
	 */
	public String getTerms() {
		return terms;
	}

	/**
	 * Sets the terms.
	 * @param terms The terms to set
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}

}
