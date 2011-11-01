/**
 * 
 */
package org.bernitt.imapfilter.rules;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Interface for all filter rules.
 * <p>
 * Filtering is done using the apply rule. If this rule returns a not
 * <code>null</code> result, this result gets executed (if not in dry run mode).
 * 
 * @author fbe
 * 
 */
public interface Rule {

	/**
	 * Calls rule for Message. If rule applies it returns its wanted result as a
	 * {@link RuleResult} instance.
	 * 
	 * @param msg
	 *            The mail message
	 * @return The result or <code>null</code>
	 */
	public RuleResult apply(Message msg) throws MessagingException;
}
