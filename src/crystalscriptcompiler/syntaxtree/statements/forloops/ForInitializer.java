/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.forloops;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.StatementExpressionList;
import crystalscriptcompiler.syntaxtree.variables.LocalVariableDeclaration;

/**
 *
 * @author User
 */
public class ForInitializer extends ParseTreeNode {

	private ParseTreeNode initialization; // Nullable

	public ForInitializer() {
	}

	public ForInitializer(LocalVariableDeclaration declaration) {
		initialization = declaration;
	}

	public ForInitializer(StatementExpressionList expressionList) {
		initialization = expressionList;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		if (initialization != null)
			initialization.setSymbolTable(symbolTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		if (initialization != null)
			initialization.addVariablesToTable(statementIndex);
	}

	@Override
	public void determineReferenceType() {
		if (initialization != null)
			initialization.determineReferenceType();
	}
	
}
