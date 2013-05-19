/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

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
}
