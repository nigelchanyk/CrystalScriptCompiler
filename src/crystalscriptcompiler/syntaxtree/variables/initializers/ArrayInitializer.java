/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables.initializers;

import crystalscriptcompiler.symbols.SymbolTable;

/**
 *
 * @author User
 */
public class ArrayInitializer extends GenericVariableInitializer {
	
	private GenericVariableInitializers innerInitializers;

	public ArrayInitializer() {
	}

	public ArrayInitializer(GenericVariableInitializers innerInitializers) {
		this.innerInitializers = innerInitializers;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		innerInitializers.setSymbolTable(symbolTable);
	}

}
