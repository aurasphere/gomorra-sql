package co.aurasphere.gomorrasql.states.query;

import java.util.Arrays;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.AnyTokenConsumerState;
import co.aurasphere.gomorrasql.states.SingleTokenMatchState;
import co.aurasphere.gomorrasql.states.where.WhereFieldState;

/**
 * State for an update when the first value is set. Allows to continue setting
 * values or moving on to an optional where clause.
 * 
 * @author Donato Rimenti
 *
 */
public class UpdateSetState extends AbstractState {

	public UpdateSetState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		// Adds another variable
		if (token.equalsIgnoreCase(",")) {
			return new AnyTokenConsumerState(queryInfo, queryInfo::addColumnName,
					q -> new SingleTokenMatchState(q, Keywords.SET_EQUAL_KEYWORD,
							q2 -> new AnyTokenConsumerState(q2, q2::addValue, UpdateSetState::new)));
		}
		// Moves to a where clause
		if (token.equalsIgnoreCase(Keywords.WHERE_KEYWORD)) {
			return new WhereFieldState(queryInfo);
		}
		throw new CaggiaFaException(Arrays.asList(",", Keywords.WHERE_KEYWORD, "%END_OF_QUERY%"), token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}