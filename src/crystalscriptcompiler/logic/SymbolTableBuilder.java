/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.logic;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;

/**
 *
 * @author User
 */
public class SymbolTableBuilder {
	
	public void createTables(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.initializeSymbolTable();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			createTables(namespace);
	}

	public void linkDependentTables(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.linkDependentSymbolTables(globalNamespace);

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			linkDependentTables(namespace);
	}
	
	public void linkInheritedTables(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.linkInheritedSymbolTables();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			linkInheritedTables(namespace);
	}
}
