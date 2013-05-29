/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.syntaxtree.imports.ImportDeclaration;

/**
 *
 * @author User
 */
public class ImportException extends CompilerException {
	
	public ImportException(ImportDeclaration a, ImportDeclaration b) {
		super("Identical imports detected.");
	}
	
}
