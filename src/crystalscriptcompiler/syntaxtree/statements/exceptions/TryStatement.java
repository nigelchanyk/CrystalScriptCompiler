/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;

/**
 *
 * @author User
 */
public class TryStatement extends BlockStatement {
	
	private Block block;
	private CatchStatements catches;
	private Block finallyBlock;

	public TryStatement(Block block, CatchStatements catches) {
		this(block, catches, new Block());
	}

	public TryStatement(Block block, Block finallyBlock) {
		this(block, new CatchStatements(), block);
	}
	
	public TryStatement(Block block, CatchStatements catches, Block finallyBlock) {
		this.block = block;
		this.catches = catches;
		this.finallyBlock = finallyBlock;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		block.setSymbolTable(symbolTable);
		catches.setSymbolTable(symbolTable);
		finallyBlock.setSymbolTable(symbolTable);
	}
	
}
