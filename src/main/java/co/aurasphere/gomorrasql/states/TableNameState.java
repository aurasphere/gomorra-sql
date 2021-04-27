package co.aurasphere.gomorrasql.states;

import java.util.function.Function;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

public class TableNameState extends AbstractState {

	private Function<QueryInfo, AbstractState> stateTransitionFunction;

	public TableNameState(QueryInfo queryInfo, Function<QueryInfo, AbstractState> stateTransitionFunction) {
		super(queryInfo);
		this.stateTransitionFunction = stateTransitionFunction;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		queryInfo.setTableName(token);
		return stateTransitionFunction.apply(queryInfo);
	}
}