/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.exceptions.InheritanceException;
import crystalscriptcompiler.exceptions.InterfaceException;
import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.symbols.ClassSymbolDeclaration;
import crystalscriptcompiler.symbols.InterfaceSymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.interfaces.Interfaces;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class ClassDeclaration extends TypeDeclaration {
	
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
		symbolTable.getParent().addSymbol(id, this);
		members.addDeclarationToTable();
	}

	@Override
	public void createInheritanceTree() {
		if (superclass != null) {
			SymbolDeclaration symbol = symbolTable.get(superclass.getName(), SymbolTable.Scope.ALL);
			if (symbol == null)
				throw new ReferenceNotFoundException(superclass.getName());
			if (!(symbol instanceof ClassSymbolDeclaration))
				throw new InheritanceException(superclass, InheritanceException.ExpectedKind.CLASS);
			
			inherit(((ClassSymbolDeclaration)symbol).getDeclaration(), superclass);
		}

		for (ClassOrInterfaceType superType : interfaces) {
			SymbolDeclaration symbol = symbolTable.get(superType.getName(), SymbolTable.Scope.ALL);
			if (symbol == null)
				throw new ReferenceNotFoundException(superType.getName());
			if (!(symbol instanceof InterfaceSymbolDeclaration))
				throw new InterfaceException(superType);

			inherit(((InterfaceSymbolDeclaration)symbol).getDeclaration(), superType);
		}
		
		members.createInheritanceTree();
	}

	@Override
	public void linkInheritedSymbolTables() {
		super.linkInheritedSymbolTables();
		members.linkInheritedSymbolTables();
	}
	
}
