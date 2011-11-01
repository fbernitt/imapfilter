/**
 * 
 */
package org.bernitt.imapfilter.contrib;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

/**
 * Tests the java mail dir jar.
 * 
 * @author fbe
 */
public class JavaMailDirTest extends TestCase {

	public void testIt() throws Exception {
		MimeMessage mm = new MimeMessage((Session) null);
		mm.setText("hello\nworld\n");
		java.util.Properties p = new java.util.Properties();
		p.put("mail.store.maildir.autocreatedir", "true");
		Session session = Session.getDefaultInstance(p);
		Store store = session.getStore(new URLName("maildir:/tmp/Maildir"));
		store.connect();
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
		inbox.appendMessages(new Message[] { mm });
		inbox.close(true);
		store.close();
	}
}
