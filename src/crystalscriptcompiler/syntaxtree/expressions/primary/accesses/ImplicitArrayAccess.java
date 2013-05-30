/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ImplicitArrayAccess extends ArrayAccess {
	
	private Name name;

	public ImplicitArrayAccess(Name name, Expression index) {
		super(index);
		this.name = name;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		name.setSymbolTable(symbolTable);
	}

}
