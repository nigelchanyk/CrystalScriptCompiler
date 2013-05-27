/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class VariableDeclarators extends SequentialCollection<VariableDeclarator> {
	
	public VariableDeclarators() {
	}

	public VariableDeclarators(VariableDeclarator declarator) {
		super(declarator);
	}

	public VariableDeclarators(VariableDeclarator declarator, VariableDeclarators next, Order order) {
		super(declarator, next, order);
	}
	
}
