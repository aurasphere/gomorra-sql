package co.aurasphere.gomorrasql.model;

import java.util.List;

/**
 * Exception thrown by GomorraSQL when an error occurs during query
 * parsing/execution.
 * 
 * @author Donato Rimenti
 *
 */
public class CaggiaFaException extends RuntimeException {

	public CaggiaFaException(List<String> expectedTokens, String actualToken) {
		super("Expected one of " + expectedTokens + " but got [" + actualToken + "]");
	}

	public CaggiaFaException(String expectedToken, String token) {
		super("Expected [" + expectedToken + "] but got [" + token + "]");
	}

	public CaggiaFaException(String message) {
		super(message);
	}

	public CaggiaFaException(Exception e) {
		super(e);
	}

}
