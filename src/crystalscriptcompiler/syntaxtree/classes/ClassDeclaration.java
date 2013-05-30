/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.interfaces.Interfaces;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class ClassDeclaration extends MemberDeclaration {
	
	private ClassOrInterfaceType superclass;
	private Interfaces interfaces;
	private MemberDeclarations members;
	
	public ClassDeclaration(Modifiers modifiers, String id, SuperclassOptional superclassOptional,
			Interfaces interfaces, MemberDeclarations members) {
		super(Kind.CLASS, modifiers, new ClassOrInterfaceType(new Name(id)), id);
		this.interfaces = interfaces;
		this.members = members;
		this.superclass = superclassOptional.getSuperclass();
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		superclass.setSymbolTable(symbolTable);
		interfaces.setSymbolTable(symbolTable);
		members.setSymbolTable(new SymbolTable(symbolTable));
	}
	
}
