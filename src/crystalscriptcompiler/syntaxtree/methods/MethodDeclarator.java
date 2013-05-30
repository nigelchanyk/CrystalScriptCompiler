/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;

/**
 *
 * @author User
 */
public class MethodDeclarator extends ParseTreeNode {

	/*
	 * This node is not included in the syntax tree
	 */
	
	private String id;
	private Parameters parameters;

	public MethodDeclarator(String id, Parameters parameters) {
		this.id = id;
		this.parameters = parameters;
	}

	public String getId() {
		return id;
	}

	public Parameters getParameters() {
		return parameters;
	}

}
