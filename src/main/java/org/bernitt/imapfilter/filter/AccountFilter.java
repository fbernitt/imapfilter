/**
 * 
 */
package org.bernitt.imapfilter.filter;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.bernitt.imapfilter.config.Account;
import org.bernitt.imapfilter.config.Config;
import org.bernitt.imapfilter.config.FolderDefinition;
import org.bernitt.imapfilter.config.Password;

/**
 * Iterates all folders and applies their defined rules.
 * 
 * @author fbe
 */
public class AccountFilter implements Runnable {

	private final Account account;
	private final Config config;

	public AccountFilter(final Account account, final Config config) {
		this.account = account;
		this.config = config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		if (!this.account.hasActiveRules()) { // skip accounts without active
			// rules!
			if (this.config.isVerbose()) {
				System.out.println("Skipping account " + this.account.getHost()
						+ " because no active rules exist!");
			}
			return;
		}

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		try {
			Store store = session.getStore("imaps");
			try {
				store.connect(this.account.getHost(), this.account.getUser(),
						Password.decryptPassword(this.account.getPassword()));

				for (FolderDefinition def : this.account.getFolders()) {
					new FolderFilter(def, store, this.config).run();
				}
			} finally {
				if (store.isConnected()) {
					store.close();
				}
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
