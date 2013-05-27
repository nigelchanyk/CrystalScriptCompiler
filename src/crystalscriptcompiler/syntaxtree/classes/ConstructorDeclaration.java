/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.methods.Parameters;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;
import crystalscriptcompiler.syntaxtree.types.VarType;

/**
 *
 * @author User
 */
public class ConstructorDeclaration extends MemberDeclaration {

	private Block block;
	private Parameters parameters;
	private ConstructorInvocation superclassInvocation;
	
	public ConstructorDeclaration(Modifiers modifiers, ConstructorDeclarator declarator, Block block) {
		super(Kind.CONSTRUCTOR, modifiers, new VarType(), declarator.getId());
		this.block = block;
		this.parameters = declarator.getParameters();
		this.superclassInvocation = declarator.getSuperclassInvocation();
	}
	
}
