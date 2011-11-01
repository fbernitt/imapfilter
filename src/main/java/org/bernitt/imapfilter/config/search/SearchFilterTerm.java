/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.search.SearchTerm;

/**
 * Defines a search filter term.
 * 
 * @author fbe
 */
public interface SearchFilterTerm {

	/**
	 * Converts this filter term to a {@link SearchTerm}.
	 * 
	 * @return The search term
	 */
	public SearchTerm toSearchTerm();
}
