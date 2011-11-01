/**
 * 
 */
package org.bernitt.imapfilter.utils;

import junit.framework.TestCase;

import org.joda.time.Period;

/**
 * @author fbe
 * 
 */
public class PeriodHelperTest extends TestCase {

	public void testSimple() {
		Period p = new PeriodHelper().parse("10 days");
		assertNotNull(p);
		assertEquals(10, p.getDays());
	}

	public void testComplex() {
		Period p = new PeriodHelper().parse("1 year and 10 days");
		assertNotNull(p);

		assertEquals(10, p.getDays());
		assertEquals(1, p.getYears());
	}

	public void testFullDate() {
		Period p = new PeriodHelper().parse("1 year and 2 months and 10 days");
		assertNotNull(p);

		assertEquals(10, p.getDays());
		assertEquals(1, p.getYears());
		assertEquals(2, p.getMonths());

	}
}
