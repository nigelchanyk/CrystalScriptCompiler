/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.switches;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.Expression;

/**
 *
 * @author User
 */
public class SwitchLabel extends ParseTreeNode {
	
	private Expression expression;
	
	public SwitchLabel(Expression expression) {
		this.expression = expression;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		expression.setSymbolTable(symbolTable);
	}
	
}
