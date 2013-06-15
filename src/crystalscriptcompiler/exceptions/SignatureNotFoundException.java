/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.types.MultipleTypes;

/**
 *
 * @author User
 */
public class SignatureNotFoundException extends CompilerException {
	
	public SignatureNotFoundException(String methodName, MultipleTypes types) {
		super(StringResources.errorSignatureNotFound(methodName, types.toString()));
	}
	
}
