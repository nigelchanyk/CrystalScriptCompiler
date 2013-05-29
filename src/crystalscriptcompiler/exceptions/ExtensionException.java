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
public class ExtensionException extends CompilerException {
	
	public ExtensionException(File file, String expectedException) {
		super("The expecte file extension is " + expectedException + ", but " + file.getName() + " is given.");
	}
	
}
