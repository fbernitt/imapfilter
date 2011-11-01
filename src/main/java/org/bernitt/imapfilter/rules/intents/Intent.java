/**
 * 
 */
package org.bernitt.imapfilter.rules.intents;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 * Interface for rule intents.
 * <p>
 * All implementations should overwrite the {@link #toString()} method as its
 * used to display the intent.
 * 
 * @author fbe
 */
public interface Intent {

	/**
	 * Executes this intent for <code>msg</code> and returns <code>true</code>
	 * on success.
	 * 
	 * @param msg
	 *            The message
	 * @return <code>true</code> on success
	 */
	public boolean execute(Message msg, Store store) throws MessagingException;

}
