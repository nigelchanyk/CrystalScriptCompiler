/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class ImportDeclarations extends SequentialCollection<ImportDeclaration> {
	
	public ImportDeclarations() {
	}

	public ImportDeclarations(ImportDeclaration declaration) {
		super(declaration);
	}

	public ImportDeclarations(ImportDeclaration declaration, ImportDeclarations next, Order order) {
		super(declaration, next, order);
	}
	
}
