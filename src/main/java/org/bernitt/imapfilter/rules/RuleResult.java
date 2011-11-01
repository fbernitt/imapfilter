/**
 * 
 */
package org.bernitt.imapfilter.rules;

import java.util.Arrays;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.bernitt.imapfilter.rules.intents.Intent;

/**
 * Defines the rule result which consists of a list of Intents.
 * 
 * @author fbe
 */
public class RuleResult {

	private final List<Intent> intents;

	public RuleResult(List<Intent> intents) {
		this.intents = intents;
	}

	public RuleResult(Intent... intents) {
		this(Arrays.asList(intents));
	}

	/**
	 * Executes the result for message msg and returns <code>true</code> on
	 * success.
	 * 
	 * @param msg
	 *            The message
	 * @return <code>true</code> on success
	 */
	public boolean execute(Message msg, Store store) throws MessagingException {
		boolean result = true;

		for (int i = 0; result && (i < this.intents.size()); i++) {
			result = result && this.intents.get(i).execute(msg, store);
		}

		return result;
	}

	/**
	 * Returns the intents of this result. Each string has to be a one line
	 * string.
	 * 
	 * @return The intents
	 */
	public List<Intent> getIntents() {
		return this.intents;
	}

	public String getIntentsString() {
		StringBuilder b = new StringBuilder();

		for (Intent i : this.intents) {
			b.append("\t\t\t" + i.toString());
			b.append("\n");
		}

		return b.toString();
	}
}
