/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;

/**
 *
 * @author User
 */
public class MethodSymbolDeclaration extends SymbolDeclaration {
	
	private MethodDeclaration declaration;

	public MethodSymbolDeclaration(MethodDeclaration declaration) {
		super(Kind.METHOD);
		this.declaration = declaration;
	}
	
}
