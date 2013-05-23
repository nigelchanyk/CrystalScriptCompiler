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
	
	private String id;
	private Parameters parameters;
	private ConstructorInvocation superclassInvocation; //Nullable

	public ConstructorDeclarator(String id, Parameters parameters, ConstructorInvocationOptional superclassInvocationOptional) {
		this.id = id;
		this.parameters = parameters;
		this.superclassInvocation = superclassInvocationOptional.getInvocation();
	}

	public String getId() {
		return id;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public ConstructorInvocation getSuperclassInvocation() {
		return superclassInvocation;
	}
	
}
