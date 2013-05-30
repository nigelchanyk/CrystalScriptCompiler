/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;

/**
 *
 * @author User
 */
public class WhileStatement extends BlockStatement {

	public static enum ConditionOrder {
		BEFORE,
		AFTER
	}
	
	private Expression condition;
	private BlockStatement statement;
	private ConditionOrder order;
	
	public WhileStatement(Expression condition, BlockStatement statement, ConditionOrder order) {
		this.condition = condition;
		this.statement = statement;
		this.order = order;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		condition.setSymbolTable(symbolTable);
		statement.setSymbolTable(symbolTable);
	}

}
