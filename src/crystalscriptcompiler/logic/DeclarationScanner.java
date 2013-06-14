/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.logic;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;

/**
 *
 * @author User
 */
public class DeclarationScanner {

	public void scanTypes(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.addDeclarationToTable();
			}
		});
	}

	public void scanReference(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.determineReferenceType();
			}
		});
	}

	public void scanMethodDeclarations(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.addMethodToTable();
			}
		});
	}

	public void scanVariableDeclarations(final Namespace globalNamespace) {
		globalNamespace.executeAll(new Namespace.ModuleAction() {

			@Override
			public void execute(ParseTreeRoot module) {
				module.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
			}
		});
	}

}
