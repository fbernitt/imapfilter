/**
 * 
 */
package org.bernitt.imapfilter.utils;

import java.util.Locale;

import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.joda.time.format.PeriodParser;

/**
 * @author fbe
 * 
 */
public class PeriodHelper {

	private final PeriodParser parser;
	private final PeriodFormatter formatter;

	public PeriodHelper() {
		PeriodFormatterBuilder b = new PeriodFormatterBuilder();
		b.appendYears().appendSuffix(" year", " years");
		b.appendSeparator(" and ");
		b.appendMonths().appendSuffix(" month", " months");
		b.appendSeparator(" and ");
		b.appendDays().appendSuffix(" day", " days");

		this.parser = b.toParser();
		this.formatter = b.toFormatter();
	}

	public Period parse(String str) {

		MutablePeriod p = new MutablePeriod();
		this.parser.parseInto(p, str.trim(), 0, Locale.getDefault());

		return p.toPeriod();
	}

	public String format(Period period) {
		return this.formatter.print(period);
	}
}
