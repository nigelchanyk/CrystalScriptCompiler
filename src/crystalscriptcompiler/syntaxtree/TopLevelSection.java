/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;

/**
 *
 * @author User
 */
public class TopLevelSection extends ParseTreeNode {
	
	private MemberDeclaration declaration;
	
	public TopLevelSection(MemberDeclaration declaration) {
		this.declaration = declaration;
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
	
}
