package co.aurasphere.gomorrasql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.aurasphere.gomorrasql.model.GomorraSqlQueryResult;

public class TestSelect {

	private final static String[] orderedCityColumns = { "MILANO", "ROMA", "NAPOLI", "NEW YORK" };

	private Connection connection;

	@Before
	public void setup() throws SQLException {
		this.connection = Config.getDbConnection();
	}

	@After
	public void teardown() throws SQLException {
		this.connection.close();
	}

	@Test
	public void testSelectAsterisk() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city");
		ResultSet resultSet = result.getResultSet();
		int expectedId = 1;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String city = resultSet.getString(2);
			Assert.assertEquals(expectedId, id);
			Assert.assertEquals(orderedCityColumns[expectedId - 1], city);
			expectedId++;
		}
		Assert.assertEquals(5, expectedId);
	}

	@Test
	public void testSelectWhere() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi
				.execute("ripigliammo tutto chillo ch'era 'o nuostro mmiez 'a city arò city_id < 3");
		ResultSet resultSet = result.getResultSet();
		int expectedId = 1;
		while (resultSet.next()) {
			int id = resultSet.getInt(1);
			String city = resultSet.getString(2);
			Assert.assertEquals(expectedId, id);
			Assert.assertEquals(orderedCityColumns[expectedId - 1], city);
			expectedId++;
		}
		Assert.assertEquals(3, expectedId);
	}

	@Test
	public void testSelectWhereAnd() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi
				.execute("ripigliammo first_name, last_name mmiez 'a user arò address è nisciun e fess è true");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		while (resultSet.next()) {
			String name = resultSet.getString(1);
			String surname = resultSet.getString(2);
			Assert.assertEquals("JOHN", name);
			Assert.assertEquals("DOE", surname);
			counter++;
		}
		Assert.assertEquals(2, counter);
	}

	@Test
	public void testSelectWhereOr() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi
				.execute("ripigliammo first_name, last_name mmiez 'a user arò address è nisciun o fess è true");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		String[] names = { "PINCO", "JOHN" };
		String[] surnames = { "PALLINO", "DOE" };
		while (resultSet.next()) {
			String name = resultSet.getString(1);
			String surname = resultSet.getString(2);
			Assert.assertEquals(names[counter - 1], name);
			Assert.assertEquals(surnames[counter - 1], surname);
			counter++;
		}
		Assert.assertEquals(3, counter);
	}

	@Test
	public void testSelectJoin() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi.execute(
				"ripigliammo first_name, city_name mmiez 'a user pesc e pesc city arò fess è true e fk_city_id = city_id");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		String[] names = { "PINCO", "JOHN" };
		String[] cities = { "MILANO", "NEW YORK" };
		while (resultSet.next()) {
			String name = resultSet.getString(1);
			String city = resultSet.getString(2);
			Assert.assertEquals(names[counter - 1], name);
			Assert.assertEquals(cities[counter - 1], city);
			counter++;
		}
		Assert.assertEquals(3, counter);
	}

	@Test
	public void testSelectWhereIsNot() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi.execute("ripigliammo first_name mmiez 'a user arò address nun è nisciun");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		String[] names = { "PINCO", "PAOLINO", "FRED" };
		while (resultSet.next()) {
			String name = resultSet.getString(1);
			Assert.assertEquals(names[counter - 1], name);
			counter++;
		}
		Assert.assertEquals(4, counter);
	}

        @Test
	public void testSelectAs() throws SQLException {
		GomorraSqlInterpreter gsi = new GomorraSqlInterpreter(connection);
		GomorraSqlQueryResult result = gsi.execute("ripigliammo first_name cumme nome, last_name cumme cognome mmiez 'a user arò address nun è nisciun");
		ResultSet resultSet = result.getResultSet();
		int counter = 1;
		String[] names = { "PINCO", "PAOLINO", "FRED" };
		while (resultSet.next()) {
			String name = resultSet.getString("nome");
			Assert.assertEquals(names[counter - 1], name);
			counter++;
		}
		Assert.assertEquals(4, counter);      
	}

}