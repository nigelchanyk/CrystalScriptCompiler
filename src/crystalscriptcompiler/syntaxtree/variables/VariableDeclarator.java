/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.variables.initializers.GenericVariableInitializer;

/**
 *
 * @author User
 */
public class VariableDeclarator extends ParseTreeNode {
	
	private String id;
	private GenericVariableInitializer init; // Nullable

	public VariableDeclarator(String id) {
		this.id = id;
	}
	
	public VariableDeclarator(String id, GenericVariableInitializer init) {
		this.id = id;
		this.init = init;
	}

	public String getId() {
		return id;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);

		if (init != null)
			init.setSymbolTable(symbolTable);
		
	}
	
}
