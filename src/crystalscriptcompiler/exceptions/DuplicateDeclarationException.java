/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;

/**
 *
 * @author User
 */
public class DuplicateDeclarationException extends CompilerException {
	
	public DuplicateDeclarationException(String id) {
		super(StringResources.errorDuplicateDeclaration(id));
	}
}
