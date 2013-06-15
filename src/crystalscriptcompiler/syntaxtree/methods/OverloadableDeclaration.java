/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.statements.Block;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public abstract class OverloadableDeclaration extends MemberDeclaration {

	protected Block block;
	protected Parameters parameters;
	
	public OverloadableDeclaration(Kind kind, Modifiers modifiers, Type type, String id, Block block, Parameters parameters) {
		super(kind, modifiers, type, id);
		this.block = block;
		this.parameters = parameters;
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
	public void determineReferenceType() {
		super.determineReferenceType();
		parameters.determineReferenceType();
		block.determineReferenceType();
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
	public Type validate() {
		parameters.validate();
		block.validate();
		return null;
	}

}
