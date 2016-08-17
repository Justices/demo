package com.expression.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.expression.enumType.Operator;
import com.expression.vo.ExpressionVO;

public class MinusExpressionParser extends ExpressionParser {

	@Override
	public List<ExpressionVO> parseExpression(String expression) {
		List<ExpressionVO> expressionList = new ArrayList<ExpressionVO>();
		parse(expression, expressionList);
		return expressionList;
	}
	
	private void parse(String expression, List<ExpressionVO> expressionList) {
		int index = getMultiDiviIndex(expression);
		if(expression==null||expression=="")
			return ;
		ExpressionVO  expressionVO = new ExpressionVO();
		if(index==-1){
			expressionVO.setElement(expression.trim());
			expressionVO.setOperator(Operator.EMPTY);
			expressionList.add(expressionVO);
		}else{
			String str = expression.substring(index, index+1);
			expressionVO.setOperator(Operator.getOperatorByVaule(str));
			expressionVO.setElement(expression.substring(index+1).trim());
			expressionList.add(expressionVO);
			parse(expression.substring(0,index).trim(), expressionList);
		}
	}
	
	
	private int getMultiDiviIndex(String expression) {
		int multiIndex = getOperatorIndex(expression, Operator.MULTIPL);
		int divIndex = getOperatorIndex(expression, Operator.DIVIDED);
		
		if(multiIndex==-1)
			return divIndex;
		
		if(divIndex==-1)
			return multiIndex;
		
		return multiIndex>divIndex?multiIndex:divIndex;
	}
	
	private double getExpressionVOValue(ExpressionVO  expressionVO, Map<String,Object> parameter) throws Exception {
		double value = getParameterValue(expressionVO.getElement(), parameter);
		switch(expressionVO.getOperator()){
			case DIVIDED:
				return 1/value;
			default:
				return value;
		}
	}
	
	@Override
	public  double getExpressionValue(String expression, Map<String,Object> parameter) throws Exception{
		List<ExpressionVO> list = parseExpression(expression);
		double sum = 1.00;
		for(ExpressionVO expressionVO:list) {
			sum*=getExpressionVOValue(expressionVO, parameter);
		}
		return sum;
	}

}
