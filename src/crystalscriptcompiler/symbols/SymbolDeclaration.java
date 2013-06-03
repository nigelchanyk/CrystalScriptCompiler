/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;

/**
 *
 * @author User
 */
public abstract class SymbolDeclaration {
	
	public static enum Kind {
		CLASS,
		INTERFACE,
		METHOD,
		MODULE,
		VARIABLE
	}

	private ParseTreeNode node;
	private Kind kind;
	
	public SymbolDeclaration(Kind kind, ParseTreeNode node) {
		this.kind = kind;
		this.node = node;
	}

	public boolean hasChildDeclaration() {
		return false;
	}

	public SymbolTable getSymbolTable() {
		return node.getSymbolTable();
	}
	
}
