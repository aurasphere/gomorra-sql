package co.aurasphere.gomorrasql.states;

import java.util.Arrays;

import co.aurasphere.gomorrasql.Keywords;
import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;
import co.aurasphere.gomorrasql.model.QueryInfo.QueryType;
import co.aurasphere.gomorrasql.states.select.SelectColumnsState;
import co.aurasphere.gomorrasql.states.update.UpdateState;
import co.aurasphere.gomorrasql.states.where.OptionalWhereState;

public class InitialState extends AbstractState {

	public InitialState() {
		super(new QueryInfo());
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (token.equalsIgnoreCase(Keywords.SELECT_KEYWORD)) {
			queryInfo.setType(QueryType.SELECT);
			return new SelectColumnsState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.UPDATE_KEYWORD)) {
			queryInfo.setType(QueryType.UPDATE);
			return new UpdateState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.DELETE_KEYWORDS[0])) {
			queryInfo.setType(QueryType.DELETE);
			return new GreedyMatchKeywordState(queryInfo, Keywords.DELETE_KEYWORDS, q -> new GreedyMatchKeywordState(q,
					Keywords.FROM_KEYWORDS, q2 -> new TableNameState(q2, OptionalWhereState::new), 0));
		}
		if (token.equalsIgnoreCase(Keywords.INSERT_KEYWORDS[0])) {
			queryInfo.setType(QueryType.INSERT);
			return new GreedyMatchKeywordState(queryInfo, Keywords.INSERT_KEYWORDS,
					q -> new TableNameState(q, q2 -> new CommaSeparedValuesState(q2, q2.getColumnNames(), Keywords.VALUES_KEYWORD, "%COLUMN_NAME%",
							q3 -> new CommaSeparedValuesState(q3, q3.getValues(), Keywords.END_QUERY_KEYWORD, "%VALUE%",
									FinalState::new, true))));
		}
		if (token.equalsIgnoreCase(Keywords.COMMIT_KEYWORDS[0])) {
			queryInfo.setType(QueryType.COMMIT);
			return new GreedyMatchKeywordState(queryInfo, Keywords.COMMIT_KEYWORDS, FinalState::new);
		}
		if (token.equalsIgnoreCase(Keywords.ROLLBACK_KEYWORD)) {
			queryInfo.setType(QueryType.ROLLBACK);
			return new FinalState(queryInfo);
		}
		if (token.equalsIgnoreCase(Keywords.BEGIN_TRANSACTION_KEYWORDS[0])) {
			queryInfo.setType(QueryType.BEGIN_TRANSACTION);
			return new GreedyMatchKeywordState(queryInfo, Keywords.BEGIN_TRANSACTION_KEYWORDS, FinalState::new);
		}
		throw new CaggiaFaException(Arrays.asList(Keywords.SELECT_KEYWORD, Keywords.UPDATE_KEYWORD,
				Keywords.INSERT_KEYWORDS[0], Keywords.DELETE_KEYWORDS[0], Keywords.BEGIN_TRANSACTION_KEYWORDS[0],
				Keywords.COMMIT_KEYWORDS[0], Keywords.ROLLBACK_KEYWORD), token);
	}

}