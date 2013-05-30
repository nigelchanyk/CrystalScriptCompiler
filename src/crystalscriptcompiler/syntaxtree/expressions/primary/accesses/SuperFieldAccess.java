/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class SuperFieldAccess extends FieldAccess {
	
	private Name superclassName;
	
	public SuperFieldAccess(String id) {
		super(id);
	}

	public SuperFieldAccess(String id, Name superclassName) {
		super(id);
		this.superclassName = superclassName;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		superclassName.setSymbolTable(symbolTable);
	}
	
}
