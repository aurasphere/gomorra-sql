package co.aurasphere.gomorrasql.states;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

public class CommaSeparedValuesState extends AbstractState {

	private boolean lastWasComma = false;
	private List<String> collector;
	private String nextToken;
	private Function<QueryInfo, AbstractState> transitionFunction;
	private String expectedToken;
	private boolean canBeFinalState = false;

	public CommaSeparedValuesState(QueryInfo queryInfo, List<String> collector, String nextToken, String expectedToken,
			Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);
		this.collector = collector;
		this.nextToken = nextToken;
		this.transitionFunction = transitionFunction;
		this.expectedToken = expectedToken;
	}

	public CommaSeparedValuesState(QueryInfo queryInfo, List<String> collector, String nextToken, String expectedToken,
			Function<QueryInfo, AbstractState> transitionFunction, boolean lastWasComma) {
		this(queryInfo, collector, nextToken, expectedToken, transitionFunction);
		
		// Used when the first token is not consumed by the previous state.
		this.lastWasComma = lastWasComma;
		this.canBeFinalState = true;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (token.equals(",")) {
			if (lastWasComma) {
				// Case ", ,"
				throw new CaggiaFaException(expectedToken, token);
			} else {
				// Case "%expectedToken% ,"
				lastWasComma = true;
				return this;
			}
		}

		// Case ", %expectedToken%"
		if (lastWasComma) {
			collector.add(token);
			lastWasComma = false;
			return this;
		}

		// Case "%expectedToken% %nextToken%"
		if (token.equalsIgnoreCase(nextToken)) {
			return transitionFunction.apply(queryInfo);
		}

		// Case "%expectedToken% %WRONG_TOKEN%"
		throw new CaggiaFaException(Arrays.asList(",", nextToken), token);
	}

	@Override
	public boolean isFinalState() {
		return canBeFinalState;
	}
}