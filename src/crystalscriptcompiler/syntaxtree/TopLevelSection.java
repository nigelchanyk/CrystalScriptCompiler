/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public class TopLevelSection extends ParseTreeNode {
	
	private MemberDeclaration declaration;
	
	public TopLevelSection(MemberDeclaration declaration) {
		this.declaration = declaration;
		declaration.setModuleLevel(true);
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		declaration.setSymbolTable(symbolTable);
	}

	@Override
	public void addDeclarationToTable() {
		declaration.addDeclarationToTable();
	}

	@Override
	public void createInheritanceTree() {
		declaration.createInheritanceTree();
	}

	@Override
	public void linkInheritedSymbolTables() {
		declaration.linkInheritedSymbolTables();
	}

	@Override
	public void addMethodToTable() {
		declaration.addMethodToTable();
	}

	@Override
	public void addVariablesToTable(int statementIndex) {
		declaration.addVariablesToTable(statementIndex);
	}

	@Override
	public void determineReferenceType() {
		declaration.determineReferenceType();
	}

	@Override
	public void validateModifiers() {
		declaration.validateModifiers();
	}

	@Override
	public Type validate() {
		return declaration.validate();
	}
	
}
