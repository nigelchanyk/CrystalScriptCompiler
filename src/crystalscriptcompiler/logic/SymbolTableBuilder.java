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
	
	public void createTables(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.initializeSymbolTable(globalNamespace);
			}
		});
	}

	public void linkDependentTables(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.linkDependentSymbolTables(globalNamespace);
			}
		});
	}

	public void createInheritanceTree(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.createInheritanceTree();
			}
		});
	}
	
	public void linkInheritedTables(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.linkInheritedSymbolTables();
			}
		});
	}
}
