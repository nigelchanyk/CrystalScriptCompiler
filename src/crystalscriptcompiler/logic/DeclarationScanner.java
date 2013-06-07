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

	public void scanTypes(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.addDeclarationToTable();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			scanTypes(namespace);
	}

	public void scanReference(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.determineReferenceType();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			scanReference(namespace);
	}

	public void scanMethodDeclarations(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.addMethodToTable();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			scanMethodDeclarations(namespace);
	}

	public void scanVariableDeclarations(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			scanVariableDeclarations(namespace);
	}

}
