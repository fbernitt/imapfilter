/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;

/**
 * Returns unseen mails only.
 * 
 * @author fbe
 */
public class UnseenSearchFilterTerm implements SearchFilterTerm {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		FlagTerm recent = new FlagTerm(new Flags(Flag.RECENT), true);
		FlagTerm unseen = new FlagTerm(new Flags(Flag.SEEN), false);

		return new OrTerm(recent, unseen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UNSEEN";
	}

}
