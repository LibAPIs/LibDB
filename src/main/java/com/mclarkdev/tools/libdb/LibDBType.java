package com.mclarkdev.tools.libdb;

/**
 * LibDB // LibDBType
 * 
 * @author Matthew R. Clark (MClarkDev.com, 2021-2026)
 */
public enum LibDBType {

	MYSQL("jdbc:mysql://.*"),

	SQLITE("jdbc:sqlite:.*");

	private final String format;

	LibDBType(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	public boolean matches(String jdbcString) {
		return jdbcString.matches(format);
	}
}
