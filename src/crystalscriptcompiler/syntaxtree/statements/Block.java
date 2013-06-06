/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.symbols.SymbolTable;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class Block extends BlockStatement implements Iterable<BlockStatement> {
	
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

	@Override
	public Iterator<BlockStatement> iterator() {
		return statements.iterator();
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		int index = 0;
		for (BlockStatement statement : this)
			statement.addVariablesToTable(index++);
	}
	
}
