/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.exceptions.AccessViolationException;
import crystalscriptcompiler.exceptions.CircularInheritanceException;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public abstract class TypeDeclaration extends MemberDeclaration {

	private LinkedList<TypeDeclaration> directParents = new LinkedList<>();

	public TypeDeclaration(Kind kind, Modifiers modifiers, ClassOrInterfaceType type, String id) {
		super(kind, modifiers, type, id);
	}

	public boolean isInstanceOf(TypeDeclaration declaration) {
		if (declaration == this)
			return true;

		return isSubclassOf(declaration);
	}

	public boolean isSubclassOf(TypeDeclaration declaration) {
		for (TypeDeclaration parent : directParents) {
			if (parent.isInstanceOf(parent))
				return true;
		}

		return false;
	}

	protected void inherit(TypeDeclaration parentDeclaration, ClassOrInterfaceType parentAlias) {
		if (parentDeclaration.isInstanceOf(this))
			throw new CircularInheritanceException((ClassOrInterfaceType)type, parentAlias);

		directParents.addLast(parentDeclaration);
	}

	@Override
	public void linkInheritedSymbolTables() {
		for (TypeDeclaration declaration : directParents)
			symbolTable.addInheritance(declaration.getSymbolTable());
	}

	public void validateParentAccess(ClassOrInterfaceType parentAlias) {
		Modifier accessModifier = parentAlias.getReferenceType().getAccessModifier();
		if (accessModifier == Modifier.PUBLIC)
			return;
		if (symbolTable.containsSymbol(parentAlias.getName(), SymbolTable.Scope.MODULAR))
			return;

		throw new AccessViolationException(parentAlias.getName(), accessModifier);
	}
	
}
