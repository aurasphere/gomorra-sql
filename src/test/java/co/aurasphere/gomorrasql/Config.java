package co.aurasphere.gomorrasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
	
	static Connection getDbConnection() throws SQLException {
		String jdbcString = "jdbc:h2:mem:sample;INIT=RUNSCRIPT FROM 'classpath:init-db.sql'";
		return DriverManager.getConnection(jdbcString);
	}

}
