/**
 * 
 */
package org.bernitt.imapfilter.config;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.bernitt.imapfilter.rules.builtin.BackupRule;
import org.bernitt.imapfilter.rules.builtin.MoveRule;
import org.xml.sax.Attributes;

/**
 * Object creation factory for builtin rules.
 * 
 * 
 * @author fbe
 */
public class BuiltinRuleObjectCreationFactory extends
		AbstractObjectCreationFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.digester.AbstractObjectCreationFactory#createObject
	 * (org.xml.sax.Attributes)
	 */
	@Override
	public Object createObject(Attributes attributes) throws Exception {
		String name = attributes.getValue("name");

		BuiltinRuleDefinition result;

		if ("move".equals(name)) {
			result = new BuiltinRuleDefinition(new MoveRule());
		} else if ("backup".equals(name)) {
			result = new BuiltinRuleDefinition(new BackupRule());
		} else {
			throw new IllegalArgumentException("Unknown rule with name: "
					+ name);
		}

		for (int i = 0; i < attributes.getLength(); i++) {
			String key = attributes.getLocalName(i);
			if (!"name".equals(key)) {
				BeanUtils.setProperty(result.getRule(), key, attributes
						.getValue(i));
			}
		}

		return result;
	}

}
