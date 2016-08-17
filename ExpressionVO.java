package com.expression.vo;

import com.expression.enumType.Operator;

public class ExpressionVO {
	
	private String element;
	
	private Operator operator;

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
