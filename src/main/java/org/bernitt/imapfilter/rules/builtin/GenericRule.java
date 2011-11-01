/**
 * 
 */
package org.bernitt.imapfilter.rules.builtin;

import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.bernitt.imapfilter.rules.Rule;

/**
 * Generic base rule.
 * <p>
 * Allows to check for subject, fromAddress and fromPersonal.
 * 
 * @author fbe
 */
public abstract class GenericRule implements Rule {

	protected String subject;
	protected String fromPersonal;
	protected String fromAddress;

	protected Pattern subjectPattern;
	protected Pattern fromPersonalPattern;
	protected Pattern fromAddressPattern;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
		this.subjectPattern = Pattern.compile(subject);
	}

	/**
	 * @return the fromPersonal
	 */
	public String getFromPersonal() {
		return this.fromPersonal;
	}

	/**
	 * @param fromPersonal
	 *            the fromPersonal to set
	 */
	public void setFromPersonal(String fromPersonal) {
		this.fromPersonal = fromPersonal;
		this.fromPersonalPattern = Pattern.compile(fromPersonal);
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return this.fromAddress;
	}

	/**
	 * @param fromAddress
	 *            the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
		this.fromAddressPattern = Pattern.compile(fromAddress);
	}

	protected boolean checkConditions(Message msg) throws MessagingException {
		boolean result = true;

		result = result && checkSubject(msg);
		result = result && checkFromAddress(msg);
		result = result && checkFromPersonal(msg);

		return result;
	}

	protected boolean checkSubject(Message msg) throws MessagingException {
		if (this.subjectPattern != null) {
			return this.subjectPattern.matcher(msg.getSubject()).matches();
		} else {
			return true;
		}
	}

	protected boolean checkFromAddress(Message msg) throws MessagingException {
		if (this.fromAddressPattern != null) {
			return this.fromAddressPattern.matcher(
					extractFromAddress(msg).getAddress()).matches();
		} else {
			return true;
		}
	}

	protected boolean checkFromPersonal(Message msg) throws MessagingException {
		if (this.fromPersonalPattern != null) {
			return this.fromPersonalPattern.matcher(
					extractFromAddress(msg).getPersonal()).matches();
		} else {
			return true;
		}
	}

	protected InternetAddress extractFromAddress(Message msg)
			throws MessagingException {
		return (InternetAddress) msg.getFrom()[0];
	}
}
