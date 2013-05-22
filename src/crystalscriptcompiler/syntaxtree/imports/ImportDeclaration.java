/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ImportDeclaration extends ParseTreeNode {
	
	private Name module;
	private String alias;

	public ImportDeclaration(Name module) {
		this.module = module;
	}

	public ImportDeclaration(Name module, String alias) {
		this.module = module;
		this.alias = alias;
	}
}
