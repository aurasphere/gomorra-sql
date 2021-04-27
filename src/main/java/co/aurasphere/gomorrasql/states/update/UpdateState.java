package co.aurasphere.gomorrasql.states.update;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.states.AbstractState;

public class UpdateState extends AbstractState {

	public UpdateState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFinalState() {
		// TODO Auto-generated method stub
		return false;
	}

}
