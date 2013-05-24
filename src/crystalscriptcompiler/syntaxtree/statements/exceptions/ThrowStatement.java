/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;

/**
 *
 * @author User
 */
public class ThrowStatement extends BlockStatement {
	
	private Expression expression;

	public ThrowStatement(Expression expression) {
		this.expression = expression;
	}
	
}
