/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.statements.BlockStatements;

/**
 *
 * @author User
 */
public class MethodDeclaration extends MemberDeclaration {
	
	private BlockStatements statements;
	private Parameters parameters;

	public MethodDeclaration(MethodHeader header) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.statements = new BlockStatements();
		this.parameters = new Parameters();
	}
	
	public MethodDeclaration(MethodHeader header, BlockStatements statements) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.statements = statements;
		this.parameters = header.getDeclarator().getParameters();
	}
	
}
