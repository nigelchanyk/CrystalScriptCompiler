/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.exceptions.ModifierException;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.classes.Modifier;
import crystalscriptcompiler.syntaxtree.statements.Block;

/**
 *
 * @author User
 */
public class MethodDeclaration extends MemberDeclaration {
	
	private Block block;
	private Parameters parameters;

	public MethodDeclaration(MethodHeader header) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.block = new Block();
		this.parameters = new Parameters();
	}
	
	public MethodDeclaration(MethodHeader header, Block block) {
		super(Kind.METHOD, header.getModifiers(), header.getType(), header.getDeclarator().getId());
		this.block = block;
		this.parameters = header.getDeclarator().getParameters();
	}

	public Parameters getParameters() {
		return parameters;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		SymbolTable innerTable = new SymbolTable(symbolTable, SymbolTable.Kind.LOCAL_ROOT);
		super.setSymbolTable(innerTable);
		block.setSymbolTable(innerTable);
		parameters.setSymbolTable(innerTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		parameters.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
		block.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
	}

	@Override
	public void addMethodToTable() {
		symbolTable.getParent().addSymbol(id, this);
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

	@Override
	public void determineReferenceType() {
		super.determineReferenceType();
		parameters.determineReferenceType();
		block.determineReferenceType();
	}
	
}
