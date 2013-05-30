/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.symbols.SymbolTable;

/**
 *
 * @author User
 */
public class Block extends BlockStatement {
	
	private BlockStatements statements;

	public Block() {
		this.statements = new BlockStatements();
	}

	public Block(BlockStatements statements) {
		this.statements = statements;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		SymbolTable nestedTable = new SymbolTable(symbolTable);
		super.setSymbolTable(nestedTable);
		statements.setSymbolTable(nestedTable);
	}
	
}
