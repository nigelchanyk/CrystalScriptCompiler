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
public class LabeledStatement extends BlockStatement {
	
	private String id;
	private BlockStatement statement;

	public LabeledStatement(String id, BlockStatement statement) {
		this.id = id;
		this.statement = statement;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		statement.setSymbolTable(new SymbolTable(symbolTable));
	}
	
}
