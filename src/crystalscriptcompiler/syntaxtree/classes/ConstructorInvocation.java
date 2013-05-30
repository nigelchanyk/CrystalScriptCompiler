/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.ArgumentList;

/**
 *
 * @author User
 */
public class ConstructorInvocation extends ParseTreeNode {
	
	public enum Kind {
		THIS,
		SUPER
	}
	
	private ArgumentList arguments;
	private Kind kind;
	
	public ConstructorInvocation(Kind kind, ArgumentList arguments) {
		this.kind = kind;
		this.arguments = arguments;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		arguments.setSymbolTable(symbolTable);
	}
	
}
