package co.aurasphere.gomorrasql.states.where;

import java.util.Arrays;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.states.AbstractState;

public class WhereJoinState extends AbstractState {

	public WhereJoinState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (token.equalsIgnoreCase(Keywords.AND_KEYWORD)) {
			queryInfo.addWhereConditionsJoinOperator("AND");
			return new WhereFieldState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.OR_KEYWORD)) {
			queryInfo.addWhereConditionsJoinOperator("OR");
			return new WhereFieldState(queryInfo);
		}
		throw new CaggiaFaException(Arrays.asList(Keywords.AND_KEYWORD, Keywords.OR_KEYWORD), token);
	}
	
	@Override
	public boolean isFinalState() {
		return true;
	}

}