/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.conditional;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class IfStatement extends BlockStatement {
	
	private Expression condition;
	private BlockStatements statements;
	private ElseIfStatements elseIfStatements;
	private BlockStatements elseStatements;

	public IfStatement(Expression condition, BlockStatements statements) {
		this(condition, statements, new ElseIfStatements(), new BlockStatements());
	}

	public IfStatement(Expression condition, BlockStatements statements, ElseIfStatements elseIfStatements, BlockStatements elseStatements) {
		this.condition = condition;
		this.statements = statements;
		this.elseIfStatements = elseIfStatements;
		this.elseStatements = elseStatements;
	}
	
}
