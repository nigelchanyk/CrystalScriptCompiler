/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.interfaces;

import crystalscriptcompiler.exceptions.InheritanceException;
import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.symbols.InterfaceSymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclarations;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.classes.TypeDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class InterfaceDeclaration extends TypeDeclaration {
	
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
		symbolTable.getParent().addSymbol(id, this);
		members.addDeclarationToTable();
	}

	@Override
	public void createInheritanceTree() {
		for (ClassOrInterfaceType interfaceType : superInterfaces) {
			SymbolDeclaration symbol = symbolTable.get(interfaceType.getName(), SymbolTable.Scope.ALL);
			if (symbol == null)
				throw new ReferenceNotFoundException(interfaceType.getName());
			if (!(symbol instanceof InterfaceSymbolDeclaration))
				throw new InheritanceException(interfaceType, InheritanceException.ExpectedKind.INTERFACE);

			inherit(((InterfaceSymbolDeclaration)symbol).getDeclaration(), interfaceType);
		}
	}

	@Override
	public void linkInheritedSymbolTables() {
		super.linkInheritedSymbolTables();
		members.linkInheritedSymbolTables();
	}

	@Override
	public void determineReferenceType() {
		members.determineReferenceType();
	}

	@Override
	public void addMethodToTable() {
		members.addMethodToTable();
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		members.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
	}
	
}
