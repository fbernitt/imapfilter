/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.io.File;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Base configuration for this imap filter.
 * 
 * @author fbe
 */
public class Config {

	private boolean showHelp;
	private boolean dryRun;
	private boolean exitBeforeFilter;
	private boolean verbose;
	private Options options;
	private CommandLine commandLine;
	private List<Account> accounts;
	private File configFile = new File(System.getProperty("user.home")
			+ "/.imapfilter/config.xml");

	/**
	 * @return the showHelp
	 */
	public boolean isShowHelp() {
		return this.showHelp;
	}

	/**
	 * @param showHelp
	 *            the showHelp to set
	 */
	public void setShowHelp(boolean showHelp) {
		this.showHelp = showHelp;
	}

	/**
	 * @return the dryRun
	 */
	public boolean isDryRun() {
		return this.dryRun;
	}

	/**
	 * @param dryRun
	 *            the dryRun to set
	 */
	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	/**
	 * @return the options
	 */
	public Options getOptions() {
		return this.options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

	/**
	 * @return the commandLine
	 */
	public CommandLine getCommandLine() {
		return this.commandLine;
	}

	/**
	 * @param commandLine
	 *            the commandLine to set
	 */
	public void setCommandLine(CommandLine commandLine) {
		this.commandLine = commandLine;
	}

	/**
	 * @return the exitBeforeFilter
	 */
	public boolean isExitBeforeFilter() {
		return this.exitBeforeFilter;
	}

	/**
	 * @param exitBeforeFilter
	 *            the exitBeforeFilter to set
	 */
	public void setExitBeforeFilter(boolean exitBeforeFilter) {
		this.exitBeforeFilter = exitBeforeFilter;
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return this.accounts;
	}

	/**
	 * @param accounts
	 *            the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return this.verbose;
	}

	/**
	 * @param verbose
	 *            the verbose to set
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * @return the configFile
	 */
	public File getConfigFile() {
		return this.configFile;
	}

	/**
	 * @param configFile
	 *            the configFile to set
	 */
	public void setConfigFile(File configFile) {
		this.configFile = configFile;
	}

}
