package co.aurasphere.gomorrasql.states;

import co.aurasphere.gomorrasql.model.CaggiaFaException;
import co.aurasphere.gomorrasql.model.QueryInfo;

/**
 * Base class for any state used by the GomorraSQL parser.
 * 
 * @author Donato Rimenti
 *
 */
public abstract class AbstractState {

	protected QueryInfo queryInfo;

	public AbstractState(QueryInfo queryInfo) {
		this.queryInfo = queryInfo;
	}

	/**
	 * Moves from this state to the next one, according to the token read.
	 * 
	 * @param token the token in the query currently being parsed
	 * @return the next state
	 * @throws CaggiaFaException if the token is not allowed in this position
	 */
	public abstract AbstractState transitionToNextState(String token) throws CaggiaFaException;

	/**
	 * Returns true if the query can end with this state (e.g. if the query can end
	 * with a given token).
	 * 
	 * @return true if the query can end in this state
	 */
	public boolean isFinalState() {
		return false;
	}

	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

}