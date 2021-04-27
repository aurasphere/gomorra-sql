package co.aurasphere.gomorrasql.states;

import java.util.function.Function;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

// State that proceeds to the next one if all the given keywords are matched, otherwise throws an exception
public class GreedyMatchKeywordState extends AbstractState {

	private int currentIndex = 1;
	private String[] keywords;
	private Function<QueryInfo, AbstractState> nextStateTransition;

	public GreedyMatchKeywordState(QueryInfo queryInfo, String[] keywords,
			Function<QueryInfo, AbstractState> nextStateTransition) {
		super(queryInfo);
		this.keywords = keywords;
		this.nextStateTransition = nextStateTransition;
	}

	public GreedyMatchKeywordState(QueryInfo queryInfo, String[] keywords,
			Function<QueryInfo, AbstractState> nextStateTransition, int currentIndex) {
		super(queryInfo);
		this.keywords = keywords;
		this.nextStateTransition = nextStateTransition;
		this.currentIndex = currentIndex;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		if (token.equals(keywords[currentIndex])) {
			currentIndex++;
			if (currentIndex == keywords.length) {
				return nextStateTransition.apply(queryInfo);
			}
			return this;
		}
		throw new CaggiaFaException(keywords[currentIndex], token);
	}
}