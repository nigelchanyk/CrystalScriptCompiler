/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;

/**
 *
 * @author User
 */
public class DuplicateSignatureException extends CompilerException {
	
	public DuplicateSignatureException(MethodDeclaration declaration) {
		super(StringResources.errorDuplicateSignature(declaration.getId()));
	}
	
}
