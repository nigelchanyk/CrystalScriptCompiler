/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.interfaces.InterfaceDeclaration;

/**
 *
 * @author User
 */
public class InterfaceSymbolDeclaration extends SymbolDeclaration {
	
	private InterfaceDeclaration declaration;

	public InterfaceSymbolDeclaration(InterfaceDeclaration declaration) {
		super(Kind.INTERFACE);
		this.declaration = declaration;
	}

	public InterfaceDeclaration getDeclaration() {
		return declaration;
	}

}
