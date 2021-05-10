package co.aurasphere.gomorrasql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

import org.junit.Test;

/**
 * SQLTokenizer used by GomorraSQL.
 *
 * @author Matteo Baccan
 *
 */
public class TestTokenizer {

    @Test
    public void testTokenizer() throws SQLException {
        String query = "rifacimm user accunza name accussì \"Pinco\"";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("rifacimm");
        expectedResult.add("user");
        expectedResult.add("accunza");
        expectedResult.add("name");
        expectedResult.add("accussì");
        expectedResult.add("\"Pinco\"");

        List<String> result = SQLTokenizer.tokenize(query);

        Assert.assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testTokenizerWithSpace() throws SQLException {
        String query = "rifacimm user accunza name accussì \"Pinco Pallo\"";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("rifacimm");
        expectedResult.add("user");
        expectedResult.add("accunza");
        expectedResult.add("name");
        expectedResult.add("accussì");
        expectedResult.add("\"Pinco Pallo\"");

        List<String> result = SQLTokenizer.tokenize(query);

        Assert.assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testTokenizerWithComma() throws SQLException {
        String query = "rifacimm user accunza name accussì \"Pinco,Pallo\"";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("rifacimm");
        expectedResult.add("user");
        expectedResult.add("accunza");
        expectedResult.add("name");
        expectedResult.add("accussì");
        expectedResult.add("\"Pinco,Pallo\"");

        List<String> result = SQLTokenizer.tokenize(query);

        Assert.assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testTokenizerWithCommaAndSpaces() throws SQLException {
        String query = "  rifacimm   user accunza  name accussì \"Pinco ,  Pallo\"";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("rifacimm");
        expectedResult.add("user");
        expectedResult.add("accunza");
        expectedResult.add("name");
        expectedResult.add("accussì");
        expectedResult.add("\"Pinco ,  Pallo\"");

        List<String> result = SQLTokenizer.tokenize(query);

        Assert.assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

    @Test
    public void testTokenizerWithoutLastQuote() throws SQLException {
        String query = "  rifacimm   user accunza  name accussì \"Pinco ,  Pallo";

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("rifacimm");
        expectedResult.add("user");
        expectedResult.add("accunza");
        expectedResult.add("name");
        expectedResult.add("accussì");
        expectedResult.add("\"Pinco ,  Pallo");

        List<String> result = SQLTokenizer.tokenize(query);

        Assert.assertArrayEquals(expectedResult.toArray(), result.toArray());
    }

}
