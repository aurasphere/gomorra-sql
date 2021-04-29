package co.aurasphere.gomorrasql.states;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

/**
 * State that represents the end of a query.
 * 
 * @author Donato Rimenti
 *
 */
public class FinalState extends AbstractState {

	public FinalState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		throw new CaggiaFaException("%END_OF_QUERY%", token);
	}

	@Override
	public boolean isFinalState() {
		return true;
	}

}