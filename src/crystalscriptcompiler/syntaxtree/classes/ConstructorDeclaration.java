/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.exceptions.SignatureNotFoundException;
import crystalscriptcompiler.symbols.SignatureTree;
import crystalscriptcompiler.symbols.SymbolDeclaration;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.methods.OverloadableDeclaration;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.types.MultipleTypes;
import crystalscriptcompiler.syntaxtree.types.Type;
import crystalscriptcompiler.syntaxtree.types.VarType;

/**
 *
 * @author User
 */
public class ConstructorDeclaration extends OverloadableDeclaration {

	private static final String CONSTRUCTOR_NAME = "constructor";

	private ConstructorInvocation constructorInvocation; // Nullable
	
	public ConstructorDeclaration(Modifiers modifiers, ConstructorDeclarator declarator, Block block) {
		super(Kind.CONSTRUCTOR, modifiers, new VarType(), CONSTRUCTOR_NAME, block, declarator.getParameters());
		this.constructorInvocation = declarator.getConstructorInvocation();
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		if (constructorInvocation != null)
			constructorInvocation.setSymbolTable(this.symbolTable);
	}

	@Override
	public void validateModifiers() {
		modifiers.setForbiddenModifiers(Modifier.ABSTRACT, Modifier.STATIC, Modifier.OVERRIDE);
		modifiers.validateModifiers();
		modifiers.setDefaultAccessModifier(Modifier.PRIVATE);
		block.validateModifiers();
	}

	@Override
	public Type validate() {
		super.validate();
		if (constructorInvocation != null) {
			Type invocationType = constructorInvocation.getArguments().validate();
			if (constructorInvocation.getKind() == ConstructorInvocation.Kind.THIS) {
				SymbolDeclaration declaration = symbolTable.get(CONSTRUCTOR_NAME, SymbolTable.Scope.CURRENT_CLASS);
				if (declaration == null)
					throw new ReferenceNotFoundException(CONSTRUCTOR_NAME);
				// No type checking required because it is impossible to create a reference named "constructor"
				SignatureTree tree = (SignatureTree)declaration;
				if (!tree.containsDeclaration((MultipleTypes)invocationType))
					throw new SignatureNotFoundException(CONSTRUCTOR_NAME, (MultipleTypes)invocationType);
			}
		}
		return null;
	}

}
