/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ReferenceNotFoundException extends CompilerException {

	public ReferenceNotFoundException(String name) {
		super(StringResources.errorReferenceNotFound(name));
	}

	public ReferenceNotFoundException(Name name) {
		super(StringResources.errorReferenceNotFound(name.toString()));
	}

	public ReferenceNotFoundException(Name name, Name scope) {
		super(StringResources.errorReferenceNotFound(name.toString(), scope.toString()));
	}
	
}
