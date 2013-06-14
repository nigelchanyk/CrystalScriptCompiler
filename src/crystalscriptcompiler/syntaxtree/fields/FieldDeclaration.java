/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.fields;

import crystalscriptcompiler.exceptions.AccessViolationException;
import crystalscriptcompiler.exceptions.TypeMismatchException;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.classes.Modifier;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;
import crystalscriptcompiler.syntaxtree.types.Type;
import crystalscriptcompiler.syntaxtree.variables.VariableDeclarator;

/**
 *
 * @author User
 */
public class FieldDeclaration extends MemberDeclaration {
	
	private VariableDeclarator variable;

	public FieldDeclaration(Modifiers modifiers, Type type, VariableDeclarator variable) {
		super(Kind.FIELD, modifiers, type, variable.getId());
		this.variable = variable;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		variable.setSymbolTable(symbolTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		symbolTable.addSymbol(id, type, VariableSymbolDeclaration.NO_INDEX, VariableSymbolDeclaration.Scope.FIELD);
	}

	@Override
	public void validateModifiers() {
		modifiers.setForbiddenModifiers(Modifier.ABSTRACT, Modifier.OVERRIDE);
		modifiers.validateModifiers();
		modifiers.setDefaultAccessModifier(Modifier.PRIVATE);
	}

	@Override
	public Type validate() {
		if (type instanceof ClassOrInterfaceType && modifiers.getAccessModifier() != Modifier.PUBLIC) {
			ClassOrInterfaceType classOrInterfaceType = (ClassOrInterfaceType)type;
			if (!symbolTable.containsSymbol(classOrInterfaceType.getName(), SymbolTable.Scope.MODULAR))
				throw new AccessViolationException(classOrInterfaceType.getName(), modifiers.getAccessModifier());
		}
		Type assignedType = variable.validate();
		if (assignedType == null)
			return null;
		if (type.equals(assignedType))
			return null;
		
		throw new TypeMismatchException(type.toString(), assignedType.toString());
	}
	
}
