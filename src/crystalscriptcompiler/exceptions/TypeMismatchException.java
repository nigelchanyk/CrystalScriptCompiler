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
public class TypeMismatchException extends CompilerException {
	
	public TypeMismatchException(String expected, String given) {
		super(StringResources.errorTypeMismatch(expected, given));
	}
	
}
