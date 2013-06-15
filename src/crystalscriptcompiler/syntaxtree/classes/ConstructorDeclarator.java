/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.methods.Parameters;

/**
 *
 * @author User
 */
public class ConstructorDeclarator extends ParseTreeNode {

	/*
	 * This node will not be included in the syntax tree
	 */
	
	private Parameters parameters;
	private ConstructorInvocation superclassInvocation; //Nullable

	public ConstructorDeclarator(Parameters parameters, ConstructorInvocationOptional superclassInvocationOptional) {
		this.parameters = parameters;
		this.superclassInvocation = superclassInvocationOptional.getInvocation();
	}

	public Parameters getParameters() {
		return parameters;
	}

	public ConstructorInvocation getConstructorInvocation() {
		return superclassInvocation;
	}
	
}
