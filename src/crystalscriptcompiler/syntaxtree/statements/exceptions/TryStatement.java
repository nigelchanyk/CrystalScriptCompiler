/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.syntaxtree.statements.BlockStatement;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class TryStatement extends BlockStatement {
	
	private BlockStatements statements;
	private CatchStatements catches;
	private BlockStatements finallyStatements;

	public TryStatement(BlockStatements statements, CatchStatements catches) {
		this(statements, catches, new BlockStatements());
	}

	public TryStatement(BlockStatements statements, BlockStatements finallyStatements) {
		this(statements, new CatchStatements(), finallyStatements);
	}
	
	public TryStatement(BlockStatements statements, CatchStatements catches, BlockStatements finallyStatements) {
		this.statements = statements;
		this.catches = catches;
		this.finallyStatements = finallyStatements;
	}
	
}
