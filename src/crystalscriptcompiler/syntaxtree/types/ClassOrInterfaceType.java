/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ClassOrInterfaceType extends Type {
	
	private Name name;
	
	public ClassOrInterfaceType(Name name) {
		this.name = name;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		name.setSymbolTable(symbolTable);
	}

	public Name getName() {
		return name;
	}
	
}
