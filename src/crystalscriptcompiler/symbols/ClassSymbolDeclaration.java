/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.classes.ClassDeclaration;

/**
 *
 * @author User
 */
public class ClassSymbolDeclaration extends SymbolDeclaration {
	
	private ClassDeclaration declaration;

	public ClassSymbolDeclaration(ClassDeclaration declaration) {
		super(Kind.CLASS);
		this.declaration = declaration;
	}

	public ClassDeclaration getDeclaration() {
		return declaration;
	}
	
}
