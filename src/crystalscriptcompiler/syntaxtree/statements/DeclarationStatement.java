/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.variables.LocalVariableDeclaration;

/**
 *
 * @author User
 */
public class DeclarationStatement extends BlockStatement {
	
	private LocalVariableDeclaration declaration;
	
	public DeclarationStatement(LocalVariableDeclaration declaration) {
		this.declaration = declaration;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		declaration.setSymbolTable(symbolTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		declaration.addVariablesToTable(statementIndex);
	}
	
}
