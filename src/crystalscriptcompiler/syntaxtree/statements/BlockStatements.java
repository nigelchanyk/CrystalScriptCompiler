/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class BlockStatements extends SequentialCollection<BlockStatement> {
	
	public BlockStatements() {
	}

	public BlockStatements(BlockStatement statement) {
		super(statement);
	}

	public BlockStatements(BlockStatement statement, BlockStatements next, Order order) {
		super(statement, next, order);
	}
	
}
