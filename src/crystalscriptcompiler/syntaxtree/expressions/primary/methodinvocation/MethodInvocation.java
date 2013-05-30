/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.methodinvocation;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.ArgumentList;
import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public class MethodInvocation extends Primary {

	private ArgumentList arguments;
	
	public MethodInvocation(ArgumentList arguments) {
		this.arguments = arguments;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		arguments.setSymbolTable(symbolTable);
	}
	
}
