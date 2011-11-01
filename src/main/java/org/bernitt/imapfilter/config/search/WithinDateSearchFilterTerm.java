/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import java.util.Date;

import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.bernitt.imapfilter.utils.PeriodHelper;
import org.joda.time.Period;

/**
 * @author fbe
 * 
 */
public class WithinDateSearchFilterTerm implements SearchFilterTerm {

	private final Date date;
	private final Period period;

	public WithinDateSearchFilterTerm(final Date date, Period period) {
		this.date = date;
		this.period = period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		return new ReceivedDateTerm(ReceivedDateTerm.GT, this.date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WITHIN " + new PeriodHelper().format(this.period);
	}

}
