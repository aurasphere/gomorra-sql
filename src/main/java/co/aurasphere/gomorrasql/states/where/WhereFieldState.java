package co.aurasphere.gomorrasql.states.where;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.WhereCondition;
import co.aurasphere.gomorrasql.states.AbstractState;

/**
 * State for creating a new WHERE subclause in the format "field operator
 * value" by inserting the field name.
 * 
 * @author Donato Rimenti
 *
 */
public class WhereFieldState extends AbstractState {

	public WhereFieldState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		return new WhereOperatorState(queryInfo, new WhereCondition(token));
	}

}