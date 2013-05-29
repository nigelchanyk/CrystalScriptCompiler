/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

/**
 *
 * @author User
 */
public abstract class SymbolDeclaration {
	
	public static enum Kind {
		CLASS,
		INTERFACE,
		VARIABLE
	}

	private Kind kind;
	
	public SymbolDeclaration(Kind kind) {
		this.kind = kind;
	}
	
}
