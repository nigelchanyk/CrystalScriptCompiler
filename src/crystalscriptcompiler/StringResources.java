/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import java.util.Collection;

/**
 *
 * @author User
 */
public class StringResources {
	
	public static final String ERROR_NO_ARGUMENTS = "No arguments provided.";
	
	public static final String HINT_SIMPLE_USAGE = "Usage: java -jar CrystalScriptCompiler.jar [File].";
	
	public static String errorFileNotFound(String fileName) {
		return "[Error] Unable to locate file: " + fileName;
	}

	public static String errorRead(String fileName) {
		return "[Error] Unable to read file: " + fileName;
	}

	public static String errorParseDependencies(Collection<Exception> exceptions) {
		StringBuilder sb = exceptions.size() > 1 ? new StringBuilder("Multiple errors have been detected while parsing dependencies:\n") : new StringBuilder();

		for (Exception exception : exceptions)
			sb.append(exception.getMessage()).append('\n');

		return sb.toString();
	}
}
