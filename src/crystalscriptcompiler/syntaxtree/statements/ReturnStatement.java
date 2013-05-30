/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;

/**
 *
 * @author User
 */
public class ReturnStatement extends BlockStatement {
	
	private Expression expression; // Nullable

	public ReturnStatement() {
	}
	
	public ReturnStatement(Expression expression) {
		this.expression = expression;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		if (expression != null)
			expression.setSymbolTable(symbolTable);
	}
	
}
