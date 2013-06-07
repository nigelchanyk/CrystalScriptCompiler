/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public abstract class MemberDeclaration extends ParseTreeNode {
	
	public static enum Kind {
		CONSTRUCTOR,
		FIELD,
		METHOD,
		CLASS,
		INTERFACE
	}

	protected Modifiers modifiers;
	protected Type type;
	protected String id;

	private Kind kind;
	
	public MemberDeclaration(Kind kind, Modifiers modifiers, Type type, String id) {
		this.kind = kind;
		this.modifiers = modifiers;
		this.type = type;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Kind getKind() {
		return kind;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		modifiers.setSymbolTable(symbolTable);
		type.setSymbolTable(symbolTable);
	}

	@Override
	public void determineReferenceType() {
		type.determineReferenceType();
	}
}
