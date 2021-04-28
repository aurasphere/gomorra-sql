package co.aurasphere.gomorrasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import co.aurasphere.gomorrasql.model.GomorraSqlQueryResult;

public class GomorraSqlShell {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Insert a JDBC string to connect to a database: ");
		String url = scanner.nextLine();
		Connection connection = DriverManager.getConnection(url);
		GomorraSqlInterpreter gomorraSqlParser = new GomorraSqlInterpreter(connection);

		System.out.println("Succesfully connected to DB");
		while (true) {
			System.out.print("> ");
			String query = scanner.nextLine();
			GomorraSqlQueryResult result = gomorraSqlParser.execute(query);
			if (result.getResultSet() != null) {
				printSelectResult(result.getResultSet());
			}
			if (result.getAffectedRows() != null) {
				System.out.println("Affected rows: " + result.getAffectedRows());
			}
			if (result.getResultSet() == null && result.getAffectedRows() == null) {
				System.out.println("OK");
			}
		}
	}

	private static void printSelectResult(ResultSet result) throws SQLException {
		ResultSetMetaData rsmd = result.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		for (int i = 1; i <= columnsNumber; i++) {
			System.out.print(rsmd.getColumnName(i) + " | ");
		}
		System.out.println();
		while (result.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print("  |");
				System.out.print(result.getString(i) + " | ");
			}
			System.out.println();
		}
	}

}