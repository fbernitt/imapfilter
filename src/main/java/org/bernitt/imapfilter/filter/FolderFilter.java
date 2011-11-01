/**
 * 
 */
package org.bernitt.imapfilter.filter;

import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.bernitt.imapfilter.config.Config;
import org.bernitt.imapfilter.config.FolderDefinition;
import org.bernitt.imapfilter.config.RuleDefinition;
import org.bernitt.imapfilter.rules.RuleResult;

import com.sun.mail.imap.IMAPFolder.FetchProfileItem;

/**
 * Applies the rules on specified folder.
 * 
 * @author fbe
 */
public class FolderFilter implements Runnable {

	private final Store store;
	private final FolderDefinition folderDef;
	private final Config config;

	public FolderFilter(FolderDefinition def, Store store, Config config) {
		this.folderDef = def;
		this.store = store;
		this.config = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			Folder folder = this.store.getFolder(this.folderDef.getName());
			if (!folder.exists()) {
				throw new RuntimeException("Folder " + this.folderDef.getName()
						+ " does not exist!");
			}

			folder.open(Folder.READ_WRITE);
			try {
				if (this.config.isVerbose()) {
					System.out.println("Processing folder "
							+ folder.getFullName());
				}
				Message[] msgs = searchMessages(folder);
				fetchMessages(folder, msgs); // load needed envelope and headers
				// in one operation

				for (Message msg : msgs) {
					if (this.config.isVerbose()) {
						System.out.println("\tProcessing message received on "
								+ msg.getReceivedDate().toString() + ": "
								+ msg.getSubject());
					}
					applyRules(msg);
				}
				if (this.config.isVerbose()) {
					System.out.println();
				}
			} finally {
				if (folder.isOpen()) {
					folder.close(true); // expunge deleted or moved messages
				}
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Applies all defined rules to message <code>msg</code>.
	 * 
	 * @param msg
	 *            The message
	 * @throws MessagingException
	 */
	private void applyRules(Message msg) throws MessagingException {
		for (RuleDefinition rule : this.folderDef.getRules()) {
			RuleResult result = rule.getRule().apply(msg);
			if ((result != null) && (result.getIntents().size() > 0)) {
				if (this.config.isDryRun()) {
					System.out.println("\t\tRules for mail: "
							+ msg.getSubject() + ":");
					System.out.println(result.getIntentsString());
				} else {
					result.execute(msg, this.store);
				}
			}
		}
	}

	/**
	 * Searches the messages the rules should be applied to.
	 * 
	 * @param folder
	 *            The folder
	 * @return The messages
	 * @throws MessagingException
	 */
	private Message[] searchMessages(final Folder folder)
			throws MessagingException {
		return this.folderDef.getSearchFilter().search(folder);
	}

	/**
	 * Fetches message data for specified messages.
	 * <p>
	 * Currently envelope and headers are fetched.
	 * 
	 * 
	 * @param folder
	 *            The parent folder of msgs
	 * @param msgs
	 *            The messages
	 * @throws MessagingException
	 */
	private void fetchMessages(final Folder folder, final Message[] msgs)
			throws MessagingException {
		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfileItem.ENVELOPE);
		fp.add(FetchProfileItem.HEADERS);
		folder.fetch(msgs, fp);
	}
}
