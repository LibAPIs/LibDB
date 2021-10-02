package com.mclarkdev.tools.libdb;

import java.util.HashMap;
import java.util.Map;

public class LibDB {

	private static final HashMap<LibDBType, Class<? extends LibDBAdapter>> adapters = new HashMap<>();

	public static void loadAdapter(LibDBType type, Class<? extends LibDBAdapter> adapter) {

		adapters.put(type, adapter);
	}

	public static LibDBAdapter newInstance(String jdbcString) {

		try {

			for (Map.Entry<LibDBType, Class<? extends LibDBAdapter>> entry : adapters.entrySet()) {
				if (!entry.getKey().matches(jdbcString)) {
					continue;
				}
				return entry.getValue().getConstructor(new Class[] { String.class }).newInstance(jdbcString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
