/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.exceptions.TypeMismatchException;
import crystalscriptcompiler.symbols.ClassSymbolDeclaration;
import crystalscriptcompiler.symbols.InterfaceSymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.TypeDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ClassOrInterfaceType extends Type {
	
	private Name name;
	private TypeDeclaration referenceType;
	
	public ClassOrInterfaceType(Name name) {
		this.name = name;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		name.setSymbolTable(symbolTable);
	}

	public Name getName() {
		return name;
	}

	@Override
	public void determineReferenceType() {
		SymbolDeclaration declaration = symbolTable.get(name, SymbolTable.Scope.ALL);
		if (declaration == null)
			throw new ReferenceNotFoundException(name);

		if (declaration.getKind() == SymbolDeclaration.Kind.CLASS)
			referenceType = ((ClassSymbolDeclaration)declaration).getDeclaration();
		else if (declaration.getKind() == SymbolDeclaration.Kind.INTERFACE)
			referenceType = ((InterfaceSymbolDeclaration)declaration).getDeclaration();
		else
			throw new TypeMismatchException("class or interface", declaration.getKind().toString().toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClassOrInterfaceType other = (ClassOrInterfaceType) obj;
		return referenceType == other.referenceType;
	}

	@Override
	public int hashCode() {
		int hash = 7 * referenceType.hashCode();
		return hash;
	}
	
}
