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
public class ImportItems extends SequentialCollection<ImportName> {
	
	public ImportItems(ImportName name) {
		super(name);
	}

	public ImportItems(ImportName name, ImportItems next) {
		super(name, next);
	}
	
}
