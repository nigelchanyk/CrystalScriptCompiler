/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.ParseTreeRoot;

/**
 *
 * @author User
 */
public class ModuleSymbolDeclaration extends SymbolDeclaration {
	
	private ParseTreeRoot module;

	public ModuleSymbolDeclaration(ParseTreeRoot module) {
		super(Kind.MODULE, module);
		this.module = module;
	}

	public ParseTreeRoot getModule() {
		return module;
	}

	@Override
	public boolean hasChildDeclaration() {
		return true;
	}
	
}
