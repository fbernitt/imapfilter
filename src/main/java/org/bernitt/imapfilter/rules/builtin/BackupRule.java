/**
 * 
 */
package org.bernitt.imapfilter.rules.builtin;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.bernitt.imapfilter.rules.RuleResult;
import org.bernitt.imapfilter.rules.intents.BackupIntent;
import org.bernitt.imapfilter.rules.intents.DeleteIntent;

/**
 * A backup rule. All matching messages are exported to a local maildir.
 * 
 * @author fbe
 */
public class BackupRule extends GenericRule {

	private String targetFolder;
	private String targetMaildir;
	private boolean deleteOriginal = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.rules.Rule#apply(javax.mail.Message)
	 */
	public RuleResult apply(Message msg) throws MessagingException {
		if (checkConditions(msg)) {
			BackupIntent bi = new BackupIntent(this.targetFolder,
					this.targetMaildir);
			if (this.deleteOriginal) {
				return new RuleResult(bi, new DeleteIntent());
			} else {
				return new RuleResult(bi);
			}

		} else {
			return null;
		}
	}

	/**
	 * @param targetFolder
	 *            the targetFolder to set
	 */
	public void setTargetFolder(String targetFolder) {
		this.targetFolder = targetFolder;
	}

	/**
	 * @param targetMaildir
	 *            the targetMaildir to set
	 */
	public void setTargetMaildir(String targetMaildir) {
		this.targetMaildir = targetMaildir;
	}

	/**
	 * @param deleteOriginal
	 *            the deleteOriginal to set
	 */
	public void setDeleteOriginal(boolean deleteOriginal) {
		this.deleteOriginal = deleteOriginal;
	}

}
