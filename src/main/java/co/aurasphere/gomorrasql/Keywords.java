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

	public final static String SELECT_KEYWORD = "ripigliammo";
	public final static String UPDATE_KEYWORD = "rifacimm";
	public final static String[] INSERT_KEYWORDS = { "nzipp", "'ngoppa" };
	public static final String[] DELETE_KEYWORDS = { "facimm", "na'", "strage" };
	public static final String[] JOIN_KEYWORDS = { "pesc", "e", "pesc" };
	public static final String[] FROM_KEYWORDS = { "mmiez", "'a" };
	public final static String[] ASTERISK_KEYWORDS = { "tutto", "chillo", "ch'era", "'o", "nuostro" };
	public final static String WHERE_KEYWORD = "ar�";
	public final static String[] BEGIN_TRANSACTION_KEYWORDS = { "deux", "fritures" };
	public final static String[] COMMIT_KEYWORDS = { "biv", "aggià", "capì" , "si", "me", "pozz", "fidà", "e", "te" };
	public final static String ROLLBACK_KEYWORD = "struuunz";
	public final static String AND_KEYWORD = "e";
	public final static String OR_KEYWORD = "o";
	public final static String NULL_KEYWORD = "nisciun";
	public final static String IS_KEYWORD = "�";
	public final static String VALUES_KEYWORD = "chist";
	public final static String[] IS_NOT_KEYWORDS = { "nun", "�" };
	public final static String SET_KEYWORD = "accunza";
	public final static List<String> WHERE_OPERATORS = Arrays.asList(">", "<", "=", "!=", "<>", ">=", "<=",
			Keywords.IS_KEYWORD, Keywords.IS_NOT_KEYWORDS[0]);
	public final static String SET_EQUAL_KEYWORD = "accuss�";
}