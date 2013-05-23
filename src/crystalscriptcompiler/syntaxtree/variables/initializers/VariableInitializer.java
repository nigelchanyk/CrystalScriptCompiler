/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables.initializers;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.Expression;

/**
 *
 * @author User
 */
public class VariableInitializer extends GenericVariableInitializer {
	
	private Expression expression;

	public VariableInitializer(Expression expression) {
		this.expression = expression;
	}
	
}
