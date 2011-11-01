/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.bernitt.imapfilter.config.search.SearchFilter;
import org.bernitt.imapfilter.config.search.SearchTermFilter;
import org.bernitt.imapfilter.config.search.UnseenSearchFilterTerm;

/**
 * Defines a folder and its connected rules.
 * <p>
 * The default search filter is {@link UnseenSearchFilterTerm}!
 * 
 * @author fbe
 */
public class FolderDefinition {

	private String name;
	private List<RuleDefinition> rules = new LinkedList<RuleDefinition>();
	private SearchFilter searchFilter = new SearchTermFilter(
			new UnseenSearchFilterTerm());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rules
	 */
	public List<RuleDefinition> getRules() {
		return this.rules;
	}

	/**
	 * @param rules
	 *            the rules to set
	 */
	public void setRules(List<RuleDefinition> rules) {
		this.rules = rules;
	}

	/**
	 * Sets the search filter.
	 * 
	 * @param searchFilter
	 *            the searchFilter to set
	 */
	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

	/**
	 * Returns the search filter for this folder definition.
	 * 
	 * @return the search filter for this folder definition
	 */
	public SearchFilter getSearchFilter() {
		return this.searchFilter;
	}

	/**
	 * Returns <code>true</code> if at least one active rule exists.
	 * 
	 * @return <code>true</code> if at least one active rule exists
	 */
	public boolean hasActiveRules() {
		return this.rules.size() > 0;
	}
}
