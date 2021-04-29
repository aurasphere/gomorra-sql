package co.aurasphere.gomorrasql.states.query;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.states.AbstractState;
import co.aurasphere.gomorrasql.states.AnyTokenConsumerState;
import co.aurasphere.gomorrasql.states.CommaSeparedValuesState;
import co.aurasphere.gomorrasql.states.GreedyMatchKeywordState;

/**
 * State that allows a select to switch between the * operator and the column
 * names to rietrieve.
 * 
 * @author Donato Rimenti
 *
 */
public class SelectColumnsState extends AbstractState {

	public SelectColumnsState(QueryInfo queryInfo) {
		super(queryInfo);
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (token.equalsIgnoreCase(Keywords.ASTERISK_KEYWORDS[0])) {
			// Token is "*" (all columns). We proceed to from keyword
			queryInfo.addColumnName("*");
			return new GreedyMatchKeywordState(queryInfo, Keywords.ASTERISK_KEYWORDS,
					q -> new GreedyMatchKeywordState(q, Keywords.FROM_KEYWORDS,
							q2 -> new AnyTokenConsumerState(q2, q2::setTableName, OptionalWhereState::new), 0));
		} else {
			// Token is a column name, we continue until there are none
			queryInfo.addColumnName(token);
			return new CommaSeparedValuesState(queryInfo, queryInfo.getColumnNames(), Keywords.FROM_KEYWORDS[0],
					"%COLUMN_NAME%", q -> new GreedyMatchKeywordState(queryInfo, Keywords.FROM_KEYWORDS,
							q2 -> new AnyTokenConsumerState(q2, q2::setTableName, OptionalWhereState::new)));
		}
	}
}