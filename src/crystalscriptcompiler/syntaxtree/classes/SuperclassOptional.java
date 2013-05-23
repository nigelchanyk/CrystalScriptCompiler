/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class SuperclassOptional extends ParseTreeNode {
	
	private ClassOrInterfaceType type;
	
	public SuperclassOptional() {
	}

	public SuperclassOptional(ClassOrInterfaceType type) {
		this.type = type;
	}
	
	public ClassOrInterfaceType getSuperclass() {
		return type;
	}
}
