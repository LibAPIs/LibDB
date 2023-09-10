# LibDB

A simple helper library for managing connections and interactions with different database layers.

This library will allow you to create multiple database interfaces, allowing the user to choose which one to use.

## Maven Dependency

Include the library in your project by adding the following dependency to your pom.xml

```
<dependency>
	<groupId>com.mclarkdev.tools</groupId>
	<artifactId>libdb</artifactId>
	<version>1.5.1</version>
</dependency>
```
## Example

Create an adapter interface containing all the methods required.

```
public abstract class DBAdapter extends LibDBAdapter {

	public abstract void doSomething();
}
```

Implement your interface for each of the database types.

```
public class MySQLDataAdapter extends DBAdapter {

	public void doSomething() {
		// Interact with MySQL
	}
}

public class SQLiteDataAdapter extends DBAdapter {

	public void doSomething() {
		// Interact with SQLite
	}
}
```

Finally, tell the library about your adapters and pass it the connection string to get started.

```
LibDB.loadAdapter(LibDBType.MYSQL, MySQLDataAdapter.class);
LibDB.loadAdapter(LibDBType.SQLITE, SQLiteDataAdapter.class);

DBAdapter db = (DBAdapter) LibDB.newInstance(dbString);
```

# License

Open source & free for all. ❤
