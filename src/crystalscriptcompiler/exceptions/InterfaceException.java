/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class InterfaceException extends CompilerException {
	
	public InterfaceException(ClassOrInterfaceType type) {
		super(StringResources.errorNotImplementable(type.getName().toString()));
	}
	
}
