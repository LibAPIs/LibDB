package com.mclarkdev.tools.libdb;

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
