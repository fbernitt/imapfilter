/**
 * 
 */
package org.bernitt.imapfilter.config;

import groovy.util.GroovyScriptEngine;

import org.bernitt.imapfilter.rules.Rule;

/**
 * Interface for rule definitions.
 * 
 * @author fbe
 */
public interface RuleDefinition {

	/**
	 * Returns the rule of this definition.
	 * 
	 * @return The rule
	 */
	public Rule getRule();

	/**
	 * Initializes this rule definition.
	 * 
	 * @param config
	 *            The configuration
	 * @param groovyEngine
	 *            The groovy script engine
	 */
	public void init(Config config, GroovyScriptEngine groovyEngine);
}
