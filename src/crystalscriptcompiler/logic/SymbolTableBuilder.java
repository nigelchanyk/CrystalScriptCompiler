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
	
	public void start(Namespace globalNamespace) {
		for (ParseTreeRoot root : globalNamespace.moduleIterable())
			root.initializeSymbolTable();

		for (Namespace namespace : globalNamespace.subNamespaceIterable())
			start(namespace);
	}
	
}
