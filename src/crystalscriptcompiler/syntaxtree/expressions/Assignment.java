/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;

/**
 *
 * @author User
 */
public class Assignment extends Expression {
	
	public enum Operator {
		EQ,
		MULTEQ,
		DIVEQ,
		MODEQ,
		PLUSEQ,
		MINUSEQ,
		LSHIFTEQ,
		RSHIFTEQ,
		URSHIFTEQ,
		ANDEQ,
		XOREQ,
		OREQ
	}

	private LeftValue leftValue;
	private Operator operator;
	private Expression assignmentExpression;

	public Assignment(LeftValue leftValue, Operator operator, Expression assignmentExpression) {
		this.leftValue = leftValue;
		this.operator = operator;
		this.assignmentExpression = assignmentExpression;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		leftValue.setSymbolTable(symbolTable);
		assignmentExpression.setSymbolTable(symbolTable);
	}
	
}
