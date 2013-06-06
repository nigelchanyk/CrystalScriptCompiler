/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.methods.Parameters;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.types.VarType;

/**
 *
 * @author User
 */
public class ConstructorDeclaration extends MemberDeclaration {

	private Block block;
	private Parameters parameters;
	private ConstructorInvocation superclassInvocation; // Nullable
	
	public ConstructorDeclaration(Modifiers modifiers, ConstructorDeclarator declarator, Block block) {
		super(Kind.CONSTRUCTOR, modifiers, new VarType(), declarator.getId());
		this.block = block;
		this.parameters = declarator.getParameters();
		this.superclassInvocation = declarator.getSuperclassInvocation();
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		if (superclassInvocation != null)
			superclassInvocation.setSymbolTable(symbolTable);

		SymbolTable innerTable = new SymbolTable(symbolTable, true);
		parameters.setSymbolTable(innerTable);
		block.setSymbolTable(innerTable);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		parameters.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
		block.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
	}

}
