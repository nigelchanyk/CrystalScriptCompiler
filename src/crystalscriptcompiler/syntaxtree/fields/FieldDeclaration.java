/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.fields;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
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
	
}
