/**
 * 
 */
package org.bernitt.imapfilter.rules.intents;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Flags.Flag;

/**
 * Moves the message to a specified folder.
 * 
 * @author fbe
 */
public class MoveIntent implements Intent {

	private final String targetFolder;

	public MoveIntent(String targetFolder) {
		this.targetFolder = targetFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.rules.intents.Intent#execute(javax.mail.Message,
	 * javax.mail.Store)
	 */
	public boolean execute(Message msg, Store store) throws MessagingException {
		Folder target = store.getFolder(this.targetFolder);
		if (!target.exists()) {
			throw new RuntimeException("Folder " + this.targetFolder
					+ " does not exist!");
		}

		// System.out.println("System flags:");
		// for (Flag f : msg.getFlags().getSystemFlags()) {
		// if (f == Flag.DELETED) {
		// System.out.println("DELETED");
		// }
		// if (f == Flag.FLAGGED) {
		// System.out.println("FLAGGED");
		// }
		// if (f == Flag.RECENT) {
		// System.out.println("RECENT");
		// }
		// if (f == Flag.SEEN) {
		// System.out.println("SEEN");
		// }
		// if (f == Flag.USER) {
		// System.out.println("USER");
		// }
		// }
		// System.out.println("User flags:");
		// for (String f : msg.getFlags().getUserFlags()) {
		// System.out.println(f);
		// }

		msg.getFolder().copyMessages(new Message[] { msg }, target);
		msg.setFlag(Flag.DELETED, true); // mark as deleted

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": moving to folder "
				+ this.targetFolder;
	}

}
