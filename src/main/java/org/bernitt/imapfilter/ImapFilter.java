/**
 * 
 */
package org.bernitt.imapfilter;

import groovy.util.GroovyScriptEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.bernitt.imapfilter.config.Account;
import org.bernitt.imapfilter.config.Config;
import org.bernitt.imapfilter.config.ConfigParser;
import org.bernitt.imapfilter.config.FolderDefinition;
import org.bernitt.imapfilter.config.Password;
import org.bernitt.imapfilter.config.RuleDefinition;
import org.bernitt.imapfilter.filter.AccountFilter;

/**
 * Base class for a simple remote IMAP filter.
 * <p>
 * As to problems with sieve and many different mail clients in use I needed a
 * common way of filtering remote resources. This filter works in a simple and
 * comfortable way and allows to move/mark/delete using the JavaMail API.
 * <p>
 * Filters and configuration are read from {user.home}/.imapfilter/config.xml.
 * <p>
 * Filters are defined as groovy scripts (no "generic" filter definition needed)
 * 
 * @author fbe
 */
public class ImapFilter implements Runnable {

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ImapFilter(args).run();
	}

	private final String filterUrl = System.getProperty("user.home")
			+ "/.imapfilter/rules";

	private final String[] args;
	private final GroovyScriptEngine groovyEngine;

	public ImapFilter(String[] args) {
		this.args = args;

		try {
			this.groovyEngine = new GroovyScriptEngine(this.filterUrl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		Config cfg = new Config();
		parseCommandLine(cfg);

		if (cfg.isShowHelp()) {
			showHelp(cfg);
		}

		if (cfg.isExitBeforeFilter()) {
			return;
		}

		new ConfigParser().parse(cfg.getConfigFile(), cfg);

		initRules(cfg);

		// for (Account a : cfg.getAccounts()) {
		// System.out.println(a.toString());
		// }

		processAccounts(cfg);
	}

	/**
	 * Processes all accounts.
	 * 
	 * @param cfg
	 *            The config
	 */
	private void processAccounts(Config cfg) {
		for (Account account : cfg.getAccounts()) {
			new AccountFilter(account, cfg).run();
		}
	}

	/**
	 * Initializes all rules.
	 * 
	 * @param cfg
	 *            The config
	 */
	private void initRules(Config cfg) {
		for (Account a : cfg.getAccounts()) {
			for (FolderDefinition folderDef : a.getFolders()) {
				for (RuleDefinition ruleDef : folderDef.getRules()) {
					ruleDef.init(cfg, this.groovyEngine);
				}
			}
		}
	}

	/**
	 * Parses the command line.
	 * 
	 * @param cfg
	 *            The config
	 */
	private void parseCommandLine(Config cfg) {
		try {
			Options opts = new Options();
			opts.addOption("h", false, "show this help");
			opts.addOption("n", false, "dry run");
			opts.addOption("e", false, "encrypt password");
			opts.addOption("v", false, "verbose output");
			opts.addOption("c", true,
					"config file. Default is ~/.imapfilter/config.xml");

			CommandLineParser parser = new GnuParser();

			CommandLine cmd = parser.parse(opts, this.args);

			cfg.setOptions(opts);
			cfg.setCommandLine(cmd);

			if (cmd.hasOption('v')) {
				cfg.setVerbose(true);
			}
			if (cmd.hasOption('h')) {
				cfg.setShowHelp(true);
				cfg.setExitBeforeFilter(true);
			}
			if (cmd.hasOption('n')) {
				cfg.setDryRun(true);
			}

			if (cmd.hasOption('e')) {
				encryptPassword(cfg);
				cfg.setExitBeforeFilter(true);
			}
			if (cmd.hasOption('c')) {
				cfg.setConfigFile(new File(cmd.getOptionValue('c')));
			}

		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Shows the help.
	 * 
	 * @param cfg
	 *            The config
	 */
	private void showHelp(Config cfg) {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("imapfilter.sh [OPTIONS]", cfg.getOptions());
	}

	/**
	 * Helper method to create encryped passwords.
	 * 
	 * @param cfg
	 *            The config
	 */
	private void encryptPassword(Config cfg) {

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("The password: ");
			String password = in.readLine().trim();
			System.out.println();
			String enc = Password.encryptPassword(password);
			System.out.println("The encrypted password: " + enc);
			System.out.println("Testdecrypt: [" + Password.decryptPassword(enc)
					+ "]");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
