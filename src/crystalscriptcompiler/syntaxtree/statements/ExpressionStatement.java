/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.syntaxtree.expressions.StatementExpression;

/**
 *
 * @author User
 */
public class ExpressionStatement extends BlockStatement {
	
	private StatementExpression expression;

	public ExpressionStatement(StatementExpression expression) {
		this.expression = expression;
	}
	
}
