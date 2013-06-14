/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.classes.Modifier;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class AccessViolationException extends CompilerException {
	
	public AccessViolationException(Name name, Modifier modifier) {
		super(StringResources.errorAccessViolation(name.toString(), modifier.toString().toLowerCase()));
	}
}
