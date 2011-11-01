/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

/**
 * @author fbe
 * 
 */
public class FromSearchFilterTerm implements SearchFilterTerm {

	private final String address;

	public FromSearchFilterTerm(final String address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		try {
			return new FromTerm(new InternetAddress(this.address));
		} catch (AddressException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FROM " + this.address;
	}

}
