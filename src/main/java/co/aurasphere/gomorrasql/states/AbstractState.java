package co.aurasphere.gomorrasql.states;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

public abstract class AbstractState {
	
	protected QueryInfo queryInfo;
	
	public AbstractState(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}
	
	public abstract AbstractState transitionToNextState(String token) throws CaggiaFaException;

	public boolean isFinalState() {
		return false;
	}

	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[queryInfo=" + queryInfo + "]";
	}
	
}