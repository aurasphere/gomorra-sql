package co.aurasphere.gomorrasql.model;

public class WhereCondition {

	private String field;
	private String value;
	private String operator;
	
	public WhereCondition(String field) {
		this.field = field;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}

}
