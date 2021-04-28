package co.aurasphere.gomorrasql.states;

import java.util.function.Consumer;
import java.util.function.Function;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

/**
 * Consumes any token applying the given function and then moves to the next
 * state.
 * 
 * @author Donato Rimenti
 *
 */
public class AnyTokenConsumerState extends AbstractState {

	private Consumer<String> tokenConsumer;
	private Function<QueryInfo, AbstractState> transitionFunction;

	public AnyTokenConsumerState(QueryInfo queryInfo, Consumer<String> tokenConsumer,
			Function<QueryInfo, AbstractState> transitionFunction) {
		super(queryInfo);
		this.tokenConsumer = tokenConsumer;
		this.transitionFunction = transitionFunction;
	}

	@Override
	public AbstractState transitionToNextState(String token) throws CaggiaFaException {
		tokenConsumer.accept(token);
		return transitionFunction.apply(queryInfo);
	}

}