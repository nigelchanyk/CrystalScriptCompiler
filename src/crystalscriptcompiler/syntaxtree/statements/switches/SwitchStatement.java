/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.switches;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;

/**
 *
 * @author User
 */
public class SwitchStatement extends BlockStatement {
	
	private Expression condition;
	private SwitchGroups groups;
	
	public SwitchStatement(Expression condition, SwitchGroups groups) {
		this.condition = condition;
		this.groups = groups;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		condition.setSymbolTable(symbolTable);
		groups.setSymbolTable(symbolTable);
	}
	
}
