/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public class VariableSymbolDeclaration extends SymbolDeclaration {
	
	private Type type;
	
	public VariableSymbolDeclaration(Type type) {
		super(Kind.VARIABLE, type);
		this.type = type;
	}
	
}
