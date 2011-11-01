/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

/**
 * Matches the subject.
 * 
 * @author fbe
 */
public class SubjectSearchFilterTerm implements SearchFilterTerm {

	private final String subject;

	public SubjectSearchFilterTerm(final String subject) {
		this.subject = subject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		return new SubjectTerm(this.subject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SUBJECT " + this.subject;
	}

}
