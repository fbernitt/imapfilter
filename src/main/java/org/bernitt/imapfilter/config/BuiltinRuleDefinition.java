/**
 * 
 */
package org.bernitt.imapfilter.config;

import groovy.util.GroovyScriptEngine;

import org.bernitt.imapfilter.rules.Rule;

/**
 * Definition for builtin rules.
 * 
 * @author fbe
 */
public class BuiltinRuleDefinition implements RuleDefinition {

	private final Rule rule;

	public BuiltinRuleDefinition(final Rule rule) {
		this.rule = rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.RuleDefinition#getRule()
	 */
	public Rule getRule() {
		return this.rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.config.RuleDefinition#init(org.bernitt.imapfilter
	 * .config.Config, groovy.util.GroovyScriptEngine)
	 */
	public void init(Config config, GroovyScriptEngine groovyEngine) {
		// do nothing

	}

}
