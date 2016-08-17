package com.exression.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.expression.parser.ExpressionParser;
import com.expression.parser.PlusExpressionParser;
import com.expression.vo.ExpressionVO;

public class PlusExpressionParserTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testParser() {
		ExpressionParser plusExpression = new PlusExpressionParser();
		List<ExpressionVO> expressionList = plusExpression.parseExpression("2*x+25/y+34");
		assertTrue(expressionList.size()==3);
	}

}
