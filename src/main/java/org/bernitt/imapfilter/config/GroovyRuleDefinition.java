/**
 * 
 */
package org.bernitt.imapfilter.config;

import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.bernitt.imapfilter.rules.Rule;

/**
 * @author fbe
 * 
 */
public class GroovyRuleDefinition implements RuleDefinition {

	private String name;
	private Rule rule;
	private Map<String, String> args;

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
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bernitt.imapfilter.config.RuleDefinition#init(org.bernitt.imapfilter
	 * .config.Config, groovy.util.GroovyScriptEngine)
	 */
	public void init(Config config, GroovyScriptEngine groovyEngine) {
		try {
			this.rule = (Rule) groovyEngine.loadScriptByName(this.name)
					.newInstance();
			propagateArgs();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ResourceException e) {
			throw new RuntimeException(e);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bernitt.imapfilter.config.RuleDefinition#getRule()
	 */
	public Rule getRule() {
		return this.rule;
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Map<String, String> args) {
		this.args = args;
	}

	private void propagateArgs() {
		if (this.args == null) {
			return;
		}

		try {
			for (String name : this.args.keySet()) {
				BeanUtils.setProperty(this.rule, name, this.args.get(name));
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}

	}
}
