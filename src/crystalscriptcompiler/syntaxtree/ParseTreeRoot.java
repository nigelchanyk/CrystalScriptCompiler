/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.syntaxtree.imports.ImportDeclarations;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.List;

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

	@Override
	public void addDependencies(List<Name> importList) {
	}
	
}
