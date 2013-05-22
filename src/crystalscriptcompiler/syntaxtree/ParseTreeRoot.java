/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.syntaxtree.imports.ImportDeclarations;

/**
 *
 * @author User
 */
public class ParseTreeRoot extends ParseTreeNode {
	
	private ImportDeclarations imports;
	private TopLevelSections sections;

	public ParseTreeRoot(ImportDeclarations imports, TopLevelSections sections) {
		this.imports = imports;
		this.sections = sections;
	}
	
}
