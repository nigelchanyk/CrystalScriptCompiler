/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.types.Type;
import crystalscriptcompiler.syntaxtree.types.VarType;

/**
 *
 * @author User
 */
public class MethodHeader extends ParseTreeNode {
	
	private Modifiers modifiers;
	private MethodDeclarator declarator;
	private Type type;

	public MethodHeader(Modifiers modifiers, MethodDeclarator declarator) {
		this(modifiers, declarator, new VarType());
	}
	
	public MethodHeader(Modifiers modifiers, MethodDeclarator declarator, Type type) {
		this.modifiers = modifiers;
		this.declarator = declarator;
		this.type = type;
	}

	public MethodDeclarator getDeclarator() {
		return declarator;
	}

	public Modifiers getModifiers() {
		return modifiers;
	}

	public Type getType() {
		return type;
	}
	
}
