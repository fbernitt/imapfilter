/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Interface for search filters.
 * 
 * @author fbe
 */
public interface SearchFilter {

	/**
	 * Searches the folder and returns the matching messages.
	 * 
	 * @param folder
	 *            The folder
	 * @return The matching messages
	 */
	public Message[] search(Folder folder) throws MessagingException;
}
