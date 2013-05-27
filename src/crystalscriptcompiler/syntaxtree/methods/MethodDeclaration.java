/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class MethodDeclaration extends MemberDeclaration {
	
	private Block block;
	private Parameters parameters;

	public MethodDeclaration(MethodHeader header) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.block = new Block();
		this.parameters = new Parameters();
	}
	
	public MethodDeclaration(MethodHeader header, Block block) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.block = block;
		this.parameters = header.getDeclarator().getParameters();
	}
	
}
