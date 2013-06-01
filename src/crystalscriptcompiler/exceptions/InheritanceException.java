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
public class InheritanceException extends CompilerException {

	public static enum ExpectedKind {
		CLASS,
		INTERFACE
	}
	
	public InheritanceException(ClassOrInterfaceType type, ExpectedKind kind) {
		super(StringResources.errorNotInheritable(type.getName().toString(), kind.toString().toLowerCase()));
	}
	
}
