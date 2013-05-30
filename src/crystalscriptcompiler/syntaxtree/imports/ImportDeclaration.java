/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

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
	
	private Name module;
	private String alias;

	public ImportDeclaration(Name module) {
		this.module = module;
	}

	public ImportDeclaration(Name module, String alias) {
		this.module = module;
		this.alias = alias;
	}

	@Override
	public void addDependencies(List<Name> importList) {
		importList.add(module);
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
		hash = 97 * hash + Objects.hashCode(this.alias);
		return hash;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		module.setSymbolTable(symbolTable);
	}

}
