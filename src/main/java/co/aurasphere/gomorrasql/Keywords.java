package co.aurasphere.gomorrasql;

import java.util.Arrays;
import java.util.List;

/**
 * Keywords used by GomorraSQL.
 * 
 * @author Donato Rimenti
 *
 */
public class Keywords {

	public static final String SELECT_KEYWORD = "ripigliammo";
	public static final String UPDATE_KEYWORD = "rifacimm";
	public static final String[] INSERT_KEYWORDS = { "nzipp", "'ngoppa" };
	public static final String[] DELETE_KEYWORDS = { "facimm", "na'", "strage" };
	public static final String[] JOIN_KEYWORDS = { "pesc", "e", "pesc" };
	public static final String[] FROM_KEYWORDS = { "mmiez", "'a" };
	public static final String[] ASTERISK_KEYWORDS = { "tutto", "chillo", "ch'era", "'o", "nuostro" };
	public static final String WHERE_KEYWORD = "arò";
	public static final String[] BEGIN_TRANSACTION_KEYWORDS = { "ua", "uagliò" };
	public static final String[] COMMIT_KEYWORDS = { "iamme", "bello", "ia'" };
	public static final String ROLLBACK_KEYWORD = "sfaccimm";
	public static final String AND_KEYWORD = "e";
	public static final String OR_KEYWORD = "o";
	public static final String NULL_KEYWORD = "nisciun";
	public static final String IS_KEYWORD = "è";
	public static final String VALUES_KEYWORD = "chist";
	public static final String[] IS_NOT_KEYWORDS = { "nun", "è" };
	public static final String SET_KEYWORD = "accunza";
	public static final List<String> WHERE_OPERATORS = Arrays.asList(">", "<", "=", "!=", "<>", ">=", "<=",
			Keywords.IS_KEYWORD, Keywords.IS_NOT_KEYWORDS[0]);
	public static final String SET_EQUAL_KEYWORD = "accussì";
	public static final String LIMIT_KEYWORD = "è pccirill ashpiett";
	public static final String AS_KEYWORD = "cumme";
}