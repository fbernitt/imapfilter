/**
 * 
 */
package org.bernitt.imapfilter.config.search;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Returns all mails in folder.
 * 
 * @author fbe
 */
public class AllSearchFilter implements SearchFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.config.search.SearchFilter#search(javax.mail.Folder
	 * )
	 */
	public Message[] search(Folder folder) throws MessagingException {
		return folder.getMessages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ALL";
	}

}
