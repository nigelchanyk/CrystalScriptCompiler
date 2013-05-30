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
public class IfStatement extends BlockStatement {
	
	private Expression condition;
	private BlockStatement statement;
	private BlockStatement elseStatement; // Nullable

	public IfStatement(Expression condition, BlockStatement statement) {
		this(condition, statement, null);
	}

	public IfStatement(Expression condition, BlockStatement statement, BlockStatement elseStatement) {
		this.condition = condition;
		this.statement = statement;
		this.elseStatement = elseStatement;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		condition.setSymbolTable(symbolTable);
		statement.setSymbolTable(symbolTable);
		elseStatement.setSymbolTable(symbolTable);
	}
	
}
