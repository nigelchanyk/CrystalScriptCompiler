/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclarations;
import crystalscriptcompiler.syntaxtree.expressions.ArgumentList;
import crystalscriptcompiler.syntaxtree.types.Type;

/**
 *
 * @author User
 */
public class InstantiationExpression extends Primary {
	
	private Type type;
	private ArgumentList arguments;
	private MemberDeclarations overrides;

	public InstantiationExpression(Type type, ArgumentList arguments) {
		this(type, arguments, new MemberDeclarations());
	}
	
	public InstantiationExpression(Type type, ArgumentList arguments, MemberDeclarations overrides) {
		this.type = type;
		this.arguments = arguments;
		this.overrides = overrides;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		type.setSymbolTable(symbolTable);
		arguments.setSymbolTable(symbolTable);
		overrides.setSymbolTable(new SymbolTable(symbolTable));
	}
	
}
