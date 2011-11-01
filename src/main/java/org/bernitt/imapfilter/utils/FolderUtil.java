/**
 * 
 */
package org.bernitt.imapfilter.utils;

import javax.mail.Folder;
import javax.mail.MessagingException;

/**
 * @author fbe
 * 
 */
public class FolderUtil {

	/**
	 * Returns the root folder for folder.
	 * 
	 * @param folder
	 *            The folder
	 * @return the root folder
	 */
	public static Folder getRootFolder(Folder folder) throws MessagingException {
		Folder parent = folder;
		Folder root = folder;
		while ((parent = folder.getParent()) != null) {
			if (parent == null) {
				return root;
			} else {
				root = parent;
			}
		}
		throw new RuntimeException("Not able to find root folder!");
	}
}
