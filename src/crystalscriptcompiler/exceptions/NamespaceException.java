/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import java.io.File;

/**
 *
 * @author User
 */
public class NamespaceException extends CompilerException {
	
	public NamespaceException(File file) {
		super("The following namespace cannot be found: " + file.getAbsolutePath());
	}
	
}
