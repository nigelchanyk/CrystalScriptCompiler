/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ImportDeclaration extends ParseTreeNode {
	
	private ImportName module;
	private ImportItems items; // Nullable

	public ImportDeclaration(ImportName module) {
		// Import the module as a whole
		this.module = module;
	}

	public ImportDeclaration(ImportName module, ImportItems items) {
		// Import given components from the module
		this.module = module;
		this.items = items;
	}

	@Override
	public void addDependencies(List<Name> importList) {
		importList.add(module.getRealName());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ImportDeclaration other = (ImportDeclaration) obj;
		if (!Objects.equals(this.module, other.module)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + Objects.hashCode(this.module);
		hash = 97 * hash + Objects.hashCode(this.items);
		return hash;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		module.setSymbolTable(symbolTable);
	}

	@Override
	public void linkDependentSymbolTables(Namespace globalNamespace) {
		if (items == null)
			symbolTable.addDependency(module);
		else
			symbolTable.addDependency(module, items);
	}

}
