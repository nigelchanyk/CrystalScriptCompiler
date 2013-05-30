/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.methods.Parameter;
import crystalscriptcompiler.syntaxtree.statements.Block;

/**
 *
 * @author User
 */
public class CatchStatement extends ParseTreeNode {
	
	private Parameter parameter;
	private Block block;
	
	public CatchStatement(Parameter parameter, Block block) {
		this.parameter = parameter;
		this.block = block;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		SymbolTable innerTable = new SymbolTable(symbolTable);
		parameter.setSymbolTable(innerTable);
		block.setSymbolTable(innerTable);
	}
	
}
