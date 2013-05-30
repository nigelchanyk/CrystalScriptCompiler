/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.methodinvocation;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.ArgumentList;
import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class NonInheritedMethodInvocation extends MethodInvocation {

	private Primary primary;
	private String methodId;

	public NonInheritedMethodInvocation(Primary primary, String methodId, ArgumentList arguments) {
		super(arguments);
		this.primary = primary;
		this.methodId = methodId;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		primary.setSymbolTable(symbolTable);
	}
	
}
