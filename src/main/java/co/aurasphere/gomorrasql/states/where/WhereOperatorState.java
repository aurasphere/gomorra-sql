package co.aurasphere.gomorrasql.states.where;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.WhereCondition;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.GreedyMatchKeywordState;

/**
 * State for continuing the last WHERE subclause in the format "field operator
 * value" by inserting the operator.
 * 
 * @author Donato Rimenti
 *
 */
public class WhereOperatorState extends AbstractState {

	private WhereCondition condition;

	public WhereOperatorState(QueryInfo queryInfo, WhereCondition condition) {
		super(queryInfo);
		this.condition = condition;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (Keywords.WHERE_OPERATORS.contains(token) || token.equalsIgnoreCase(Keywords.IS_KEYWORD)) {
			if (token.equalsIgnoreCase(Keywords.IS_NOT_KEYWORDS[0])) {
				condition.setOperator("IS NOT");
				return new GreedyMatchKeywordState(queryInfo, Keywords.IS_NOT_KEYWORDS, q -> new WhereValueState(q, condition));
			}
			if (token.equalsIgnoreCase(Keywords.IS_KEYWORD)) {
				condition.setOperator("IS");
			} else {
				condition.setOperator(token);
			}
			return new WhereValueState(queryInfo, condition);
		}
		throw new CaggiaFaException(Keywords.WHERE_OPERATORS, token);
	}

}