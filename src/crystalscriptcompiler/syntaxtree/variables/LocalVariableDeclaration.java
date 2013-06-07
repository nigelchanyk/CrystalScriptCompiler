/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public class LocalVariableDeclaration extends ParseTreeNode {
	
	private Type type;
	private VariableDeclarators declarators;
	
	public LocalVariableDeclaration(Type type, VariableDeclarators declarators) {
		this.type = type;
		this.declarators = declarators;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		type.setSymbolTable(symbolTable);
		declarators.setSymbolTable(symbolTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		for (VariableDeclarator declarator : declarators)
			symbolTable.addSymbol(declarator.getId(), type, statementIndex, VariableSymbolDeclaration.Scope.LOCAL);
	}

	@Override
	public void determineReferenceType() {
		type.determineReferenceType();
	}
	
}
