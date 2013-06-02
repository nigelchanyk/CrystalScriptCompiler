/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.imports.ImportDeclarations;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.List;

/**
 *
 * @author User
 */
public class ParseTreeRoot extends ParseTreeNode {
	
	private ImportDeclarations imports;
	private TopLevelSections sections;

	public ParseTreeRoot() {
		imports = new ImportDeclarations();
		sections = new TopLevelSections();
	}

	public ParseTreeRoot(ImportDeclarations imports, TopLevelSections sections) {
		this.imports = imports;
		this.sections = sections;
	}

	@Override
	public void addDependencies(List<Name> importList) {
		imports.addDependencies(importList);
	}

	public void initializeSymbolTable(Namespace namespace) {
		symbolTable = new SymbolTable(namespace);
		imports.setSymbolTable(symbolTable);
		sections.setSymbolTable(symbolTable);
	}

	@Override
	public void addDeclarationToTable() {
		sections.addDeclarationToTable();
	}

	@Override
	public void linkDependentSymbolTables(Namespace globalNamespace) {
		imports.linkDependentSymbolTables(globalNamespace);
	}

	@Override
	public void linkInheritedSymbolTables() {
		sections.linkInheritedSymbolTables();
	}
	
}
