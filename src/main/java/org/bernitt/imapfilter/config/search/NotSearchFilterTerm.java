/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.search.NotTerm;
import javax.mail.search.SearchTerm;

/**
 * @author fbe
 * 
 */
public class NotSearchFilterTerm implements SearchFilterTerm {

	private final SearchFilterTerm term;

	public NotSearchFilterTerm(SearchFilterTerm term) {
		this.term = term;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		return new NotTerm(this.term.toSearchTerm());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NOT " + this.term.toString();
	}

}
