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
import crystalscriptcompiler.syntaxtree.classes.Modifier;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.classes.TypeDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;
import crystalscriptcompiler.syntaxtree.types.Type;

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
		SymbolTable currentTable = new SymbolTable(symbolTable,
				moduleLevel ? SymbolTable.Kind.OUTER_CLASS_ROOT : SymbolTable.Kind.INNER_CLASS_ROOT);
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
		superInterfaces.determineReferenceType();
		members.determineReferenceType();
	}

	@Override
	public void addMethodToTable() {
		members.addMethodToTable();
	}

	@Override
	public void validateModifiers() {
		modifiers.setForbiddenModifiers(Modifier.ABSTRACT, Modifier.STATIC, Modifier.OVERRIDE);
		modifiers.validateModifiers();
		modifiers.setDefaultAccessModifier(Modifier.PUBLIC);
		members.validateModifiers();
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		members.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
	}

	@Override
	public Type validate() {
		for (ClassOrInterfaceType superInterface : superInterfaces)
			validateParentAccess(superInterface);
		members.validate();
		return null;
	}
	
}
