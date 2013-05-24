/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class ArgumentList extends SequentialCollection<Expression> {
	
	public ArgumentList() {
	}

	public ArgumentList(Expression argument) {
		super(argument);
	}

	public ArgumentList(Expression argument, ArgumentList next) {
		super(argument, next);
	}
	
}
