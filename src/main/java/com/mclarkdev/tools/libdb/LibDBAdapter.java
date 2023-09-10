package com.mclarkdev.tools.libdb;

import java.sql.Connection;
import java.sql.SQLException;

import com.mclarkdev.tools.libobjectpooler.LibObjectPooler;
import com.mclarkdev.tools.libobjectpooler.LibObjectPoolerController;

/**
 * LibDB // LibDBAdapter
 */
public abstract class LibDBAdapter implements LibObjectPoolerController<Connection> {

	private final String dbString;

	private LibObjectPooler<Connection> pooler;

	/**
	 * Instantiate a new adapter with the given connection string.
	 * 
	 * @param dbString database connection string
	 */
	protected LibDBAdapter(String dbString) {

		this.dbString = dbString;
		this.pooler = new LibObjectPooler<Connection>(3, this);
		try {
			this.initSchema();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("failed to initialize database");
		}
	}

	/**
	 * Returns the connection string used for the adapter.
	 * 
	 * @return database connection string
	 */
	public String getDBString() {
		return dbString;
	}

	/**
	 * Returns the underlying object pooler containing the objects.
	 * 
	 * @return connection pool
	 */
	public LibObjectPooler<Connection> getPooler() {
		return pooler;
	}

	/**
	 * Called on startup to initialize the database.
	 * 
	 * @throws SQLException
	 */
	public abstract void initSchema() throws SQLException;
}
