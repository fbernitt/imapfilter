/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.digester.Digester;
import org.bernitt.imapfilter.config.search.SearchFilter;
import org.bernitt.imapfilter.config.search.SearchFilterConverter;
import org.xml.sax.SAXException;

/**
 * Parses the configuration using a {@link Digester}.
 * 
 * @author fbe
 */
public class ConfigParser {

	static {
		ConvertUtils.register(new SearchFilterConverter(), SearchFilter.class);
	}

	@SuppressWarnings("unchecked")
	public void parse(File file, Config cfg) {
		Digester d = createDigester();
		try {
			cfg.setAccounts((List<Account>) d.parse(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	private Digester createDigester() {
		Digester d = new Digester();

		d.addObjectCreate("configuration/accounts", ArrayList.class);
		d.addObjectCreate("configuration/accounts/account", Account.class);
		d.addSetProperties("configuration/accounts/account");

		d.addObjectCreate("configuration/accounts/account/folders",
				ArrayList.class);
		d.addObjectCreate("configuration/accounts/account/folders/folder",
				FolderDefinition.class);
		d.addSetProperties("configuration/accounts/account/folders/folder");

		d.addObjectCreate(
				"configuration/accounts/account/folders/folder/rules",
				ArrayList.class);

		d.addFactoryCreate(
				"configuration/accounts/account/folders/folder/rules/groovy",
				new GroovyRuleObjectCreationFactory());
		// d.addObjectCreate(
		// "configuration/accounts/account/folders/folder/rules/groovy",
		// GroovyRuleDefinition.class);
		//
		// d
		// .addSetProperties("configuration/accounts/account/folders/folder/rules/groovy");

		d.addSetNext(
				"configuration/accounts/account/folders/folder/rules/groovy",
				"add");

		d.addFactoryCreate(
				"configuration/accounts/account/folders/folder/rules/rule",
				new BuiltinRuleObjectCreationFactory());
		d.addSetNext(
				"configuration/accounts/account/folders/folder/rules/rule",
				"add");

		d.addSetNext("configuration/accounts/account/folders/folder/rules",
				"setRules");

		d.addSetNext("configuration/accounts/account/folders/folder", "add");

		d.addSetNext("configuration/accounts/account/folders", "setFolders");

		d.addSetNext("configuration/accounts/account", "add");

		return d;
	}
}
