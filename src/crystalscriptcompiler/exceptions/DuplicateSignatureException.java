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
public class DuplicateSignatureException extends CompilerException {
	
	public DuplicateSignatureException(OverloadableDeclaration m1, OverloadableDeclaration m2) {
		super(StringResources.errorDuplicateSignature(m1.getId(), m2.getId()));
	}
	
}
