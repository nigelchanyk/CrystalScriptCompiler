/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.interfaces;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclarations;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class InterfaceDeclaration extends MemberDeclaration {
	
	private Interfaces superInterfaces;
	private MemberDeclarations members;

	public InterfaceDeclaration(Modifiers modifiers, String id, Interfaces superInterfaces, MemberDeclarations members) {
		super(Kind.INTERFACE, modifiers, new ClassOrInterfaceType(new Name(id)), id);
		this.superInterfaces = superInterfaces;
		this.members = members;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		SymbolTable currentTable = new SymbolTable(symbolTable);
		super.setSymbolTable(currentTable);
		superInterfaces.setSymbolTable(symbolTable);
		members.setSymbolTable(currentTable);
	}

	@Override
	public void addDeclarationToTable() {
		symbolTable.addSymbol(id, this);
		members.addDeclarationToTable();
	}

	@Override
	public void linkInheritedSymbolTables() {
		for (ClassOrInterfaceType interfaceType : superInterfaces)
			symbolTable.addInterfaceInheritance(interfaceType);

		members.linkInheritedSymbolTables();
	}
	
}
