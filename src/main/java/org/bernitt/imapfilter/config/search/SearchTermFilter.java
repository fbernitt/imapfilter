/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Search filter defined by {@link SearchFilterTerm}s.
 * 
 * @author fbe
 */
public class SearchTermFilter implements SearchFilter {

	private final SearchFilterTerm term;

	public SearchTermFilter(final SearchFilterTerm term) {
		this.term = term;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.config.search.SearchFilter#search(javax.mail.Folder
	 * )
	 */
	public Message[] search(Folder folder) throws MessagingException {
		return folder.search(this.term.toSearchTerm());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.term.toString();
	}

}
