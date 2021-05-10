package co.aurasphere.gomorrasql;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matteo
 */
public final class SQLTokenizer {

    /**
     * Private constructor for utility class.
     */
    private SQLTokenizer() {

    }

    /**
     * Tokenize the input query in single usable tokens.
     *
     * @param query the query to tokenize
     * @return the arrya list of String tokens
     */
    static List<String> tokenize(final String query) {
        List<String> result = new ArrayList<>();

        boolean quote = false;
        StringBuilder currentToken = new StringBuilder();
        for (int n = 0; n < query.length(); n++) {
            char chr = query.charAt(n);

            // Quote check
            if (!quote && chr == '"') {
                quote = true;
            } else if (quote && chr == '"') {
                quote = false;
            }

            if (quote) {
                currentToken.append(chr);
            } else if (chr == ' ' || chr == ',') {
                if (currentToken.length() > 0) {
                    result.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }
                if (chr == ',') {
                    result.add("" + chr);
                }
            } else {
                currentToken.append(chr);
            }
        }
        if (currentToken.length() > 0) {
            result.add(currentToken.toString());
        }

        return result;
    }
}
