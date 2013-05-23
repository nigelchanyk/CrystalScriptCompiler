/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

import crystalscriptcompiler.syntaxtree.variables.LocalVariableDeclaration;

/**
 *
 * @author User
 */
public class DeclarationStatement extends BlockStatement {
	
	private LocalVariableDeclaration declaration;
	
	public DeclarationStatement(LocalVariableDeclaration declaration) {
		this.declaration = declaration;
	}
	
}
