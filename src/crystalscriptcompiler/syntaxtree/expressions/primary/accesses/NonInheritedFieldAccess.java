/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public class NonInheritedFieldAccess extends FieldAccess {
	
	private Primary primary; // Nullable
	
	public NonInheritedFieldAccess(Primary primary, String id) {
		super(id);
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		if (primary != null)
			super.setSymbolTable(symbolTable);
	}
	
}
