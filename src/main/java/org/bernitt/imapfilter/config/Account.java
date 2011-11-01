/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author fbe
 * 
 */
public class Account {

	private String host;
	private String user;
	private String password;

	private List<FolderDefinition> folders;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the folders
	 */
	public List<FolderDefinition> getFolders() {
		return this.folders;
	}

	/**
	 * @param folders
	 *            the folders to set
	 */
	public void setFolders(List<FolderDefinition> folders) {
		this.folders = folders;
	}

	/**
	 * Returns <code>true</code> if at least one active rule exists.
	 * 
	 * @return <code>true</code> if at least one active rule exists
	 */
	public boolean hasActiveRules() {
		boolean result = false;

		for (FolderDefinition def : this.folders) {
			result = result || def.hasActiveRules();
		}

		return result;
	}
}
