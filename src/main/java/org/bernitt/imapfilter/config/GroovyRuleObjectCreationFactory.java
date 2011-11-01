/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.util.HashMap;

import org.apache.commons.digester.AbstractObjectCreationFactory;
import org.xml.sax.Attributes;

/**
 * @author fbe
 * 
 */
public class GroovyRuleObjectCreationFactory extends
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
		GroovyRuleDefinition result = new GroovyRuleDefinition();

		HashMap<String, String> args = new HashMap<String, String>();
		for (int i = 0; i < attributes.getLength(); i++) {
			if ("name".equals(attributes.getLocalName(i))) {
				result.setName(attributes.getValue(i));
			} else {
				args.put(attributes.getLocalName(i), attributes.getValue(i));
			}
		}
		if (args.size() > 0) {
			result.setArgs(args);
		}

		return result;
	}

}
