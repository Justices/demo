package com.expression.parser;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.expression.enumType.Operator;

public abstract class ExpressionParser {
	protected final Operator PLUS = Operator.PLUS;
	protected final Operator MINUS = Operator.MINUS;
	protected final Operator DIVIDED = Operator.DIVIDED;
	protected  final Operator EMPTY = Operator.EMPTY;
	
	public abstract <T> List<T> parseExpression(String expression);
	
	public abstract double getExpressionValue(String expression, Map<String,Object> parameter) throws Exception ;
	
	
	public int getOperatorIndex(String expression, Operator operator) {
		return expression.lastIndexOf(operator.toString());
	}
	
	public int getPlusMinusIndex(String expression) {
		int plusIndx = expression.lastIndexOf(PLUS.toString());
		int minusIndx = expression.lastIndexOf(MINUS.toString());
		
		if(plusIndx==-1)
			return minusIndx;
		
		if(minusIndx==-1)
			return plusIndx;
		
		return plusIndx>minusIndx ? plusIndx:minusIndx;
	}
	
	
	protected double getParameterValue(String expression, Map<String,Object> parameter) throws Exception {
		if(expression == null || expression =="")
			return 0;
		
		if(isNumberic(expression))
			return Double.valueOf(expression+".0");
		
		if(parameter.containsKey(expression)) {
			
			String orgialValue = parameter.get(expression).toString();
			if(isNumberic(expression)){
				return Double.valueOf(orgialValue + ".0");
			}else {
				return Double.valueOf(orgialValue);
			}
		}
		
		throw new Exception("There is no input for :" + expression);
	}
	
	
	protected boolean isNumberic(String expression) {
		Pattern pattern = Pattern.compile("-?[0-9]+");
		Matcher isNum = pattern.matcher(expression);
		return isNum.matches();
	}
	
	protected boolean isDouble(String expression) {
		Pattern pattern = Pattern.compile("-?[0-9]+.[0-9]+");
		Matcher isNum = pattern.matcher(expression);
		return isNum.matches();
	}
}
