/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.Converter;
import org.bernitt.imapfilter.utils.PeriodHelper;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 * Beanutils converter for search filters.
 * 
 * @author fbe
 */
public class SearchFilterConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value) {
		if (!type.equals(SearchFilter.class)) {
			throw new IllegalArgumentException("Not able to convert to "
					+ type.getName());
		}

		String str = value.toString();

		if ("ALL".equalsIgnoreCase(str)) {
			return new AllSearchFilter();
		}

		return parseFilter(str);
	}

	private SearchFilter parseFilter(String filter) {
		return new SearchTermFilter(parseFilterTerm(filter));
	}

	private SearchFilterTerm parseFilterTerm(String term) {
		// first split by or
		term = term.trim();

		String[] orParts = term.split("[|][|]");
		if (orParts.length > 1) {
			return new OrSearchFilterTerm(convertTerms(orParts));
		}
		// no or found, check and
		String[] andParts = term.split("&&");
		if (andParts.length > 1) {
			return new AndSearchFilterTerm(convertTerms(andParts));
		}

		// still here? seems to be a simple term
		if ("UNSEEN".equalsIgnoreCase(term)) {
			return new UnseenSearchFilterTerm();
		}

		if (term.toLowerCase().startsWith("subject ")) {
			return new SubjectSearchFilterTerm(term.substring("subject "
					.length()));
		}

		if (term.toLowerCase().startsWith("from ")) {
			return new FromSearchFilterTerm(term.substring("from ".length()));
		}

		if (term.toLowerCase().startsWith("not ")) {
			return new NotSearchFilterTerm(parseFilterTerm(term
					.substring("not ".length())));
		}

		if (term.toLowerCase().startsWith("header ")) {
			String[] parts = term.substring("header ".length()).split("=");
			if (parts.length != 2) {
				throw new IllegalArgumentException(
						"Expecte: HEADER headerName=pattern");
			}
			return new HeaderSearchFilterTerm(parts[0].trim(), parts[1].trim());
		}

		if (term.toLowerCase().startsWith("before")) {
			Period p = new PeriodHelper().parse(term.substring("before"
					.length()));
			if (p == null) {
				throw new IllegalArgumentException(
						"Not able to parse before definition: " + term);
			}

			return new BeforeDateSearchFilterTerm(new DateTime().minus(p)
					.toDate(), p);
		}

		if (term.toLowerCase().startsWith("within")) {
			Period p = new PeriodHelper().parse(term.substring("within"
					.length()));
			if (p == null) {
				throw new IllegalArgumentException(
						"Not able to parse before definition: " + term);
			}

			return new WithinDateSearchFilterTerm(new DateTime().minus(p)
					.toDate(), p);
		}

		// still here? invalid term!
		throw new IllegalArgumentException("Unkown filter type: ["
				+ term.toString() + "]");
	}

	private List<SearchFilterTerm> convertTerms(String[] terms) {
		ArrayList<SearchFilterTerm> result = new ArrayList<SearchFilterTerm>(
				terms.length);

		for (String term : terms) {
			result.add(parseFilterTerm(term));
		}

		return result;
	}

}
