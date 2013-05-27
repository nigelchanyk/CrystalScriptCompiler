/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.forloops;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.expressions.StatementExpressionList;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class ForStatement extends BlockStatement {

	private ForInitializer initializer;
	private Expression condition;
	private StatementExpressionList updates;
	private BlockStatement statement;
	
	public ForStatement(ForInitializer initializer, Expression condition, StatementExpressionList updates, BlockStatement statement) {
		this.initializer = initializer;
		this.condition = condition;
		this.updates = updates;
		this.statement = statement;
	}
	
}
