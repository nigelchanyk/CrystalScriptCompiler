/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.interfaces.Interfaces;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class ClassDeclaration extends MemberDeclaration {
	
	private ClassOrInterfaceType superclass; // Nullable
	private Interfaces interfaces;
	private MemberDeclarations members;
	
	public ClassDeclaration(Modifiers modifiers, String id, SuperclassOptional superclassOptional,
			Interfaces interfaces, MemberDeclarations members) {
		super(Kind.CLASS, modifiers, new ClassOrInterfaceType(new Name(id)), id);
		this.interfaces = interfaces;
		this.members = members;
		this.superclass = superclassOptional.getSuperclass();
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		SymbolTable classSymbolTable = new SymbolTable(symbolTable);
		super.setSymbolTable(classSymbolTable);
		
		if (superclass != null)
			superclass.setSymbolTable(symbolTable);

		interfaces.setSymbolTable(symbolTable);
		members.setSymbolTable(classSymbolTable);
	}

	@Override
	public void addDeclarationToTable() {
		symbolTable.addSymbol(id, this);
		members.addDeclarationToTable();
	}

	@Override
	public void linkInheritedSymbolTables() {
		if (superclass != null)
			symbolTable.addClassInheritance(superclass);

		for (ClassOrInterfaceType superType : interfaces)
			symbolTable.addInterfaceImplementation(superType);

		members.linkInheritedSymbolTables();
	}
	
}
