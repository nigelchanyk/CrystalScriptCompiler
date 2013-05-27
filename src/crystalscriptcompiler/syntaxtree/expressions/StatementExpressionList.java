/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class StatementExpressionList extends SequentialCollection<StatementExpression> {
	
	public StatementExpressionList() {
	}

	public StatementExpressionList(StatementExpression expression) {
		super(expression);
	}

	public StatementExpressionList(StatementExpression expression, StatementExpressionList next, Order order) {
		super(expression, next, order);
	}
	
}
