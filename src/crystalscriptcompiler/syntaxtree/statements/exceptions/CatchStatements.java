/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.exceptions;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class CatchStatements extends SequentialCollection<CatchStatement> {
	
	public CatchStatements() {
	}

	public CatchStatements(CatchStatement catchStatement) {
		super(catchStatement);
	}

	public CatchStatements(CatchStatement catchStatement, CatchStatements next) {
		super(catchStatement, next);
	}
	
}
