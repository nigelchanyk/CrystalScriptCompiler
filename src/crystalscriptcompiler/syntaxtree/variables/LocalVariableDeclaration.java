/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public class LocalVariableDeclaration extends ParseTreeNode {
	
	private Type type;
	private VariableDeclarators declarators;
	
	public LocalVariableDeclaration(Type type, VariableDeclarators declarators) {
		this.type = type;
		this.declarators = declarators;
	}
	
}
