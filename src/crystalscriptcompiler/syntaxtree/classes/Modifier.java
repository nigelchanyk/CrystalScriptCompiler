/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;

/**
 *
 * @author User
 */
public class Modifier extends ParseTreeNode {
	
	public static enum Kind {
		PUBLIC,
		PROTECTED,
		PRIVATE,
		ABSTRACT,
		STATIC
	}
	
	private Kind kind;
	
	public Modifier(Kind kind) {
		this.kind = kind;
	}
}
