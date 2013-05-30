/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class ParseTreeNode {

	protected SymbolTable symbolTable;
	
	public void addDependencies(List<Name> importList) {
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public void addDeclarationToTable() {
	}
	
}
