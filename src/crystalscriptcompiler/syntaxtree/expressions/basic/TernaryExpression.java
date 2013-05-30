/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;

/**
 *
 * @author User
 */
public class TernaryExpression extends BasicExpression {
	
	private BasicExpression condition;
	private Expression trueExpression;
	private BasicExpression falseExpression;

	public TernaryExpression(BasicExpression condition, Expression trueExpression, BasicExpression falseExpression) {
		this.condition = condition;
		this.trueExpression = trueExpression;
		this.falseExpression = falseExpression;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		condition.setSymbolTable(symbolTable);
		trueExpression.setSymbolTable(symbolTable);
		falseExpression.setSymbolTable(symbolTable);
	}
	
}
