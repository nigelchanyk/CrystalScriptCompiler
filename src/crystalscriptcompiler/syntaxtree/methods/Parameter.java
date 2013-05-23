/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.types.Type;
import crystalscriptcompiler.syntaxtree.types.VarType;
import crystalscriptcompiler.syntaxtree.variables.VariableDeclarator;

/**
 *
 * @author User
 */
public class Parameter extends ParseTreeNode {
	
	private VariableDeclarator variable;
	private Type type;
	private Expression defaultParameter; // Nullable

	public Parameter(VariableDeclarator variable) {
		this.variable = variable;
		this.type = new VarType();
	}
	
	public Parameter(VariableDeclarator variable, Type type) {
		this.variable = variable;
		this.type = type;
	}

	public Parameter(VariableDeclarator variable, Expression defaultParameter) {
		this.variable = variable;
		this.type = new VarType();
		this.defaultParameter = defaultParameter;
	}

	public Parameter(VariableDeclarator variable, Type type, Expression defaultParameter) {
		this.variable = variable;
		this.type = type;
		this.defaultParameter = defaultParameter;
	}
	
}
