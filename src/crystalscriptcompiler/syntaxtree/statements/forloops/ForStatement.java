/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.forloops;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.expressions.StatementExpressionList;
import crystalscriptcompiler.syntaxtree.statements.BlockStatement;

/**
 *
 * @author User
 */
public class ForStatement extends BlockStatement {

	private ForInitializer initializer;
	private Expression condition;
	private StatementExpressionList updates;
	private BlockStatement statement;
	
	public ForStatement(ForInitializer initializer, Expression condition, StatementExpressionList updates, BlockStatement statement) {
		this.initializer = initializer;
		this.condition = condition;
		this.updates = updates;
		this.statement = statement;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		SymbolTable innerTable = new SymbolTable();
		initializer.setSymbolTable(innerTable);
		condition.setSymbolTable(innerTable);
		updates.setSymbolTable(innerTable);
		statement.setSymbolTable(innerTable);
	}
	
}
