package co.aurasphere.gomorrasql.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Context wrapper for the parsed query.
 * 
 * @author Donato Rimenti
 *
 */
public class QueryInfo {

	public enum QueryType {
		SELECT, UPDATE, DELETE, INSERT, COMMIT, BEGIN_TRANSACTION, ROLLBACK;
	}

	private QueryType type;

	private String tableName;

	private List<String> columnNames = new ArrayList<>();

	private List<String> values = new ArrayList<>();

	private List<WhereCondition> whereConditions = new ArrayList<>();

	private List<String> joinedTables = new ArrayList<>();

	private List<String> whereConditionsJoinOperators = new ArrayList<>();
	
	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void addColumnName(String columnName) {
		this.columnNames.add(columnName);
	}

	public List<String> getValues() {
		return values;
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	public List<WhereCondition> getWhereConditions() {
		return whereConditions;
	}

	public void addWhereCondition(WhereCondition whereCondition) {
		this.whereConditions.add(whereCondition);
	}

	public List<String> getJoinedTables() {
		return joinedTables;
	}

	public void addJoinedTable(String joinedTable) {
		this.joinedTables.add(joinedTable);
	}

	public List<String> getWhereConditionsJoinOperators() {
		return whereConditionsJoinOperators;
	}

	public void addWhereConditionsJoinOperator(String whereConditionsJoinOperator) {
		this.whereConditionsJoinOperators.add(whereConditionsJoinOperator);
	}

}