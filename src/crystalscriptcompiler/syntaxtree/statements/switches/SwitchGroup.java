/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.switches;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class SwitchGroup extends ParseTreeNode {
	
	private SwitchLabels labels;
	private BlockStatements statements;
	
	public SwitchGroup(SwitchLabels labels, BlockStatements statements) {
		this.labels = labels;
		this.statements = statements;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		labels.setSymbolTable(symbolTable);
		statements.setSymbolTable(new SymbolTable(symbolTable));
	}
	
}
