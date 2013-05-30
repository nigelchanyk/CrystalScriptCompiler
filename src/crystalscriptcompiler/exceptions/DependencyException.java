/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import java.util.Collection;

/**
 *
 * @author User
 */
public class DependencyException extends CompilerException {
	
	
	public DependencyException(Collection<Exception> exceptions) {
		super(StringResources.errorParseDependencies(exceptions));
	}
	
}
