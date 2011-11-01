/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.search.HeaderTerm;
import javax.mail.search.SearchTerm;

/**
 * @author fbe
 * 
 */
public class HeaderSearchFilterTerm implements SearchFilterTerm {

	private final String headerName;
	private final String pattern;

	public HeaderSearchFilterTerm(final String headerName, final String pattern) {
		this.headerName = headerName;
		this.pattern = pattern;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		return new HeaderTerm(this.headerName, this.pattern);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HEADER " + this.headerName + "=" + this.pattern;
	}

}
