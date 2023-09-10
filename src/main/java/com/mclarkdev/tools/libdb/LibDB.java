package com.mclarkdev.tools.libdb;

import java.util.HashMap;
import java.util.Map;

/**
 * LibDB // LibDB
 * 
 * A simple helper library for managing connections and interactions with
 * different database layers.
 */
public class LibDB {

	private static final HashMap<LibDBType, Class<? extends LibDBAdapter>> adapters = new HashMap<>();

	/**
	 * Disallow instantiation.
	 */
	private LibDB() {
	}

	/**
	 * Load an adapter for the given database type.
	 * 
	 * Load multiple adapters, the best option will be chosen at runtime based on
	 * the given JDBC connection string.
	 * 
	 * @param type
	 * @param adapter
	 */
	public static void loadAdapter(LibDBType type, Class<? extends LibDBAdapter> adapter) {

		adapters.put(type, adapter);
	}

	/**
	 * Create a new database connection adapter with the given JDBC string.
	 * 
	 * @param jdbcString database connection string
	 * @return the LibDBAdapter interface
	 */
	public static LibDBAdapter newInstance(String jdbcString) {

		try {

			// Loop each of the configured adapters
			for (Map.Entry<LibDBType, Class<? extends LibDBAdapter>> entry : adapters.entrySet()) {

				// Skip if not matching adapter string
				if (!entry.getKey().matches(jdbcString)) {
					continue;
				}

				// Create and return new instance of the adapter object
				return entry.getValue().getConstructor(String.class).newInstance(jdbcString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
