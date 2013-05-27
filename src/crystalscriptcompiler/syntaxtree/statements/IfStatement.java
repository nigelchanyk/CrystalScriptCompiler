/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;

/**
 *
 * @author User
 */
public class IfStatement extends BlockStatement {
	
	private Expression condition;
	private BlockStatement statement;
	private BlockStatement elseStatement; // Nullable

	public IfStatement(Expression condition, BlockStatement statement) {
		this(condition, statement, null);
	}

	public IfStatement(Expression condition, BlockStatement statement, BlockStatement elseStatement) {
		this.condition = condition;
		this.statement = statement;
		this.elseStatement = elseStatement;
	}
	
}
