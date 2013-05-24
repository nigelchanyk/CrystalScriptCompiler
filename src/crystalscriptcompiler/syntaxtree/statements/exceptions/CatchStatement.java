/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.methods.Parameter;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class CatchStatement extends ParseTreeNode {
	
	private Parameter parameter;
	private BlockStatements statements;
	
	public CatchStatement(Parameter parameter, BlockStatements statements) {
		this.parameter = parameter;
		this.statements = statements;
	}
	
}
