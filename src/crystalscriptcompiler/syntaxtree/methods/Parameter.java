/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
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

	public Parameter(VariableDeclarator variable) {
		this.variable = variable;
		this.type = new VarType();
	}
	
	public Parameter(VariableDeclarator variable, Type type) {
		this.variable = variable;
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		variable.setSymbolTable(symbolTable);
		type.setSymbolTable(symbolTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		symbolTable.addSymbol(variable.getId(), type, statementIndex, VariableSymbolDeclaration.Scope.LOCAL);
	}

}
