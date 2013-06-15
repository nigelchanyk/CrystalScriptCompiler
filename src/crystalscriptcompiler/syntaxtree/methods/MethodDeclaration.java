/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.exceptions.ModifierException;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.Modifier;
import crystalscriptcompiler.syntaxtree.statements.Block;

/**
 *
 * @author User
 */
public class MethodDeclaration extends OverloadableDeclaration {
	
	public MethodDeclaration(MethodHeader header) {
		this(header, new Block());
	}
	
	public MethodDeclaration(MethodHeader header, Block block) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId(),
				block, header.getDeclarator().getParameters());
	}

	@Override
	public void validateModifiers() {
		modifiers.validateModifiers();

		if (modifiers.contains(Modifier.ABSTRACT) && modifiers.contains(Modifier.STATIC))
			throw new ModifierException.ModifierConflictException(Modifier.ABSTRACT, Modifier.STATIC);
		if (modifiers.contains(Modifier.ABSTRACT) && modifiers.contains(Modifier.OVERRIDE))
			throw new ModifierException.ModifierConflictException(Modifier.ABSTRACT, Modifier.OVERRIDE);
		if (modifiers.contains(Modifier.STATIC) && modifiers.contains(Modifier.OVERRIDE))
			throw new ModifierException.ModifierConflictException(Modifier.STATIC, Modifier.OVERRIDE);

		modifiers.setDefaultAccessModifier(Modifier.PUBLIC);
		
		block.validateModifiers();
	}
	
}
