/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

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
	private BlockStatements statements;
	private ConditionOrder order;
	
	public WhileStatement(Expression condition, BlockStatements statements, ConditionOrder order) {
		this.condition = condition;
		this.statements = statements;
		this.order = order;
	}
}
