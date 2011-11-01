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
 * Returns all mails received before specified date.
 * 
 * @author fbe
 */
public class BeforeDateSearchFilterTerm implements SearchFilterTerm {

	private final Date date;
	private final Period period;

	public BeforeDateSearchFilterTerm(final Date date, Period period) {
		this.date = date;
		this.period = period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.search.SearchFilterTerm#toSearchTerm()
	 */
	public SearchTerm toSearchTerm() {
		return new ReceivedDateTerm(ReceivedDateTerm.LT, this.date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BEFORE " + new PeriodHelper().format(this.period);
	}
}
