/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.methods.OverloadableDeclaration;

/**
 *
 * @author User
 */
public class AmbiguousSignatureException extends CompilerException {
	
	public AmbiguousSignatureException(OverloadableDeclaration declaration) {
		super(StringResources.errorAmbiguousSignature(declaration.getId()));
	}
	
}
