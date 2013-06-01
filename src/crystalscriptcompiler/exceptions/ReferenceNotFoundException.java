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
	
	public ReferenceNotFoundException(Name name) {
		super(StringResources.errorReferenceNotFound(name.toString()));
	}
	
}
