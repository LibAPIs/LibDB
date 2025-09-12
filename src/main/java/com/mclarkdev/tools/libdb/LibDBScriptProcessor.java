package com.mclarkdev.tools.libdb;

import java.util.ArrayList;

public class LibDBScriptProcessor {

	public static String[] parseFile(String blob) {

		ArrayList<String> queries = new ArrayList<>();

		// loop each line
		String query = "";
		String blobLines[] = blob.split("\n");
		for (String queryLine : blobLines) {

			// ignore empty lines
			if (queryLine.length() == 0) {
				continue;
			}

			// ignore anything after a comment mark
			if (queryLine.contains("#")) {

				queryLine = queryLine.substring(0, queryLine.indexOf("#"));
			}

			// break at the end of a command
			if (queryLine.contains(";;")) {

				// add everything before the terminator
				query += queryLine.substring(0, queryLine.indexOf(";;") + 1);

				// add to the list
				queries.add(query);

				// begin parsing next query
				query = queryLine.substring(queryLine.indexOf(";;") + 2, queryLine.length());
			} else {

				// or add to the command string
				query += queryLine + "\n";
			}
		}

		// return an array of queries
		return queries.toArray(new String[queries.size()]);
	}
}
