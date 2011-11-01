/**
 * 
 */
package org.bernitt.imapfilter.rules.intents;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.search.AndTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SubjectTerm;

/**
 * Backup intent. Creates a backup of message in the specified local target
 * maildir.
 * 
 * @author fbe
 */
public class BackupIntent implements Intent {

	private final String targetFolder;
	private final String targetMaildir;

	public BackupIntent(final String targetFolder, final String targetMaildir) {
		this.targetFolder = targetFolder;
		this.targetMaildir = targetMaildir;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.rules.intents.Intent#execute(javax.mail.Message,
	 * javax.mail.Store)
	 */
	public boolean execute(Message msg, Store store) throws MessagingException {
		java.util.Properties p = new java.util.Properties();
		p.put("mail.store.maildir.autocreatedir", "true");
		Session session = Session.getDefaultInstance(p);

		Store mailDirStore = session.getStore(new URLName(this.targetMaildir));
		mailDirStore.connect();

		Folder targetFolder = mailDirStore.getFolder(this.targetFolder);
		if (!targetFolder.exists()) {
			System.out.println("Creating folder " + targetFolder.getFullName());
			if (!targetFolder.create(Folder.HOLDS_FOLDERS
					| Folder.HOLDS_MESSAGES)) {
				throw new RuntimeException("Failed to create folder "
						+ this.targetFolder + " in maildir "
						+ this.targetMaildir);
			}
		}

		if (!checkExistence(targetFolder, msg)) {
			targetFolder.appendMessages(new Message[] { msg });
		} else {
			System.err.println("Message [" + msg.getSubject()
					+ "] already exists in target folder, skipping backup");
		}

		mailDirStore.close();

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Backup message to maildir " + this.targetMaildir + ", folder "
				+ this.targetFolder;
	}

	/**
	 * Returns <code>true</code> if message already exists in folder.
	 * 
	 * @param folder
	 *            The folder
	 * @param msg
	 *            The message
	 * @return <code>true</code> if message already exists in folder
	 * @throws MessagingException
	 */
	public boolean checkExistence(Folder folder, Message msg)
			throws MessagingException {

		if (!folder.exists()) {
			return false;
		}

		folder.open(Folder.READ_ONLY);

		SubjectTerm subjectTerm = new SubjectTerm(msg.getSubject());
		SentDateTerm sentTerm = new SentDateTerm(SentDateTerm.EQ, msg
				.getSentDate());
		FromTerm fromTerm = new FromTerm(msg.getFrom()[0]);

		boolean result = (folder.search(new AndTerm(new SearchTerm[] {
				subjectTerm, sentTerm, fromTerm })).length > 0);

		folder.close(false);

		return result;
	}
}
