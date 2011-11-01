/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import junit.framework.TestCase;

/**
 * Tests the {@link SearchFilterConverter}.
 * 
 * @author fbe
 */
public class SearchFilterConverterTest extends TestCase {

	public void testAll() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class, "ALL");
		assertNotNull(f);
		assertTrue(f instanceof AllSearchFilter);
		assertEquals("ALL", f.toString());
	}

	public void testUnseen() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"UNSEEN");
		assertNotNull(f);
		assertEquals("UNSEEN", f.toString());
	}

	public void testNot() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"NOT UNSEEN");
		assertNotNull(f);
		assertEquals("NOT UNSEEN", f.toString());
	}

	public void testAnd() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"UNSEEN && BEFORE 10 days");
		assertNotNull(f);
		assertEquals("UNSEEN && BEFORE 10 days", f.toString());
	}

	public void testFrom() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"FROM foo@bar.de");
		assertNotNull(f);
		assertEquals("FROM foo@bar.de", f.toString());
	}

	public void testHeader() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"HEADER foo=bar");
		assertNotNull(f);
		assertEquals("HEADER foo=bar", f.toString());
	}

	public void testSubject() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"SUBJECT ^HelloWorld");
		assertNotNull(f);
		assertEquals("SUBJECT ^HelloWorld", f.toString());
	}

	public void testOr() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"UNSEEN || BEFORE 10 days");
		assertNotNull(f);
		assertEquals("UNSEEN || BEFORE 10 days", f.toString());
	}

	public void testComplex() {
		SearchFilterConverter conv = new SearchFilterConverter();
		SearchFilter f = (SearchFilter) conv.convert(SearchFilter.class,
				"WITHIN 7 days || UNSEEN && NOT BEFORE 50 days");
		assertNotNull(f);
		assertEquals("WITHIN 7 days || UNSEEN && NOT BEFORE 50 days", f
				.toString());
	}
}
