/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.conditional;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class ElseIfStatements extends SequentialCollection<ElseIfStatement> {
	
	public ElseIfStatements() {
	}

	public ElseIfStatements(ElseIfStatement statement) {
		super(statement);
	}

	public ElseIfStatements(ElseIfStatement statement, ElseIfStatements next) {
		super(statement, next);
	}
	
}
