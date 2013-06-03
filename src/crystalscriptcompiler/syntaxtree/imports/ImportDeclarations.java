/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.syntaxtree.SequentialCollection;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.List;

/**
 *
 * @author User
 */
public class ImportDeclarations extends SequentialCollection<ImportDeclaration> {
	
	public ImportDeclarations() {
	}

	public ImportDeclarations(ImportDeclaration declaration) {
		super(declaration);
	}

	public ImportDeclarations(ImportDeclaration declaration, ImportDeclarations next, Order order) {
		super(declaration, next, order);
	}

	@Override
	public void addDependencies(List<Name> importList) {
		for (ImportDeclaration declaration : this)
			declaration.addDependencies(importList);
	}

	@Override
	public void linkDependentSymbolTables(Namespace globalNamespace) {
		for (ImportDeclaration declaration : this)
			declaration.linkDependentSymbolTables(globalNamespace);
	}
	
}
