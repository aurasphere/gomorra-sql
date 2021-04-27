package co.aurasphere.gomorrasql.model;

import java.sql.ResultSet;

public class GomorraSqlQueryResult {
	
	private Integer affectedRows;
	
	private ResultSet resultSet;

	public Integer getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(Integer affectedRows) {
		this.affectedRows = affectedRows;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}