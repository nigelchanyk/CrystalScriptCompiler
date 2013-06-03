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
public class CircularInheritanceException extends CompilerException {
	
	public CircularInheritanceException(ClassOrInterfaceType childClass, ClassOrInterfaceType parentClass) {
		super(StringResources.errorCircularInheritance(childClass.getName().toString(), parentClass.getName().toString()));
	}
	
}
