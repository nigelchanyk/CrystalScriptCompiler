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
	
	public static enum Scope {
		FIELD,
		LOCAL
	}
	
	public static final int NO_INDEX = -1;
	
	private Type type;
	private int declarationIndex;
	private Scope scope;

	public VariableSymbolDeclaration(Type type, Scope scope) {
		this(type, NO_INDEX, scope);
	}
	
	public VariableSymbolDeclaration(Type type, int declarationIndex, Scope scope) {
		super(Kind.VARIABLE, type);
		this.type = type;
		this.declarationIndex = declarationIndex;
		this.scope = scope;
	}
	
}
