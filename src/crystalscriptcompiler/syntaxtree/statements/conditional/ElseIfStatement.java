/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.conditional;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class ElseIfStatement extends ParseTreeNode {
	
	private Expression condition;
	private BlockStatements statements;

	public ElseIfStatement(Expression condition, BlockStatements statements) {
		this.condition = condition;
		this.statements = statements;
	}
	
}
