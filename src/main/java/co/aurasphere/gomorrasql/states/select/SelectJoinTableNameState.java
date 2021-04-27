package co.aurasphere.gomorrasql.states.select;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.where.OptionalWhereState;

public class SelectJoinTableNameState extends AbstractState {

	public SelectJoinTableNameState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		queryInfo.addJoinedTable(token);
		return new OptionalWhereState(queryInfo);
	}
}