/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.symbols.VariableSymbolDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
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

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		SymbolTable innerTable = new SymbolTable(symbolTable, true);
		super.setSymbolTable(innerTable);
		block.setSymbolTable(innerTable);
		parameters.setSymbolTable(innerTable);
	}
	
	@Override
	public void addDeclarationToTable() {
		symbolTable.getParent().addSymbol(id, this);
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		parameters.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
		block.addVariablesToTable(VariableSymbolDeclaration.NO_INDEX);
	}
	
}
