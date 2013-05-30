/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;

/**
 *
 * @author User
 */
public class ConstructorInvocationOptional extends ParseTreeNode {

	private ConstructorInvocation invocation;
	
	public ConstructorInvocationOptional() {
	}

	public ConstructorInvocationOptional(ConstructorInvocation invocation) {
		this.invocation = invocation;
	}

	public ConstructorInvocation getInvocation() {
		return invocation;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		invocation.setSymbolTable(symbolTable);
	}
	
}
