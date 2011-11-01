/**
 * 
 */
package org.bernitt.imapfilter.rules.builtin;

import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.bernitt.imapfilter.rules.RuleResult;
import org.bernitt.imapfilter.rules.intents.Intent;
import org.bernitt.imapfilter.rules.intents.MarkAsReadIntent;
import org.bernitt.imapfilter.rules.intents.MoveIntent;

/**
 * @author fbe
 * 
 */
public class MoveRule extends GenericRule {

	private boolean markAsRead = true;
	private String targetFolder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.rules.Rule#apply(javax.mail.Message)
	 */
	public RuleResult apply(Message msg) throws MessagingException {
		if (checkConditions(msg)) {
			ArrayList<Intent> intents = new ArrayList<Intent>();
			if (this.markAsRead) {
				intents.add(new MarkAsReadIntent());
			}
			intents.add(new MoveIntent(this.targetFolder));
			return new RuleResult(intents);
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.markAsRead) {
			return "Mark message as read and move it to " + this.targetFolder;
		} else {
			return "Move message to " + this.targetFolder;
		}
	}

	/**
	 * @param markAsRead
	 *            the markAsRead to set
	 */
	public void setMarkAsRead(boolean markAsRead) {
		this.markAsRead = markAsRead;
	}

	/**
	 * @param targetFolder
	 *            the targetFolder to set
	 */
	public void setTargetFolder(String targetFolder) {
		this.targetFolder = targetFolder;
	}

}
