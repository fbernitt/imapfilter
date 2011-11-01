/**
 * 
 */
package org.bernitt.imapfilter.contrib;

import java.util.regex.Pattern;

import junit.framework.TestCase;

/**
 * Due to some pattern machting problems this test case helps to identify some
 * problems.
 * 
 * @author fbe
 */
public class PatternTest extends TestCase {

	public void testIt() throws Exception {
		assertTrue(Pattern
				.matches(
						"Cron .otrs@gemini.",
						"Cron <otrs@gemini> OTRS_PATH=/opt/otrs/;cd $OTRS_PATH;$OTRS_PATH/bin/PostMasterPOP3.pl > /dev/null"));
	}
}
