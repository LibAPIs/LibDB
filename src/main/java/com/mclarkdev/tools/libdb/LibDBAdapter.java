package com.mclarkdev.tools.libdb;

import java.sql.Connection;
import java.sql.SQLException;

import com.mclarkdev.tools.libobjectpooler.LibObjectPooler;
import com.mclarkdev.tools.libobjectpooler.LibObjectPoolerController;

public abstract class LibDBAdapter implements LibObjectPoolerController<Connection> {

	private final String dbString;

	private LibObjectPooler<Connection> pooler;

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

	public String getDBString() {
		return dbString;
	}

	public LibObjectPooler<Connection> getPooler() {
		return pooler;
	}

	public abstract void initSchema() throws SQLException;
}
